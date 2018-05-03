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

	@Produces
	private List<PessoaModel> pessoasFiltradas;

	@Inject
	transient private PessoaModel pessoaModel;

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

	public List<PessoaModel> getPessoasFiltradas() {
		return pessoasFiltradas;
	}

	public void setPessoasFiltradas(List<PessoaModel> pessoasFiltradas) {
		this.pessoasFiltradas = pessoasFiltradas;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
		PrimeFaces.current().executeScript("PF('dialog-modal-alterar').show();");
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
		String operacao = pessoaModel.getCodigo() == null ? "incluir" : "alterar";

		if (pessoaModel.getCodigo() == null) {
			pessoaRepository.incluir(pessoaModel);
		} else {
			// ************** Inicio Usuario **************
			boolean excluirUsuario = false;
			UsuarioModel oldUsuarioModel = pessoaRepository.consultarModel(pessoaModel.getCodigo()).getUsuarioModel();
			if (pessoaModel.getUsuarioModel() != null) {
				if (oldUsuarioModel == null) {
					usuarioRepository.incluir(pessoaModel.getUsuarioModel());

				} else {
					if (pessoaModel.getUsuarioModel().getCodigo() == null)
						pessoaModel.getUsuarioModel().setCodigo(oldUsuarioModel.getCodigo());
					usuarioRepository.alterar(pessoaModel.getUsuarioModel());
				}
			} else {
				if (oldUsuarioModel != null) {
					excluirUsuario = true;
				}
			}
			// ************** Fim Usuario **************

			pessoaRepository.alterar(pessoaModel);

			// ************** Inicio Usuario **************
			if (excluirUsuario) {
				usuarioRepository.excluir(oldUsuarioModel.getCodigo());
			}
			// ************** Fim Usuario **************
		}

		PrimeFaces.current().executeScript("PF('dialog-modal-" + operacao + "').hide();");
		Uteis.MensagemInfo("Registro salvo com sucesso!");
		init();
	}

	public void btnIncluirUsuario() {
		pessoaModel.setUsuarioModel(new UsuarioModel());

	}

	public void btnExcluirUsuario() {
		pessoaModel.setUsuarioModel(null);

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

	public void btnIncluir() {
		pessoaModel = new PessoaModel();
		PrimeFaces.current().executeScript("PF('dialog-modal-incluir').show();");
	}
}