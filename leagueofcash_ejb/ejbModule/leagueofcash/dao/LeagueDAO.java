package leagueofcash.dao;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import leagueofcash.entities.League;



@Stateless
public class LeagueDAO {
	private final static String UNIT_NAME = "LoC";

	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(League team) {
		em.persist(team);
	}

	public League merge(League team) {
		return em.merge(team);
	}

	public void remove(League team) {
		em.remove(em.merge(team));
	}

	public League find(Object id) {
		return em.find(League.class, id);
	}

	
}

