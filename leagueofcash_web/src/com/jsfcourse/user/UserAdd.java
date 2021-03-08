package com.jsfcourse.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.JoinTable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import org.primefaces.model.SelectableDataModel;

import leagueofcash.dao.UserDAO;
import leagueofcash.dao.TeamDAO;
import leagueofcash.dao.LeagueDAO;
import leagueofcash.entities.User;
import leagueofcash.entities.Team;
import leagueofcash.entities.League;

@Named
@ViewScoped
@Transactional
public class UserAdd implements Serializable {
		private static final long serialVersionUID = 1L;

		private static final String PAGE_USER = "index?faces-redirect=true";
		private static final String PAGE_STAY_AT_THE_SAME_USER = null;
		private static final String PAGE_STAY_AT_THE_SAME_TEAM = null;
		private static final String PAGE_STAY_AT_THE_SAME_LEAGUE = null;
		
		private League league = new League();
		private Team team = new Team();
		private User user = new User();;
		private List<User> users = new ArrayList<User>();
		private List<Team> teams = new ArrayList<Team>();
		private List<User> selectedUser = new ArrayList<User>();
		private User loaded = null;
		private String name;
		private int idLeague;
		private int idUser;		


	@Inject
	FacesContext context;
		
	@EJB
	LeagueDAO leagueDAO;
	
	@EJB
	TeamDAO teamDAO;
	
	@EJB
	UserDAO userDAO;
	
		public void selectedUser(List<User> selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	
	public void onLoad() throws IOException {
		RemoteClient<User> rm = new RemoteClient<User>();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		rm = RemoteClient.load(session);
		loaded = (User) rm.getDetails();
		users = userDAO.getFullList();
		Iterator<User> i = users.iterator();
		while (i.hasNext()) {
			User u = i.next();
			if (u.getIdUser() == loaded.getIdUser()) {
				i.remove();
			}
		}
		setUsers(users);
		teams = loaded.getTeams();
		
}
	
	public String addUser() {
		try {
	 userDAO.create(this.user);
	 User user = new User();
	 
	 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "U¿ytkownik zosta³ dodany", null));
	 return PAGE_STAY_AT_THE_SAME_USER;
		}catch(Exception e) {
	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas dodawania", null));
	 return PAGE_STAY_AT_THE_SAME_USER;
		}
	}
	
		
	public String addTeam() {
		try {
		
		League league = leagueDAO.getLeagueFromDatabase(this.league.getName());
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, league.getName() + league.getIdLeague() , null));
		team.setLeague(league);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dupa2", null));
		team.setUsers(selectedUser);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dupa3", null));
		league.addTeam(this.team);
		teamDAO.create(this.team);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dupa4", null));

		for(User u : selectedUser) {
			u.getTeams().add(team);
			userDAO.merge(u);
		}
		loaded.getTeams().add(team);
		
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dru¿yna zosta³a dodana", null));	
		return PAGE_STAY_AT_THE_SAME_TEAM;
		
		}catch(Exception e) {
		
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas dodawania", null));
		return PAGE_STAY_AT_THE_SAME_TEAM;
		}
	}
	
	public String addLeague() {
		try {
			leagueDAO.create(this.league);
				
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liga zosta³a dodany", null));	
			return PAGE_STAY_AT_THE_SAME_LEAGUE;
			
			}catch(Exception e) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas dodawania", null));
			return PAGE_STAY_AT_THE_SAME_LEAGUE;
			}
	}
	
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Team getTeam() {
		return team;
	}


	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 

	public int getIdLeague() {
		return idLeague;
	}

	public void setIdLeague(int idLeague) {
		this.idLeague = idLeague;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<User> getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(List<User> selectedUser) {
		this.selectedUser = selectedUser;
	}

}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
