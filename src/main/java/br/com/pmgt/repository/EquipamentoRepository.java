package br.com.pmgt.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pmgt.model.EquipamentoModel;
import br.com.pmgt.model.UsuarioModel;
import br.com.pmgt.repository.entity.Equipamento;
import br.com.pmgt.repository.entity.Usuario;
import br.com.pmgt.uteis.Uteis;

public class EquipamentoRepository {

	@Inject
	Equipamento equipamento;

	EntityManager entityManager;

	public void incluir(EquipamentoModel equipamentoModel) {
		entityManager = Uteis.JpaEntityManager();
		equipamento = new Equipamento();
		equipamento.setDescricao(equipamentoModel.getDescricao());
		equipamento.setDataAquisicao(equipamentoModel.getDataAquisicao());
		equipamento.setValorCompra(equipamentoModel.getValorCompra());
		equipamento.setDataCadastro(new Date());		
		equipamento.setUsuarioCadastro(entityManager.find(Usuario.class, equipamentoModel.getUsuarioCadastro().getCodigo()));
		entityManager.persist(equipamento);
	}

	public List<EquipamentoModel> listar() {
		entityManager = Uteis.JpaEntityManager();
		
		List<EquipamentoModel> equipamentosModel = new ArrayList<EquipamentoModel>();
		Query query = entityManager.createNamedQuery("Equipamento.findAll");

		@SuppressWarnings("unchecked")
		Collection<Equipamento> equipamentos = (Collection<Equipamento>) query.getResultList();

		EquipamentoModel equipamentoModel = null;
		for (Equipamento equipamento : equipamentos) {
			equipamentoModel = new EquipamentoModel();
			equipamentoModel.setCodigo(equipamento.getCodigo());
			equipamentoModel.setDescricao(equipamento.getDescricao());
			equipamentoModel.setDataAquisicao(equipamento.getDataAquisicao());
			equipamentoModel.setValorCompra(equipamento.getValorCompra());
			equipamentoModel.setDataCadastro(equipamento.getDataCadastro());		
			equipamentoModel.setUsuarioCadastro(new UsuarioModel(equipamento.getUsuarioCadastro().getNome()));
			equipamentosModel.add(equipamentoModel);
		}
		return equipamentosModel;
	}

	private Equipamento consultar(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		
		return entityManager.find(Equipamento.class, codigo);
	}

	public void alterar(EquipamentoModel equipamentoModel) {
		entityManager = Uteis.JpaEntityManager();
		
		Equipamento equipamento = consultar(equipamentoModel.getCodigo());
		equipamento.setDescricao(equipamentoModel.getDescricao());
		equipamento.setDataAquisicao(equipamentoModel.getDataAquisicao());
		equipamento.setValorCompra(equipamentoModel.getValorCompra());
		entityManager.merge(equipamento);
	}

	public void excluir(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		
		entityManager.remove(consultar(codigo));
	}
}