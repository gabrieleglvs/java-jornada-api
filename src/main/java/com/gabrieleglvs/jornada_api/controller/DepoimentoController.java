package com.gabrieleglvs.jornada_api.controller;

import com.gabrieleglvs.jornada_api.dto.depoimento.DadosAtualizarDepoimento;
import com.gabrieleglvs.jornada_api.dto.depoimento.DadosCadastrarDepoimento;
import com.gabrieleglvs.jornada_api.service.DepoimentoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("")
public class DepoimentoController {
    @Autowired
    private DepoimentoService service;

    @PostMapping("depoimentos")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastrarDepoimento dados,
                                    UriComponentsBuilder uriBuilder){
        return service.cadastrarDepoimento(dados, uriBuilder);
    }

    @GetMapping("depoimentos")
    public ResponseEntity listarDepoimentos(){
        return service.listarDepoimentos();
    }

    @GetMapping("depoimentos-home")
    public ResponseEntity listarTresDepoimentos() throws Exception {
        return service.listarTresDepoimentos();
    }

    @PutMapping("depoimentos")
    @Transactional
    public ResponseEntity atualizarDepoimento(@RequestBody DadosAtualizarDepoimento dados){
        return service.atualizarDepoimento(dados);
    }

    @DeleteMapping("depoimentos/{id}")
    @Transactional
    public ResponseEntity deletarDepoimento(@PathVariable Long id) {
        return service.deletarDepoimento(id);
    }
}
