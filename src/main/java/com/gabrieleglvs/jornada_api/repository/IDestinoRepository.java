package com.gabrieleglvs.jornada_api.repository;

import com.gabrieleglvs.jornada_api.dto.destino.DadosDetalhamentoDestino;
import com.gabrieleglvs.jornada_api.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDestinoRepository extends JpaRepository<Destino, Long> {
    List<Destino> findAllByNome(String nome);
}
