import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Municipio {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({name: "municipio"})
    nombre: string;

    constructor (nombre: string) {
        this.nombre = nombre;
    }
}
