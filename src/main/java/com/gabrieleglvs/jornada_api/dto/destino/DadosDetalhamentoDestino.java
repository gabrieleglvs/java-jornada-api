package com.gabrieleglvs.jornada_api.dto.destino;

import com.gabrieleglvs.jornada_api.model.Destino;

public record DadosDetalhamentoDestino(String foto,
                                      String nome,
                                      Double preco) {
    public DadosDetalhamentoDestino(Destino destino) {
        this(destino.getFoto(), destino.getNome(), destino.getPreco());

    }
}
