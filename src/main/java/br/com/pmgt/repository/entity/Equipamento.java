package br.com.pmgt.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "equipamento")

@NamedQueries({

		@NamedQuery(name = "Equipamento.findAll", query = "SELECT e FROM Equipamento e")

})
public class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "dataAquisicao")
	@Temporal(TemporalType.DATE)
	private Date dataAquisicao;

	@Column(name = "valorCompra")
	private Float valorCompra;

	@Column(name = "dataCadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

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
	

}