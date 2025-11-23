package com.prescricao.medicamentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DOCUMENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @Column(name = "IDDOCUMENTO")
    private Integer id;

    @Column(name = "DOCUMENTO")
    private String numero;
}
