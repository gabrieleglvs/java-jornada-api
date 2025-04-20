package com.gabrieleglvs.jornada_api.dto.depoimento;

import com.gabrieleglvs.jornada_api.model.Depoimento;

public record DadosDetalhamentoDepoimento(String foto,
                                          String depoimento,
                                          String nome) {
    public DadosDetalhamentoDepoimento(Depoimento depoimento){
        this(depoimento.getFoto(), depoimento.getDepoimento(), depoimento.getNome());
    }
}
