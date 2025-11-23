package com.prescricao.medicamentos.repository;

import com.prescricao.medicamentos.model.ProntuarioTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProntuarioTemporarioRepository extends JpaRepository<ProntuarioTemporario, Integer> {
    
    // Buscar prontuários do profissional excluindo os APROVADOS (que já foram para PRONTUARIO definitivo)
    @Query("SELECT p FROM ProntuarioTemporario p WHERE p.profissional = ?1 AND (p.statusAprovacao = 'PENDENTE' OR p.statusAprovacao = 'REPROVADO')")
    List<ProntuarioTemporario> findByProfissional(Integer profissionalId);
}
