package com.gabrieleglvs.jornada_api.service;

import com.gabrieleglvs.jornada_api.dto.depoimento.DadosAtualizarDepoimento;
import com.gabrieleglvs.jornada_api.dto.depoimento.DadosCadastrarDepoimento;
import com.gabrieleglvs.jornada_api.dto.depoimento.DadosDetalhamentoDepoimento;
import com.gabrieleglvs.jornada_api.model.Depoimento;
import com.gabrieleglvs.jornada_api.repository.IDepoimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public ResponseEntity listarTresDepoimentos() throws Exception {
        List<Depoimento> depoimentoList = iDepoimentoRepository.findAll();
        var depoimentos = depoimentoList.stream()
                .map(depoimento -> new DadosDetalhamentoDepoimento(depoimento))
                .collect(Collectors.toList());

        List<DadosDetalhamentoDepoimento> tresDepoimentos = new ArrayList<>();

        if(!depoimentos.isEmpty() && depoimentos.size() >= 3){
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                DadosDetalhamentoDepoimento depoimento = depoimentos.get(
                        random.nextInt(depoimentos.size())
                );
                tresDepoimentos.add(depoimento);
                depoimentos.remove(depoimento);
            }
        } else {
            throw new Exception("Não há depoimentos suficientes.");
        }

        return ResponseEntity.ok(tresDepoimentos);
    }

    public ResponseEntity deletarDepoimento(Long id) {
        var depoimento = iDepoimentoRepository.findById(id);
        if(depoimento.isEmpty()){
            throw new EntityNotFoundException("Depoimento não encontrado!");
        }
        iDepoimentoRepository.delete(depoimento.get());
        return ResponseEntity.noContent().build();
    }
}
