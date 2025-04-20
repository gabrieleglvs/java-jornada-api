package com.gabrieleglvs.jornada_api.controller;

import com.gabrieleglvs.jornada_api.dto.depoimento.DadosCadastrarDepoimento;
import com.gabrieleglvs.jornada_api.service.DepoimentoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("depoimentos")
public class DepoimentoController {
    @Autowired
    private DepoimentoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastrarDepoimento dados,
                                    UriComponentsBuilder uriBuilder){
        return service.cadastrarDepoimento(dados, uriBuilder);
    }
}
