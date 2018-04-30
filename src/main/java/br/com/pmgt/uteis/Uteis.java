package br.com.pmgt.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class Uteis {

	public static EntityManager JpaEntityManager() {
		return (EntityManager) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getAttribute("entityManager");
	}

	// MOSTRAR MENSAGEM
	public static void Mensagem(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	// MOSTRAR MENSAGEM
	public static void MensagemAtencao(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	// MOSTRAR MENSAGEM
	public static void MensagemInfo(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

}