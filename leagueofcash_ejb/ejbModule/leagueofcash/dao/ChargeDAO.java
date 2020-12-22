package leagueofcash.dao;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import leagueofcash.entities.Charge;



@Stateless
public class ChargeDAO {
	private final static String UNIT_NAME = "LoC";

	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Charge charge) {
		em.persist(charge);
	}

	public Charge merge(Charge charge) {
		return em.merge(charge);
	}

	public void remove(Charge charge) {
		em.remove(em.merge(charge));
	}

	public Charge find(Object id) {
		return em.find(Charge.class, id);
	}

	
}
