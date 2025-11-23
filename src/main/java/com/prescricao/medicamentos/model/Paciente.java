package com.prescricao.medicamentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PACIENTE")
public class Paciente {

    @Id
    @Column(name = "IDPACIENTE")
    private Integer id;

    @Column(name = "ID_DOCUMENTO")
    private Long idDocumento;

    @Column(name = "RGPACIENTE")
    private String rg;

    @Column(name = "ESTDORGPAC")
    private String estadoRg;

    @Column(name = "STATUSPAC")
    private String status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Long getIdDocumento() { return idDocumento; }
    public void setIdDocumento(Long idDocumento) { this.idDocumento = idDocumento; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getEstadoRg() { return estadoRg; }
    public void setEstadoRg(String estadoRg) { this.estadoRg = estadoRg; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
