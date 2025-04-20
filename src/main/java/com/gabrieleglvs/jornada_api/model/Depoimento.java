package com.gabrieleglvs.jornada_api.model;

import com.gabrieleglvs.jornada_api.dto.depoimento.DadosCadastrarDepoimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Depoimento")
@Table(name = "depoimentos")
public class Depoimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foto;
    private String depoimento;
    private String nome;

    public Depoimento(DadosCadastrarDepoimento dados) {
        this.foto = dados.foto();
        this.depoimento = dados.depoimento();
        this.nome = dados.nome();
    }
}
