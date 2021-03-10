package com.jsfcourse.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

import leagueofcash.dao.UserDAO;
import leagueofcash.dao.TeamDAO;
import leagueofcash.dao.ChargeDAO;
import leagueofcash.dao.LeagueDAO;
import leagueofcash.entities.User;
import leagueofcash.entities.Team;
import leagueofcash.entities.League;
import leagueofcash.entities.Charge;

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
		private List<League> leagueList = new ArrayList<League>();
		private User loaded = null;
		private String name;
		private int idLeague;
		private int idUser;	
		private Team selectedTeam;
		private LazyDataModel<User> lazyUserModel;
		private Charge charge = new Charge();


	@Inject
	FacesContext context;
		
	@EJB
	LeagueDAO leagueDAO;
	
	@EJB
	TeamDAO teamDAO;
	
	@EJB
	UserDAO userDAO;
	
	@EJB 
	ChargeDAO chargeDAO;
	
		public void selectedUser(List<User> selectedUser) {
		this.selectedUser = selectedUser;
	}
	
		
	@PostConstruct		
	public void init()
	    {
	      lazyUserModel = new LazyUserDataModel();
	      ((LazyUserDataModel) lazyUserModel).setUserDAO(userDAO);
	      RemoteClient<User> rm = new RemoteClient<User>();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			rm = RemoteClient.load(session);
			loaded = (User) rm.getDetails();
		  ((LazyUserDataModel) lazyUserModel).setIDuser(loaded.getIdUser());
	        
	    }        
	
	public void onLoad() throws IOException {
	
		teams = loaded.getTeams();
		leagueList = leagueDAO.getFullList();
}
	
	public String addUser() {
		try {
	 userDAO.create(this.user); 
	 	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "U¿ytkownik zosta³ dodany", null));
	 		return PAGE_STAY_AT_THE_SAME_USER;
		}
		catch(Exception e) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas dodawania", null));
			return PAGE_STAY_AT_THE_SAME_USER;
		}
	}
	

	
	
	public String addTeam() {
		try {
//			League league = leagueDAO.getLeagueFromDatabase(this.team.getLeague().getName());
			System.out.println("@@@@ WYWOLANO KONTROLER @@@@@");
//			System.out.println(league.getIdLeague());
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, league.getName() + league.getIdLeague() , null));
//			team.setLeague(league);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dupa2", null));
			team.setUsers(selectedUser);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dupa3", null));
	//		league.addTeam(this.team);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dupa3.5", null));
			this.team.setUsers(selectedUser);
			teamDAO.create(this.team);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dupa4", null));
			this.team.setUsers(selectedUser);
			teamDAO.merge(this.team);
	
			for(User u : this.team.getUsers()) {
				u.getTeams().add(team);
				userDAO.merge(u);
				System.out.println(u.getNick());
			}
//			loaded.getTeams().add(team);
			
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
			}
		catch(Exception e) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas dodawania", null));
					return PAGE_STAY_AT_THE_SAME_LEAGUE;
			}
	}

	
	public String addCharge() {
		try {
		charge.setUser(user);
		chargeDAO.create(this.charge);
		charge.setIdCharge(0);
		
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Pomyœlnie dodano pensje.", null));
				return PAGE_STAY_AT_THE_SAME_TEAM;
		}catch(Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst¹pi³ b³¹d podczas zapisu", null ));
			return PAGE_STAY_AT_THE_SAME_TEAM;
		}
		
	}
	
	public void onRowEdit(RowEditEvent<Team> event) {
		try {
			Team t = teamDAO.find(event.getObject().getIdteam());
			t.setTitle(event.getObject().getTitle());
			League l = t.getLeague();
			l.setName(event.getObject().getLeague().getName());
			leagueDAO.merge(l);
			t.setLeague(l);
			teamDAO.merge(t);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Zapisano",null));
		}
		catch(Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wyst¹pi³ b³¹d w trakcie zapisywania",null));
		}
	}
	
	public void onRowCancel(RowEditEvent<Team> event) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"B³¹d", null));
	}
	
	
	public Map<String, Integer> getStringList(){
		List<User> user = teamDAO.getUserList(selectedTeam);
		Map<String, Integer> userName = new LinkedHashMap<String, Integer>();
		for(User u : user) {
			userName.put(u.getName(), u.getIdUser());
		}
		return userName;
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

	public List<League> getLeagueList() {
		return leagueList;
	}

	public void setLeagueList(List<League> leagueList) {
		this.leagueList = leagueList;
	}

	public LazyDataModel<User> getLazyUserModel() {
		return lazyUserModel;
	}

	public void setLazyUserModel(LazyDataModel<User> lazyUserModel) {
		this.lazyUserModel = lazyUserModel;
	}

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}

	public ChargeDAO getChargeDAO() {
		return chargeDAO;
	}

	public void setChargeDAO(ChargeDAO chargeDAO) {
		this.chargeDAO = chargeDAO;
	}

	public User getLoaded() {
		return loaded;
	}

	public void setLoaded(User loaded) {
		this.loaded = loaded;
	}

	public LeagueDAO getLeagueDAO() {
		return leagueDAO;
	}

	public void setLeagueDAO(LeagueDAO leagueDAO) {
		this.leagueDAO = leagueDAO;
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	

}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
