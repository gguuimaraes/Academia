package br.com.pmgt.repository;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pmgt.model.PessoaModel;
import br.com.pmgt.model.UsuarioModel;
import br.com.pmgt.repository.entity.Pessoa;
import br.com.pmgt.repository.entity.Usuario;
import br.com.pmgt.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	EntityManager entityManager;

	public Usuario ValidaUsuario(UsuarioModel usuarioModel) {

		try {
			// QUERY QUE VAI SER EXECUTADA (Usuario.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("Usuario.findUser");
			// PAR�METROS DA QUERY
			query.setParameter("nome", usuarioModel.getNome());
			query.setParameter("senha", usuarioModel.getSenha());

			// RETORNA O USU�RIO SE FOR LOCALIZADO
			return (Usuario) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}

	public void incluir(UsuarioModel usuarioModel) {
		entityManager = Uteis.JpaEntityManager();

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioModel.getNome());
		usuario.setSenha(usuarioModel.getSenha());
		entityManager.persist(usuario);
		usuarioModel.setCodigo(usuario.getCodigo());
	}

	public void alterar(UsuarioModel usuarioModel) {
		entityManager = Uteis.JpaEntityManager();

		Usuario usuario = consultar(usuarioModel.getCodigo());
		usuario.setNome(usuarioModel.getNome());
		usuario.setSenha(usuarioModel.getSenha());
		entityManager.merge(usuario);
	}

	private Usuario consultar(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		return entityManager.find(Usuario.class, codigo);
	}
	
	public void excluir(int codigo) {
		entityManager = Uteis.JpaEntityManager();
		entityManager.remove(consultar(codigo));
	}

}
