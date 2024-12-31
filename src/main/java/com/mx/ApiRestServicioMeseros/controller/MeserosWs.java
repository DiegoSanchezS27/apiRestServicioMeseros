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

import com.mx.ApiRestServicioMeseros.model.Meseros;
import com.mx.ApiRestServicioMeseros.service.MeserosServImp;

@RestController
@RequestMapping(path = "MeserosWs")
public class MeserosWs {

	@Autowired
	MeserosServImp meserosImp;

	// http://localhost:9000/MeserosWs/listar
	@GetMapping(path = "listar")
	public List<Meseros> listar() {
		return meserosImp.listar();
	}

	// http://localhost:9000/MeserosWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Meseros mesero) {
		String response = meserosImp.guardar(mesero);
		if (response.equals("Estas repitiendo el ID del mesero"))
			return new ResponseEntity<>("El ID del mesero esta repetio, ingresa otro", HttpStatus.OK);
		else if (response.equals("Estas repitiendo el nombre completo"))
			return new ResponseEntity<>("Estas ingresando un nombre completo repetido prueba con otro", HttpStatus.OK);
		else
			return new ResponseEntity<>(mesero, HttpStatus.CREATED);
	}

	// http://localhost:9000/MeserosWs/buscar
	@PostMapping(path = "buscar")
	public Meseros buscar(@RequestBody Meseros mesero) {
		return meserosImp.buscar(mesero.getIdMesero());
	}

	// http://localhost:9000/MeserosWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Meseros mesero) {
		boolean response = meserosImp.editar(mesero);
		if (response == true) {
			return new ResponseEntity<>(mesero, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id no existe", HttpStatus.NOT_FOUND);
		}
	}

	// http://localhost:9000/MeserosWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Meseros mesero) {
		boolean response = meserosImp.eliminar(mesero.getIdMesero());
		if (response) {
			return new ResponseEntity<>("El mesero fue eliminado con exito", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id no existe", HttpStatus.NOT_FOUND);
		}
	}

}
