package com.prescricao.medicamentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROFISSIONAL")
public class Profissional {

    @Id
    @Column(name = "IDPROFISSIO")
    private Integer id;

    @Column(name = "ID_DOCUMENTO")
    private Long idDocumento;

    @Column(name = "TIPOPROFI")
    private String tipo;

    @Column(name = "ID_CONSEPROFI")
    private Integer idConseProfi;

    @Column(name = "STATUSPROFI")
    private String status;

    @Column(name = "ID_SUPPROFI")
    private Integer supervisor;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Long getIdDocumento() { return idDocumento; }
    public void setIdDocumento(Long idDocumento) { this.idDocumento = idDocumento; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getSupervisor() { return supervisor; }
    public void setSupervisor(Integer supervisor) { this.supervisor = supervisor; }

    public Integer getIdConseProfi() { return idConseProfi; }
    public void setIdConseProfi(Integer idConseProfi) { this.idConseProfi = idConseProfi; }
}
