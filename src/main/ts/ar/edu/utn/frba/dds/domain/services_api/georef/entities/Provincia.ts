import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Provincia {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({name: "provincia"})
    nombre: string;

    constructor (nombre: string) {
        this.nombre = nombre;
    }
}
