package com.co.dto;

public class HorarioDTO {
    private Long id;
    private String horaInicio;
    private String horaFin;
    private String dias; // Días de la semana

    // Constructor
    public HorarioDTO(Long id, String horaInicio, String horaFin, String dias) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dias = dias;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

    public String getDias() { return dias; }
    public void setDias(String dias) { this.dias = dias; }
}
