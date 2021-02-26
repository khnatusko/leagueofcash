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
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;


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
		private User user = new User();
		private List<User> userlist = new ArrayList<User>();;
		private List<League> selectedLeague = new ArrayList<League>();
		private List<Team> teams = new ArrayList<Team>();
		private User loaded = null;
		private String name;
		private String leaguename;
		private int idLeague;
	
	
		
	public Team getTeam() {
			return team;
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

	public String getLeaguename() {
		return leaguename;
	}

	public void setLeaguename(String leaguename) {
		this.leaguename = leaguename;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}


	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}








	@Inject
	FacesContext context;
	
	
	@EJB
	LeagueDAO leagueDAO;
	
	@EJB
	TeamDAO teamDAO;
	
	@EJB
	UserDAO userDAO;
	
	//@Test
	
	//public void testMethod() throws Exception{
	public void onLoad() throws IOException {
		RemoteClient<User> rm = new RemoteClient<User>();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		rm = RemoteClient.load(session);
		loaded = (User) rm.getDetails();
		userlist = userDAO.getFullList();
		Iterator<User> i = userlist.iterator();
		while (i.hasNext()) {
			User u = i.next();
			if(u.getIdUser()== loaded.getIdUser()) {
				i.remove();
			}
		}
		setUserlist(userlist);
		teams = loaded.getTeams();
}
//	}	

	public String addUser() {
		try {
	 userDAO.create(this.user);
	 User user = new User();
	 
	 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "U�ytkownik zosta� dodany", null));
	 return PAGE_STAY_AT_THE_SAME_USER;
		}catch(Exception e) {
	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d podczas dodawania", null));
	 return PAGE_STAY_AT_THE_SAME_USER;
		}
	}
	
	public String addTeam(Map<String,Object> searchParams) {
		try {
		
		League league = leagueDAO.getLeagueFromDatabase(this.name);
		league.addTeam(this.team);
		league.getTeams().add(team);
		teamDAO.create(this.team);
		Team team = new Team();
		
		
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dru�yna zosta�a dodany", null));	
		return PAGE_STAY_AT_THE_SAME_TEAM;
		
		}catch(Exception e) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d podczas dodawania", null));
		return PAGE_STAY_AT_THE_SAME_TEAM;
		}
	}
	
	public String addLeague() {
		try {
			leagueDAO.create(this.league);
			League league = new League();	
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liga zosta�a dodany", null));	
			return PAGE_STAY_AT_THE_SAME_LEAGUE;
			
			}catch(Exception e) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d podczas dodawania", null));
			return PAGE_STAY_AT_THE_SAME_LEAGUE;
			}
	}
	
	
	public List<League> getList(){
		List<League> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = leagueDAO.getList(searchParams);
		
		return list;
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
