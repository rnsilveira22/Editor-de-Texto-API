
package com.digitro.model;

import java.util.Date;

//import javax.json.bind.annotation.JsonbDateFormat;

public class Documento {

	private Long id;

	private String titulo;
	private String corpo;
	//@JsonbDateFormat("yyyy-MM-dd")
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
