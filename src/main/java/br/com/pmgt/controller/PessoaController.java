package br.com.pmgt.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;

import br.com.pmgt.model.PessoaModel;
import br.com.pmgt.model.UsuarioModel;
import br.com.pmgt.repository.PessoaRepository;
import br.com.pmgt.repository.UsuarioRepository;
import br.com.pmgt.uteis.Uteis;

@Named(value = "pessoaController")
@ViewScoped
public class PessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject
	transient private PessoaModel pessoaModel;


	
	public boolean isExcluirUsuario() {
		return excluirUsuario;
	}

	public void setExcluirUsuario(boolean excluirUsuario) {
		this.excluirUsuario = excluirUsuario;
	}

	private boolean excluirUsuario = false;

	@Inject
	transient private PessoaRepository pessoaRepository;

	@Inject
	transient private UsuarioRepository usuarioRepository;

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
		Uteis.MensagemInfo("Registro exclu�do com sucesso!");
	}

	public void salvar() {
		if (pessoaModel.getUsuarioModel() != null) {
			if (pessoaModel.getUsuarioModel().getCodigo() == null) {
				// significa que incluiu o usuario
				usuarioRepository.incluir(pessoaModel.getUsuarioModel());
			} else {
				// significa que alterou o usuario
				usuarioRepository.alterar(pessoaModel.getUsuarioModel());
			}
		}

		String operacao = "incluir";
		if (pessoaModel.getCodigo() == null) {
			pessoaRepository.incluir(pessoaModel);
			pessoaModel = new PessoaModel();
		} else {
			operacao = "alterar";
			UsuarioModel usuarioModel = pessoaModel.getUsuarioModel();
			if (excluirUsuario) {
				pessoaModel.setUsuarioModel(null);
			}
			pessoaRepository.alterar(pessoaModel);

			if (excluirUsuario) {
				usuarioRepository.excluir(usuarioModel.getCodigo());
			}
		}
		init();
		PrimeFaces.current().executeScript("PF('dialog-modal-" + operacao + "').hide();");
		Uteis.MensagemInfo("Registro salvo com sucesso!");
	}

	public void incluirUsuario() {
		pessoaModel.setUsuarioModel(new UsuarioModel());
		excluirUsuario = false;
	}

	public void excluirUsuario() {
		excluirUsuario = true;
	}

	public boolean filterByDate(Object value, Object filter, Locale locale) {
		if (filter == null) {
			return true;
		}

		if (value == null) {
			return false;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format((Date) value).equals(simpleDateFormat.format((Date) filter));
	}
}