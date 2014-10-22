package com.example.conversor;

public class Valor {
	private String sigla;
	private String moeda;
	
	public Valor(String s, String m){
		this.sigla = s;
		this.moeda = m;
	}
	
	public String getSigla(){
		return this.sigla;
	}
	
	public String getMoeda(){
		return this.moeda;
	}
}
