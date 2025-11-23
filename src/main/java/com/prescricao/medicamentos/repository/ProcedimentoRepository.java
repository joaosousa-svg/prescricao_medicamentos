package com.prescricao.medicamentos.repository;

import com.prescricao.medicamentos.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer> {
}
