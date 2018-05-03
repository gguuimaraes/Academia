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

import br.com.pmgt.model.EquipamentoModel;
import br.com.pmgt.repository.EquipamentoRepository;
import br.com.pmgt.uteis.Uteis;

@Named(value = "equipamentoController")
@ViewScoped
public class EquipamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private List<EquipamentoModel> equipamentos;

	@Produces
	private List<EquipamentoModel> equipamentosFiltrados;

	@Inject
	transient private EquipamentoModel equipamentoModel;

	@Inject
	transient private EquipamentoRepository equipamentoRepository;

	public List<EquipamentoModel> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<EquipamentoModel> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<EquipamentoModel> getEquipamentosFiltrados() {
		return equipamentosFiltrados;
	}

	public void setEquipamentosFiltrados(List<EquipamentoModel> equipamentosFiltrados) {
		this.equipamentosFiltrados = equipamentosFiltrados;
	}

	public EquipamentoModel getEquipamentoModel() {
		return equipamentoModel;
	}

	public void setEquipamentoModel(EquipamentoModel equipamentoModel) {
		this.equipamentoModel = equipamentoModel;
		PrimeFaces.current().executeScript("PF('dialog-modal-alterar').show();");
	}

	@PostConstruct
	public void init() {
		equipamentos = equipamentoRepository.listar();
	}

	public void excluir(EquipamentoModel equipamentoModel) {
		equipamentoRepository.excluir(equipamentoModel.getCodigo());
		equipamentos.remove(equipamentoModel);
		Uteis.MensagemInfo("Registro excluído com sucesso!");
	}

	public void salvar() {
		String operacao = equipamentoModel.getCodigo() == null ? "incluir" : "alterar";

		if (equipamentoModel.getCodigo() == null) {
			equipamentoRepository.incluir(equipamentoModel);
		} else {
			equipamentoRepository.alterar(equipamentoModel);
		}

		PrimeFaces.current().executeScript("PF('dialog-modal-" + operacao + "').hide();");
		Uteis.MensagemInfo("Registro salvo com sucesso!");
		init();
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
		equipamentoModel = new EquipamentoModel();
		PrimeFaces.current().executeScript("PF('dialog-modal-incluir').show();");
	}
}