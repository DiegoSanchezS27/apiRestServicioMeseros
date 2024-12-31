package com.mx.ApiRestServicioMeseros.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *  CREATE TABLE MESAS(
    ID NUMBER PRIMARY KEY,
    NUM_MESA NUMBER NOT NULL,
    NUM_SILLAS NUMBER NOT NULL,
    ID_MESERO NUMBER NOT NULL,
    FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
    );
 */

@Entity
@Table(name = "MESAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mesas {

	@Id
	@Column(name = "ID")
	private Long idMesa;

	@Column(name = "NUM_MESA")
	private Long numMesa;

	@Column(name = "NUM_SILLAS")
	private Long numSillas;

	// Cardinalidad-- muchos a uno, muchas mesas pertenecen a un mesero
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MESERO")
	Meseros mesero; // Este debe coincidir con la variable del mappenBy = "marca".

	// Fetch---indicamos como debe ser cargada la entidad
	// FetchType.EAGER---indicamos que la relacion debe de ser cargada al momento
}
