import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Departamento {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({name: "departamento"})
    nombre: string;

    constructor (nombre: string) {
        this.nombre = nombre;
    }
}
