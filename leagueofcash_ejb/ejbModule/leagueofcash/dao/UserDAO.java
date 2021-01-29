package leagueofcash.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public List<User> getFullList() {
	List<User> list = null;

	Query query = em.createQuery("select u from User u");

	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}


public List<User> getList(Map<String, Object> searchParams) {
	List<User> list = null;

	// 1. Build query string with parameters
	String select = "select u ";
	String from = "from User u ";
	String where = "";
	String orderby = "order by u.last_Name asc, u.name";

	// search for surname
	String last_Name = (String) searchParams.get("last_Name");
	if (last_Name != null) {
		if (where.isEmpty()) {
			where = "where ";
		} else {
			where += "and ";
		}
		where += "u.last_Name like :last_Name ";
	}
	
	// ... other parameters ... 

	// 2. Create query object
	Query query = em.createQuery(select + from + where + orderby);

	// 3. Set configured parameters
	if (last_Name != null) {
		query.setParameter("last_Name", last_Name+"%");
	}

	// ... other parameters ... 

	// 4. Execute query and retrieve list of Person objects
	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}


	
}
