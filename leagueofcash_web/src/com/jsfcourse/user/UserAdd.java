package com.jsfcourse.user;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


import leagueofcash.dao.UserDAO;
import leagueofcash.entities.User;

@Named
@ViewScoped
public class UserAdd implements Serializable {
		private static final long serialVersionUID = 1L;

		private static final String PAGE_PERSON_LIST = "list?faces-redirect=true";
		private static final String PAGE_STAY_AT_THE_SAME = null;

		private User user = new User();
		private User loaded = null;
	
	
	@Inject
	FacesContext context;
	
	@Inject
	//Flash flash;
	
	@EJB
	UserDAO userDAO;	
	
	public User getUser() {
		return user;
	}
	
	public void onLoad() throws IOException {
		// 1. load person passed through session
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		loaded = (User) session.getAttribute("user");

		// 2. load person passed through flash
		//loaded = (User) flash.get("user");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			user = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne u¿ycie", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (user.getIdUser() == null) {
				// new record
				userDAO.create(user);
			} else {
				// existing record
				userDAO.merge(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst¹pi³ b³¹d podczas dodawania", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_PERSON_LIST;
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
