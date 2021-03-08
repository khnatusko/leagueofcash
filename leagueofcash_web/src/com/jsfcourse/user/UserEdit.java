package com.jsfcourse.user;

import java.io.IOException;
import java.io.Serializable;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.servlet.http.HttpSession;



import leagueofcash.dao.LeagueDAO;
import leagueofcash.dao.TeamDAO;
import leagueofcash.dao.UserDAO;
import leagueofcash.entities.League;
import leagueofcash.entities.Team;
import leagueofcash.entities.User;

@Named
@RequestScoped
public class UserEdit {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_USER = "index?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME_USER = null;
	private static final String PAGE_STAY_AT_THE_SAME_TEAM = null;
	private static final String PAGE_STAY_AT_THE_SAME_LEAGUE = null;
	
	
	private User user = new User();
	
	private User loaded = null;

		public User getUser() {
			return user;
		}
	
		public void setUser(User user) {
			this.user = user;
		}

		public User getLoaded() {
			return loaded;
		}

		public void setLoaded(User loaded) {
			this.loaded = loaded;
		}


		@Inject
		FacesContext context;

		@EJB
		UserDAO userDAO;




		public String changeLogin() {
			RemoteClient<User> rm = new RemoteClient<User>();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			rm = RemoteClient.load(session);
			loaded = (User) rm.getDetails();
			if(loaded != null) {
				if ((loaded.getLogin() != user.getLogin()) && !(user.getLogin().equals(""))) {
					loaded.setLogin(user.getLogin());
				}
				if ((loaded.getPassword() != user.getPassword()) && !(user.getPassword().equals(""))) {
					loaded.setPassword(user.getPassword());
				}
				try {
					userDAO.merge(loaded);
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Poprawnie zmienione dane logowania",null));
				} catch (Exception e) {
					e.printStackTrace();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"B³¹d podczas zmiany danych logowania",null));
					return PAGE_STAY_AT_THE_SAME_USER;
				}
				return PAGE_STAY_AT_THE_SAME_USER;
			}
			return PAGE_USER;
		}
		//public String deleteuser(){
		//	userDAO.remove(user);
		//	return PAGE_STAY_AT_THE_SAME_USER;
		//}




}
	
	
	
	
	
