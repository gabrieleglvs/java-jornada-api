package com.gabrieleglvs.jornada_api.dto;

import com.gabrieleglvs.jornada_api.model.Destino;

public record DadosDetalhamentoDestino(String foto,
                                      String nome,
                                      double preco) {
    public DadosDetalhamentoDestino(Destino destino) {
        this(destino.getFoto(), destino.getNome(), destino.getPreco());

    }
}
