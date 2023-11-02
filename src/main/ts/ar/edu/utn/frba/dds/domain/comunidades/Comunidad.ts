import {Column, Entity, PrimaryGeneratedColumn } from "typeorm";
import {Puntaje} from "./gradosDeConfianza/Puntaje";

@Entity()
export class Comunidad {
    @PrimaryGeneratedColumn()
    id: number

    administradores: Array<Miembro> = new Array<Miembro>()
    miembros: Array<Miembro> = new Array<Miembro>()

    propuestasFusion: Array<PropuestaFusion> = new Array<PropuestaFusion>()

    incidentes: Array<Incidente> = new Array<Incidente>()

    @Column()
    nombre: string

    @Column()
    descripcion: string

    puntaje: Puntaje

    agregarUsuarios (... miembros: Miembro) {
        this.miembros.push(miembros)
        for (let miembro in miembros) {
            miembro.comunidades.push(this)
        }
    }

    removerUsuarios (... miembros: Miembro) {
        for (let miembro in miembros) {
            const index = this.miembros.indexOf(miembro)
            this.miembros.splice(index, 1)
        }
    }

    cantidadDeMiembros(): number {
        return this.miembros.length
    }

    cantidadDeIncidentes(): number {
        return this.incidentes.length
    }

    notificarMiembros (incidente: Incidente) {
        this.incidentes.push(incidente)
        let notificacion: Notificacion = new Notificacion()
        notificacion.crearNotificacion(incidente)
        this.miembros.filter((miembro: Miembro) =>
            miembro.usuario != incidente.abridor.usuario
        ).forEach((miembro: Miembro) =>
            miembro.medioDeNotificacion.evaluarEnvioDeNotificacion(notificacion)
        )
    }

    cerrarIncidente (autor: Miembro, incidente: Incidente) {
        incidente.meCierro(autor)
        this.notificarMiembros(incidente)
    }

    comunidadPuntaje(): ComunidadPuntaje {
        return new ComunidadPuntajeBuilder.conId(this.id).conPuntaje(this.puntaje.valor).conMiembros(this.miembros).construir()
    }

    comunidadFusionable(): ComunidadFusionable {
        return new ComunidadFusionableBuilder().conId(this.id).conIncidentes(this.incidentes).conEstablecimientos(establecimientosDeIncidentes())
            .conServicios(serviciosDeIncidentes()).conUsuarios(this.miembros).conPropuestasAnteriores(this.propuestasFusion)
            .conGradoDeConfianza(this.puntaje.valor).construir()
    }

    establecimientosDeIncidentes(): Array<Establecimiento> {
        this.incidentes.map(Incidente::establecimiento)
    }

    serviciosDeIncidentes(): Array<PrestacionDeServicio> {
        this.incidentes.map(Incidente::prestacionDeServicio)
    }

    actualizarPuntajes (comunidadPuntaje: ComunidadPuntaje) {
        this.puntaje.valor(comunidadPuntaje.puntaje)
        actualizarPuntajesMiembros(comunidadPuntaje)
    }

    actualizarPuntajesMiembros (comunidadPuntaje: ComunidadPuntaje) {
        comunidadPuntaje.miembros.forEach((miembroPuntaje: MiembroPuntaje) =>
            this.miembros.forEach((miembro: Miembros) => {
                    if (miembro.id == miembroPuntaje.id) {
                        miembro.puntaje.valor(miembroPuntaje.puntaje)
                    }
                }
            )
        )
    }
}