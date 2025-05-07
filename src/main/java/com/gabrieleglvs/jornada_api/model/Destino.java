package com.gabrieleglvs.jornada_api.model;

import com.gabrieleglvs.jornada_api.dto.destino.DadosAtualizarDestino;
import com.gabrieleglvs.jornada_api.dto.destino.DadosCadastrarDestino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Destino")
@Table(name = "destinos")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foto;
    private String nome;
    private Double preco;

    public Destino(DadosCadastrarDestino dados) {
        this.foto = dados.foto();
        this.nome = dados.nome();
        this.preco = dados.preco();
    }

    public void atualizarDestino(DadosAtualizarDestino dados) {
        if(dados.foto() != null) {
            this.foto = dados.foto();
        }
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.preco() != null) {
            this.preco = dados.preco();
        }
    }
}
