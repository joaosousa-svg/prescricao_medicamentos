package com.prescricao.medicamentos.repository;

import com.prescricao.medicamentos.model.ProntuarioTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProntuarioTemporarioRepository extends JpaRepository<ProntuarioTemporario, Integer> {
    
    // Buscar prontuários do profissional e especialidade específicos, excluindo os APROVADOS
    @Query("SELECT p FROM ProntuarioTemporario p WHERE p.profissional = ?1 AND p.especialidade = ?2 AND (p.statusAprovacao = 'PENDENTE' OR p.statusAprovacao = 'REPROVADO')")
    List<ProntuarioTemporario> findByProfissionalAndEspecialidade(Integer profissionalId, Integer especialidadeId);
}
