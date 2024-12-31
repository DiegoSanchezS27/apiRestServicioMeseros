package com.mx.ApiRestServicioMeseros.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestServicioMeseros.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long> {

}
