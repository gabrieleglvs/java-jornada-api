package com.gabrieleglvs.jornada_api.controller;

import com.gabrieleglvs.jornada_api.dto.destino.DadosAtualizarDestino;
import com.gabrieleglvs.jornada_api.dto.destino.DadosCadastrarDestino;
import com.gabrieleglvs.jornada_api.service.DestinoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("")
public class DestinoController {

    @Autowired
    private DestinoService service;

    @PostMapping("destinos")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastrarDestino dados,
                                    UriComponentsBuilder uriBuilder){
        return service.cadastrarDestino(dados, uriBuilder);
    }

    @GetMapping("destinos/home")
    public ResponseEntity listarDestinos(){
        return service.listarDestinos();
    }

    @GetMapping("destinos")
    public ResponseEntity listarDestinoPorNome(@RequestParam String nome) {
        return service.listarDestinoPorNome(nome);
    }

    @PutMapping("destinos")
    @Transactional
    public ResponseEntity atualizarDestino(@RequestBody DadosAtualizarDestino dados) {
        return service.atualizarDestino(dados);
    }

    @DeleteMapping("destinos/{id}")
    @Transactional
    public ResponseEntity deletarDestino(@PathVariable Long id) {
        return service.deletarDestino(id);
    }
}
