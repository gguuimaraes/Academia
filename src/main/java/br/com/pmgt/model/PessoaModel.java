package br.com.pmgt.model;

import java.util.Date;

import br.com.pmgt.uteis.Sexo;

public class PessoaModel {

	private Integer codigo;
	private String nome;
	private Sexo sexo = Sexo.MASCULINO;
	private Date dataCadastro;
	private String email;
	private String endereco;
	private UsuarioModel usuarioCadastro;

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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public UsuarioModel getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(UsuarioModel usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
}