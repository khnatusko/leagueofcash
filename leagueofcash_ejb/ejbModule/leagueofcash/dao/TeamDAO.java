package leagueofcash.dao;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import leagueofcash.entities.League;
import leagueofcash.entities.Team;
import leagueofcash.entities.User;



@Stateless
public class TeamDAO {
	private final static String UNIT_NAME = "LoC";

	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Team team) {
		em.persist(team);
	}

	public Team merge(Team team) {
		return em.merge(team);
	}

	public void remove(Team team) {
		em.remove(em.merge(team));
	}

	public Team find(Object id) {
		return em.find(Team.class, id);
	}

	public Team getTeamFromDatabase() {
		Team last = new Team();
		Query query = em.createQuery("SELECT t FROM Team t order by idteam DESC");
	    query.setMaxResults(1);
	      
	try {	
		last = (Team)query.getSingleResult();
		
	} catch(NoResultException e) {
		last = null;
	}
	return last;
	}
	
	
}

