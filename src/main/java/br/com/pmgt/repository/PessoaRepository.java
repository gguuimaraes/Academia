package br.com.pmgt.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pmgt.model.PessoaModel;
import br.com.pmgt.model.UsuarioModel;
import br.com.pmgt.repository.entity.Pessoa;
import br.com.pmgt.repository.entity.Usuario;
import br.com.pmgt.uteis.Uteis;

public class PessoaRepository {

	@Inject
	Pessoa pessoa;

	EntityManager entityManager;

	public void incluir(PessoaModel pessoaModel) {
		entityManager = Uteis.JpaEntityManager();
		
		pessoa = new Pessoa();
		pessoa.setNome(pessoaModel.getNome());
		pessoa.setSexo(pessoaModel.getSexo());
		pessoa.setEmail(pessoaModel.getEmail());
		pessoa.setEndereco(pessoaModel.getEndereco());
		pessoa.setDataCadastro(new Date());
		pessoa.setUsuarioCadastro(entityManager.find(Usuario.class, pessoaModel.getUsuarioCadastro().getCodigo()));
		entityManager.persist(pessoa);
	}

	public List<PessoaModel> listar() {
		entityManager = Uteis.JpaEntityManager();
		
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
		Query query = entityManager.createNamedQuery("Pessoa.findAll");
		@SuppressWarnings("unchecked")
		Collection<Pessoa> pessoas = (Collection<Pessoa>) query.getResultList();
		PessoaModel pessoaModel = null;
		for (Pessoa pessoa : pessoas) {
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoa.getCodigo());
			pessoaModel.setDataCadastro(pessoa.getDataCadastro());
			pessoaModel.setEmail(pessoa.getEmail());
			pessoaModel.setEndereco(pessoa.getEndereco());
			pessoaModel.setNome(pessoa.getNome());
			pessoaModel.setSexo(pessoa.getSexo());
			pessoaModel.setUsuarioCadastro(new UsuarioModel(pessoa.getUsuarioCadastro().getNome()));
			pessoasModel.add(pessoaModel);
		}
		return pessoasModel;
	}

	private Pessoa consultar(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		
		return entityManager.find(Pessoa.class, codigo);
	}

	public void alterar(PessoaModel pessoaModel) {
		entityManager = Uteis.JpaEntityManager();
		
		Pessoa pessoaEntity = consultar(pessoaModel.getCodigo());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		entityManager.merge(pessoaEntity);
	}

	public void excluir(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		
		entityManager.remove(consultar(codigo));
	}
}