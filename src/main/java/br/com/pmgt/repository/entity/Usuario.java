package br.com.pmgt.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author gynadm06
 *
 */
@Entity
@Table(name = "usuario")

@NamedQueries({
	
		@NamedQuery(name = "Usuario.findUser", query = "SELECT u FROM Usuario u WHERE u.nome = :nome AND u.senha = :senha"),
		@NamedQuery(name = "Usuario.findUserByName", query = "SELECT u FROM Usuario u WHERE u.nome = :nome")

})

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "nome", unique = true)
	private String nome;

	@Column(name = "senha")
	private String senha;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}