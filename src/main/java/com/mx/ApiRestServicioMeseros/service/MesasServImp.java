package com.mx.ApiRestServicioMeseros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestServicioMeseros.dao.MesasDao;
import com.mx.ApiRestServicioMeseros.dao.MeserosDao;
import com.mx.ApiRestServicioMeseros.model.Mesas;
import com.mx.ApiRestServicioMeseros.model.Meseros;

@Service
public class MesasServImp {

	@Autowired
	MesasDao mesaDao;

	@Autowired
	MeserosDao meseroDao;

	@Transactional(readOnly = true)
	public List<Mesas> listar() {
		return mesaDao.findAll();
	}

	// Validar que el idMesa y el numMesa no se repita
	// Validar que el idMesero exista en la bd
	@Transactional
	public String guardar(Mesas mesa) {
		boolean banderaMesero = false;
		boolean banderaMesa = false;
		String respuesta = "guardado";
		for (Meseros m : meseroDao.findAll()) {
			if (m.getIdMesero().equals(mesa.getMesero().getIdMesero())) {
				banderaMesero = true;
				for (Mesas v : mesaDao.findAll()) {
					if (v.getIdMesa().equals(mesa.getIdMesa())) {
						banderaMesero = true;
						respuesta = "El id de la mesa ya existe";
						break;
					} else if (v.getNumMesa().equals(mesa.getNumMesa())) {
						banderaMesa = true;
						respuesta = "Ya existe ese numero de mesa";
						break;
					}
				}
				break;
			}
		}
		if (banderaMesero == false) {
			banderaMesa = true;
			respuesta = "Ese ID de mesero no existe";
		} else if (banderaMesa == false) {
			mesaDao.save(mesa);
		}
		return respuesta;
	}

	@Transactional(readOnly = true)
	public Mesas buscar(long idMesa) {
		return mesaDao.findById(idMesa).orElse(null);
	}

	@Transactional
	public boolean editar(Mesas Mesa) {
		boolean bandera = false;
		for (Mesas v : mesaDao.findAll()) {
			if (v.getIdMesa().equals(Mesa.getIdMesa())) {
				if (v.getMesero().getIdMesero().equals(Mesa.getMesero().getIdMesero())) {
					bandera = true;
					break;
				}
			}
		}
		if (bandera == true) {
			mesaDao.save(Mesa);
		}
		return bandera;
	}

	@Transactional
	public boolean eliminar(long idMesa) {
		if (mesaDao.existsById(idMesa)) {
			mesaDao.deleteById(idMesa);
			return true;
		}
		return false;
	}
}
