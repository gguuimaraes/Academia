package br.com.pmgt.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.pmgt.model.UsuarioModel;
import br.com.pmgt.repository.UsuarioRepository;
import br.com.pmgt.repository.entity.Usuario;
import br.com.pmgt.uteis.Uteis;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private Usuario usuario;

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public UsuarioModel GetUsuarioSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	public String efetuarLogin() {

		if (StringUtils.isEmpty(usuarioModel.getNome()) || StringUtils.isBlank(usuarioModel.getNome())) {
			Uteis.Mensagem("Favor informar o usuário!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {
			Uteis.Mensagem("Favor informar a senha!");
			return null;
		} else {
			usuario = usuarioRepository.ValidaUsuario(usuarioModel);
			if (usuario != null) {
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuario.getCodigo());
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
				return "sistema/home?faces-redirect=true";
			} else {
				Uteis.Mensagem("Usuário ou senha incorretos!");
				return null;
			}
		}

	}

}