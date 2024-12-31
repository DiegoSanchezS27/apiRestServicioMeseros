package com.mx.ApiRestServicioMeseros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestServicioMeseros.model.Mesas;
import com.mx.ApiRestServicioMeseros.service.MesasServImp;

@RestController
@RequestMapping(path = "MesasWs")
public class MesasWs {

	@Autowired
	MesasServImp mesasImp;

	// http://localhost:9000/MesasWs/listar
	@GetMapping(path = "listar")
	public List<Mesas> listar() {
		return mesasImp.listar();
	}

	// http://localhost:9000/MesasWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas Mesa) {
		String response = mesasImp.guardar(Mesa);
		if (response.equals("El id de la mesa ya existe"))
			return new ResponseEntity<>("El id de la mesa ya existe, ingresa otro", HttpStatus.OK);
		else if (response.equals("Ya existe ese numero de mesa"))
			return new ResponseEntity<>("Ese numero de mesa ya existe, ingresa otro", HttpStatus.OK);
		else if (response.equals("Ese ID de mesero no existe"))
			return new ResponseEntity<>("Ese id de mesero no existe, ingresa uno existente", HttpStatus.OK);
		else
			return new ResponseEntity<>(Mesa, HttpStatus.CREATED);
	}

	// http://localhost:9000/MesasWs/buscar
	@PostMapping(path = "buscar")
	public Mesas buscar(@RequestBody Mesas Mesa) {
		return mesasImp.buscar(Mesa.getIdMesa());
	}

	// http://localhost:9000/MesasWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Mesas Mesa) {
		boolean response = mesasImp.editar(Mesa);
		if (response == false)
			return new ResponseEntity<>("El id no se encuentra", HttpStatus.OK);
		else
			return new ResponseEntity<>(Mesa, HttpStatus.CREATED);
	}

	// http://localhost:9000/MesasWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Mesas Mesa) {
		boolean response = mesasImp.eliminar(Mesa.getIdMesa());
		if (response) {
			return new ResponseEntity<>("La mesa fue eliminada", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id no existe", HttpStatus.NOT_FOUND);
		}
	}
}
