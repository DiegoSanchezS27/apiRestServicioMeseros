package com.mx.ApiRestServicioMeseros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestServicioMeseros.dao.MeserosDao;
import com.mx.ApiRestServicioMeseros.model.Meseros;

@Service
public class MeserosServImp {

	@Autowired
	MeserosDao meseroDao;

	@Transactional(readOnly = true)
	public List<Meseros> listar() {
		return meseroDao.findAll();
	}

	@Transactional
	public String guardar(Meseros mesero) {

		String respuesta = "guardado";
		for (Meseros v : meseroDao.findAll()) {
			if (v.getIdMesero().equals(mesero.getIdMesero())) {
				respuesta = "Estas repitiendo el ID del mesero";
				break;
			}
			if (v.getNombre().equals(mesero.getNombre()) && v.getApellidoPaterno().equals(mesero.getApellidoPaterno())
					&& v.getApellidoMaterno().equals(mesero.getApellidoMaterno())) {
				respuesta = "Estas repitiendo el nombre completo";
				break;
			}

		}
		meseroDao.save(mesero);
		return respuesta;
	}

	@Transactional(readOnly = true)
	public Meseros buscar(long idMesero) {
		return meseroDao.findById(idMesero).orElse(null);
	}

	@Transactional
	public boolean editar(Meseros mesero) {
		if (meseroDao.existsById(mesero.getIdMesero())) {
			meseroDao.save(mesero);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean eliminar(long idMesero) {
		if (meseroDao.existsById(idMesero)) {
			meseroDao.deleteById(idMesero);
			return true;
		}
		return false;
	}
}
