package leagueofcash.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the league database table.
 * 
 */
@Entity
@NamedQuery(name="League.findAll", query="SELECT l FROM League l")
public class League implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLeague;

	private String country;

	private String name;

	private double price;

	//bi-directional many-to-one association to Charge
	@OneToMany(mappedBy="league")
	private List<Charge> charges;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="league")
	private List<Team> teams;

	public League() {
	}

	public int getIdLeague() {
		return this.idLeague;
	}

	public void setIdLeague(int idLeague) {
		this.idLeague = idLeague;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Charge> getCharges() {
		return this.charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	public Charge addCharge(Charge charge) {
		getCharges().add(charge);
		charge.setLeague(this);

		return charge;
	}

	public Charge removeCharge(Charge charge) {
		getCharges().remove(charge);
		charge.setLeague(null);

		return charge;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		getTeams().add(team);
		team.setLeague(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setLeague(null);

		return team;
	}

}