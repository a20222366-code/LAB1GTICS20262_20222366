package com.example.LAB1GTICS20262_20222366.repository;

import com.example.LAB1GTICS20262_20222366.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByCodigoContainingIgnoreCase(String codigo);

}