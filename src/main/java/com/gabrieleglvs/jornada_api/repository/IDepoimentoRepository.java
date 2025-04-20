package com.gabrieleglvs.jornada_api.repository;

import com.gabrieleglvs.jornada_api.model.Depoimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepoimentoRepository extends JpaRepository<Depoimento, Long> {
}
