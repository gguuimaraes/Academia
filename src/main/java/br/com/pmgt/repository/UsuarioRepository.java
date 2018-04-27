package br.com.pmgt.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.pmgt.model.UsuarioModel;
import br.com.pmgt.repository.entity.Usuario;
import br.com.pmgt.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	public Usuario ValidaUsuario(UsuarioModel usuarioModel) {

		try {
			// QUERY QUE VAI SER EXECUTADA (Usuario.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("Usuario.findUser");
			// PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (Usuario) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}
}