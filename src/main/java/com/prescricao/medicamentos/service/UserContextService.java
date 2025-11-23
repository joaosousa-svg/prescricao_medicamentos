package com.prescricao.medicamentos.service;

import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    // Mock: Identifica o profissional logado (hardcoded)
    private static final Integer PROFISSIONAL_ID = 99;

    public Integer getProfissionalId() {
        return PROFISSIONAL_ID;
    }
}
