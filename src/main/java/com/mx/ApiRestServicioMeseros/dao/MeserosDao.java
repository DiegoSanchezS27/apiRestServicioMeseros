package com.mx.ApiRestServicioMeseros.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestServicioMeseros.model.Meseros;

public interface MeserosDao extends JpaRepository<Meseros, Long> {

}
