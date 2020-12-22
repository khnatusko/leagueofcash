package leagueofcash.dao;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.faces.bean.NoneScoped;


import leagueofcash.entities.User;



@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "LoC";

	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object idUser) {
		return em.find(User.class, idUser);
	}
	
public User getUserFromDatabase(String login, String password) {
		
		User u = null;
		
		if (login.equals("user1") && password.equals("password")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(password);
			u.setName("Jan");
			u.setLast_Name("Kowalski");
		}

		return u;
}

public List<String> getUserRolesFromDatabase(User user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (user.getLogin().equals("user1")) {
			roles.add("user");
		}
		if (user.getLogin().equals("user2")) {
			roles.add("admin");
		}
		
		return roles;
	}
	
	
}
