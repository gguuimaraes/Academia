package br.com.pmgt.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.pmgt.model.EquipamentoModel;
import br.com.pmgt.repository.EquipamentoRepository;

@Named(value = "equipamentoController")
@ViewScoped
public class EquipamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private EquipamentoModel equipamentoModel;

	@Produces
	private List<EquipamentoModel> equipamentos;

	@Inject
	transient private EquipamentoRepository equipamentoRepository;

	@Inject
	UsuarioController usuarioController;

	public List<EquipamentoModel> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<EquipamentoModel> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public EquipamentoModel getEquipamentoModel() {
		return equipamentoModel;
	}

	public void setEquipamentoModel(EquipamentoModel equipamentoModel) {
		this.equipamentoModel = equipamentoModel;
	}
	
	@PostConstruct
	public void init() {
		equipamentos = equipamentoRepository.listar();
	}

	public void excluir(EquipamentoModel equipamentoModel) {
		equipamentoRepository.excluir(equipamentoModel.getCodigo());
		equipamentos.remove(equipamentoModel);
	}

	public void salvar() {
		String operacao = "incluir";
		if (equipamentoModel.getCodigo() == null) {
			equipamentoModel.setUsuarioCadastro(usuarioController.GetUsuarioSession());
			equipamentoRepository.incluir(equipamentoModel);
			equipamentoModel = new EquipamentoModel();	
		} else {
			operacao = "alterar";
			equipamentoRepository.alterar(equipamentoModel);
		}
		init();
		PrimeFaces.current().resetInputs("form-cadastro");
		PrimeFaces.current().executeScript("PF('dialog-modal-" + operacao + "').hide();");
	}
	

}