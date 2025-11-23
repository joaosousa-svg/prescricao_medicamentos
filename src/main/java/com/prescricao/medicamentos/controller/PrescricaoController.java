package com.prescricao.medicamentos.controller;

import com.prescricao.medicamentos.dto.CorrigirPrescricaoRequest;
import com.prescricao.medicamentos.dto.PrescricaoDTO;
import com.prescricao.medicamentos.model.ProntuarioTemporario;
import com.prescricao.medicamentos.service.PrescricaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescricoes")
@CrossOrigin(origins = "*")
public class PrescricaoController {

    private final PrescricaoService prescricaoService;

    public PrescricaoController(PrescricaoService prescricaoService) {
        this.prescricaoService = prescricaoService;
    }

    /**
     * GET /api/prescricoes
     * Lista todas as prescrições do profissional logado
     * (Prescrições são criadas por outro módulo na tabela PRONTUARIO_TEMPORARIO)
     */
    @GetMapping
    public ResponseEntity<List<PrescricaoDTO>> listarPrescricoes() {
        List<PrescricaoDTO> prescricoes = prescricaoService.getMinhasPrescricoes();
        return ResponseEntity.ok(prescricoes);
    }

    /**
     * GET /api/prescricoes/debug
     * Endpoint temporário para verificar dados no banco
     */
    @GetMapping("/debug")
    public ResponseEntity<?> debug() {
        return ResponseEntity.ok(prescricaoService.debugDados());
    }

    /**
     * PUT /api/prescricoes/corrigir
     * Corrige uma prescrição reprovada
     */
    @PutMapping("/corrigir")
    public ResponseEntity<ProntuarioTemporario> corrigirPrescricao(@RequestBody CorrigirPrescricaoRequest request) {
        ProntuarioTemporario prontuario = prescricaoService.corrigirPrescricao(request);
        return ResponseEntity.ok(prontuario);
    }

    /**
     * PUT /api/prescricoes/editar
     * Edita uma prescrição PENDENTE (antes do supervisor analisar)
     */
    @PutMapping("/editar")
    public ResponseEntity<ProntuarioTemporario> editarPrescricao(@RequestBody CorrigirPrescricaoRequest request) {
        ProntuarioTemporario prontuario = prescricaoService.editarPrescricao(request);
        return ResponseEntity.ok(prontuario);
    }

    /**
     * DELETE /api/prescricoes/cancelar/{id}
     * Cancela (deleta) uma prescrição permanentemente
     */
    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<Void> cancelarPrescricao(@PathVariable Integer id) {
        prescricaoService.cancelarPrescricao(id);
        return ResponseEntity.ok().build();
    }

    /**
     * GET /api/prescricoes/{id}
     * Busca detalhes completos de uma prescrição específica
     */
    @GetMapping("/{id}")
    public ResponseEntity<PrescricaoDTO> buscarPrescricao(@PathVariable Integer id) {
        List<PrescricaoDTO> todas = prescricaoService.getMinhasPrescricoes();
        return todas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
