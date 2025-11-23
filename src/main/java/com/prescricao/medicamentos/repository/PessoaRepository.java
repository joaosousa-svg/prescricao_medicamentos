package com.prescricao.medicamentos.repository;

import com.prescricao.medicamentos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    // Buscar pessoa por ID_DOCUMENTO
    java.util.Optional<Pessoa> findByIdDocumento(Long idDocumento);
}
