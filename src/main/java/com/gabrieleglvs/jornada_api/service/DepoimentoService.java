package com.gabrieleglvs.jornada_api.service;

import com.gabrieleglvs.jornada_api.dto.depoimento.DadosAtualizarDepoimento;
import com.gabrieleglvs.jornada_api.dto.depoimento.DadosCadastrarDepoimento;
import com.gabrieleglvs.jornada_api.dto.depoimento.DadosDetalhamentoDepoimento;
import com.gabrieleglvs.jornada_api.model.Depoimento;
import com.gabrieleglvs.jornada_api.repository.IDepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepoimentoService {
    @Autowired
    private IDepoimentoRepository iDepoimentoRepository;

    public ResponseEntity cadastrarDepoimento(DadosCadastrarDepoimento dados,
                                              UriComponentsBuilder uriBuilder) {
        Depoimento depoimento = new Depoimento(dados);
        iDepoimentoRepository.save(depoimento);

        var uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(depoimento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoDepoimento(depoimento));
    }

    public ResponseEntity listarDepoimentos() {
        List<Depoimento> depoimentoList = iDepoimentoRepository.findAll();
        var depoimentos = depoimentoList.stream()
                .map(depoimento -> new DadosDetalhamentoDepoimento(depoimento))
                .collect(Collectors.toList());
        return ResponseEntity.ok(depoimentos);
    }

    public ResponseEntity atualizarDepoimento(DadosAtualizarDepoimento dados) {
        var depoimento = iDepoimentoRepository.getReferenceById(dados.id());
        depoimento.atualizarDepoimento(dados);
        return ResponseEntity.ok(new DadosDetalhamentoDepoimento(depoimento));
    }
}
