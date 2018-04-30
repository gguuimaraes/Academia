package br.com.pmgt.model;

import java.util.Date;

import br.com.pmgt.uteis.Sexo;

public class EquipamentoModel {

	private Integer codigo;
	private String descricao;
	private Date dataAquisicao;
	private Float valorCompra;
	private Date dataCadastro;
	private UsuarioModel usuarioCadastro;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Float getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Float valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public UsuarioModel getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(UsuarioModel usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
}