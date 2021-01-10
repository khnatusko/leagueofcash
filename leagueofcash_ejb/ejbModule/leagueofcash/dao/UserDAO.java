package leagueofcash.dao;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
		
	Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login and u.password = :password",User.class);
    query.setParameter("login", login);
    query.setParameter("password", password);  
try {	
	return(User)query.getSingleResult();
	
} catch(NoResultException e) {
	return null;
}
	
    
}

public List<String> getUserRolesFromDatabase(User user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (user.getRola().equals("user")) {
			roles.add("user");
		}
		if (user.getRola().equals("admin")) {
			roles.add("admin");
		}
	
		return roles;
	}
	
	
}
