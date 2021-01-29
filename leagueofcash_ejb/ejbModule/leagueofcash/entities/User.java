package leagueofcash.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;



@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUser;

	private String last_Name;

	private String login;

	private String name;

	private String nick;

	private String password;

	private String position;

	private String rola;

	//bi-directional many-to-one association to Charge
	@OneToMany(mappedBy="user")
	private List<Charge> charges;

	//bi-directional many-to-many association to Team
	@ManyToMany
	@JoinTable(
		name="team_has_user"
		, joinColumns={
			@JoinColumn(name="User_idUser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Team_idteam")
			}
		)
	private List<Team> teams;

	public User() {
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRola() {
		return this.rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public List<Charge> getCharges() {
		return this.charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	public Charge addCharge(Charge charge) {
		getCharges().add(charge);
		charge.setUser(this);

		return charge;
	}

	public Charge removeCharge(Charge charge) {
		getCharges().remove(charge);
		charge.setUser(null);

		return charge;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}