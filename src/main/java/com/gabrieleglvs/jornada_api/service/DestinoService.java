package com.gabrieleglvs.jornada_api.service;

import com.gabrieleglvs.jornada_api.dto.destino.DadosAtualizarDestino;
import com.gabrieleglvs.jornada_api.dto.destino.DadosCadastrarDestino;
import com.gabrieleglvs.jornada_api.dto.destino.DadosDetalhamentoDestino;
import com.gabrieleglvs.jornada_api.model.Destino;
import com.gabrieleglvs.jornada_api.repository.IDestinoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinoService {
    @Autowired
    private IDestinoRepository iDestinoRepository;

    public ResponseEntity cadastrarDestino(DadosCadastrarDestino dados, UriComponentsBuilder uriBuilder) {
        Destino destino = new Destino(dados);
        iDestinoRepository.save(destino);

        var uri = uriBuilder.path("/destinos/{id}").buildAndExpand(destino.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoDestino(destino));
    }

    public ResponseEntity listarDestinos() {
        List<Destino> destinoList = iDestinoRepository.findAll();
        var destinos = destinoList.stream()
                .map(destino -> new DadosDetalhamentoDestino(destino))
                .collect(Collectors.toList());
        return ResponseEntity.ok(destinos);
    }

    public ResponseEntity atualizarDestino(DadosAtualizarDestino dados) {
        var destino = iDestinoRepository.getReferenceById(dados.id());
        destino.atualizarDestino(dados);

        return ResponseEntity.ok(new DadosDetalhamentoDestino(destino));
    }

    public ResponseEntity deletarDestino(Long id) {
        var destino = iDestinoRepository.findById(id);
        if(destino.isEmpty()) {
            throw new EntityNotFoundException("Destino não encontrado!");
        }
        iDestinoRepository.delete(destino.get());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity listarDestinoPorNome(String nome) {
        var destinos = iDestinoRepository.findAllByNome(nome);
        List<DadosDetalhamentoDestino> listaDestinos = new ArrayList<>();

        if(!destinos.isEmpty()) {
             listaDestinos = destinos.stream()
                    .map(destino -> new DadosDetalhamentoDestino(destino))
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Nenhum destino foi encontrado!");
        }
        return ResponseEntity.ok(listaDestinos);
    }
}
