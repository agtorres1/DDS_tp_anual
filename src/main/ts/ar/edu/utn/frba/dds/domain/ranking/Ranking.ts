export interface Ranking {
    generarRanking (comunidades: Array<Comunidad>, entidades: Array<Entidad>): Array<Entidad>

    name(): string
}