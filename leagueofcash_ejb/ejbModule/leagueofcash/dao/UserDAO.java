package leagueofcash.dao;


import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.faces.bean.NoneScoped;
import javax.inject.Named;

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


public List<User> getUsers(int offset, int pageSize){
	List<User> user = null;
	Query query = em.createQuery("Select u From User u");
	query.setFirstResult(offset);
	query.setMaxResults(pageSize);
	List<User> list = query.getResultList();
	return list;

}

public int getRows() {
	int list = 0;
	
	Query query = em.createQuery("Select u From User u");
	
	try {
		list = query.getResultList().size();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
	
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


public List<User> getUsersView(int offset, int pageSize, int IDuser){
	
	Query query = em.createQuery("Select u From User u where idUser != :idUser").setParameter("idUser", IDuser );
	query.setFirstResult(offset);
	query.setMaxResults(pageSize);
	try {
		List<User> list = query.getResultList();
		return list;
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
}

public int countUsers(int IDuser) {
	TypedQuery<Long> query = (TypedQuery<Long>) em
			.createQuery("Select count(*) from User u where idUser != :idUser").setParameter("idUser", IDuser);
	int i = 0;
	try {
		i = query.getSingleResult().intValue();
	}catch(Exception e) {
		e.printStackTrace();
	}
	return i;
}


public List<User> getList(Map<String,Object> searchParams) {
	List<User> list = null;

	String select = "select u ";
	String from = "from User u ";
	String where = "";
	String orderby = "order by u.last_Name asc, u.name";


	String last_Name = (String) searchParams.get("last_Name");
	if (last_Name != null) {
		if (where.isEmpty()) {
			where = "where ";
		} else {
			where += "and ";
		}
		where += "u.last_Name like :last_Name ";
	}
	

	Query query = em.createQuery(select + from + where + orderby);
		

	if (last_Name != null) {
		query.setParameter("last_Name", last_Name+"%");
	}

	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}



	
}
