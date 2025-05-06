package com.gabrieleglvs.jornada_api.service;

import com.gabrieleglvs.jornada_api.dto.DadosDetalhamentoDestino;
import com.gabrieleglvs.jornada_api.model.Destino;
import com.gabrieleglvs.jornada_api.repository.IDestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinoService {
    @Autowired
    private IDestinoRepository iDestinoRepository;

    public ResponseEntity listarDestinos() {
        List<Destino> destinoList = iDestinoRepository.findAll();
        var destinos = destinoList.stream()
                .map(destino -> new DadosDetalhamentoDestino(destino))
                .collect(Collectors.toList());
        return ResponseEntity.ok(destinos);
    }
}
