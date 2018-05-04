package br.com.pmgt.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.pmgt.model.UsuarioModel;
import br.com.pmgt.repository.entity.Usuario;
import br.com.pmgt.uteis.Uteis;

public class UsuarioRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	EntityManager entityManager;

	public Usuario ValidaUsuario(UsuarioModel usuarioModel) {
		try {
			// QUERY QUE VAI SER EXECUTADA (Usuario.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("Usuario.findUser");
			// PARÂMETROS DA QUERY
			query.setParameter("nome", usuarioModel.getNome());
			query.setParameter("senha", usuarioModel.getSenha());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
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

	public boolean existe(String nome) {
		try {
			Uteis.JpaEntityManager().createNamedQuery("Usuario.findUserByName").setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

}
