package br.com.pmgt.uteis;

public enum Sexo {
	MASCULINO,
	FEMININO;
	
	public Sexo[] getSexos() {
		return values();
	}
	
	public String toString() {
		String old = super.toString();
		return old.substring(0, 1) + old.substring(1).toLowerCase();
	}
}
