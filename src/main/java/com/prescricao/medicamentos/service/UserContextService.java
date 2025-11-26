package com.prescricao.medicamentos.service;

import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    // Mock: Identifica o profissional logado (hardcoded)
    private static final Integer PROFISSIONAL_ID = 99;
    private static final Integer ESPECIALIDADE_ID = 11;

    public Integer getProfissionalId() {
        return PROFISSIONAL_ID;
    }

    public Integer getEspecialidadeId() {
        return ESPECIALIDADE_ID;
    }
}
