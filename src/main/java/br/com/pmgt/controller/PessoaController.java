package br.com.pmgt.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.pmgt.model.PessoaModel;
import br.com.pmgt.repository.PessoaRepository;
import br.com.pmgt.uteis.Uteis;

@Named(value = "pessoaController")
@ViewScoped
public class PessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject
	transient private PessoaRepository pessoaRepository;

	@Inject
	UsuarioController usuarioController;

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
	
	@PostConstruct
	public void init() {
		pessoas = pessoaRepository.listar();
	}

	public void excluir(PessoaModel pessoaModel) {
		pessoaRepository.excluir(pessoaModel.getCodigo());
		pessoas.remove(pessoaModel);
		Uteis.MensagemInfo("Registro excluído com sucesso!");
	}

	public void salvar() {
		String operacao = "incluir";
		if (pessoaModel.getCodigo() == null) {
			pessoaRepository.incluir(pessoaModel);
			pessoaModel = new PessoaModel();
		} else {
			operacao = "alterar";
			pessoaRepository.alterar(pessoaModel);
		}
		init();
		PrimeFaces.current().executeScript("PF('dialog-modal-" + operacao + "').hide();");
		Uteis.MensagemInfo("Registro salvo com sucesso!");
	}
	

}