package com.mycompany.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;

@ManagedBean
@ApplicationScoped
public class NombresBean {

	private String nombre;
	private List<String> nombres = new ArrayList<>();
	
	private HtmlInputText inputNombre;
	private HtmlCommandButton botonAgregar;
	
	
	public String agregar(){
		
		this.nombres.add(nombre);
		
		if(this.nombres.size()>4){
			this.inputNombre.setDisabled(true);
			this.botonAgregar.setDisabled(true);
			this.botonAgregar.setValue("Muchos nombres asignados");
			//return "hola";  //busque la vista
			return "hola?faces-redirect=true";
		}
		
		return null; //se quede en la misma pagina
		
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getNombres() {
		return nombres;
	}
	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}


	public HtmlInputText getInputNombre() {
		return inputNombre;
	}


	public void setInputNombre(HtmlInputText inputNombre) {
		this.inputNombre = inputNombre;
	}


	public HtmlCommandButton getBotonAgregar() {
		return botonAgregar;
	}


	public void setBotonAgregar(HtmlCommandButton botonAgregar) {
		this.botonAgregar = botonAgregar;
	}
	
	
	
}