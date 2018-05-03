package br.com.pmgt.model;

import java.util.Date;

import br.com.pmgt.uteis.Sexo;

public class PessoaModel {

	private Integer codigo;
	private String nome;
	private Sexo sexo = Sexo.MASCULINO;
	private String email;
	private String endereco;
	private UsuarioModel usuarioModel = null;
	private Date dataCadastro;

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

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "PessoaModel [codigo=" + codigo + ", nome=" + nome + ", sexo=" + sexo + ", email=" + email
				+ ", endereco=" + endereco + ", usuarioModel=" + usuarioModel + ", dataCadastro=" + dataCadastro + "]";
	}

}