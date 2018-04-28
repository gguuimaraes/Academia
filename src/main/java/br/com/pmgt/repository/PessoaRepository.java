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

	/***
	 * M�TODO RESPONS�VEL POR SALVAR UMA NOVA PESSOA
	 * 
	 * @param pessoaModel
	 */
	public void incluir(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		pessoa = new Pessoa();
		pessoa.setDataCadastro(new Date());
		pessoa.setEmail(pessoaModel.getEmail());
		pessoa.setEndereco(pessoaModel.getEndereco());
		pessoa.setNome(pessoaModel.getNome());
		pessoa.setSexo(pessoaModel.getSexo());

		Usuario usuarioCadastro = entityManager.find(Usuario.class, pessoaModel.getUsuarioCadastro().getCodigo());

		pessoa.setUsuarioCadastro(usuarioCadastro);

		entityManager.persist(pessoa);

	}

	/***
	 * M�TODO PARA CONSULTAR A PESSOA
	 * 
	 * @return
	 */
	public List<PessoaModel> listar() {

		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();

		entityManager = Uteis.JpaEntityManager();

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

			Usuario usuarioCadastro = pessoa.getUsuarioCadastro();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setNome(usuarioCadastro.getNome());

			pessoaModel.setUsuarioCadastro(usuarioModel);

			pessoasModel.add(pessoaModel);
		}

		return pessoasModel;

	}

	/***
	 * CONSULTA UMA PESSOA CADASTRADA PELO C�DIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private Pessoa consultar(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(Pessoa.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param pessoaModel
	 */
	public void alterar(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		Pessoa pessoaEntity = this.consultar(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void excluir(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		Pessoa pessoaEntity = this.consultar(codigo);

		entityManager.remove(pessoaEntity);
	}
}