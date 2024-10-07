export class BusDTO {
  constructor(
    public id: number | null,
    public ruta_id: number,
    public numeroPlaca: string,
    public modelo: string
  ) {}
}
