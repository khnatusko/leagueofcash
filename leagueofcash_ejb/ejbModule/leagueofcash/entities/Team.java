package leagueofcash.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idteam;

	private double budget;

	private String shortCut;

	private String title;
	
	

	//bi-directional many-to-one association to League
	@ManyToOne
	@JoinTable(name="idLeague")
	private League league;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="teams")
	private List<User> users;

	public Team() {
	}

	public int getIdteam() {
		return this.idteam;
	}

	public void setIdteam(int idteam) {
		this.idteam = idteam;
	}

	public double getBudget() {
		return this.budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getShortCut() {
		return this.shortCut;
	}

	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	

}