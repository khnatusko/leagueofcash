package cos.jsfcourse.charge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import leagueofcash.entities.Charge;
import leagueofcash.entities.User;

public class ChargeAdd implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MAIN = "/pages/user/groups?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_EDIT = "/pages/user/groupEdit?faces-redirect=true";
	
	private Charge charge = new Charge();
	private User loaded = null;
	private List<User> selectedUsers = new ArrayList<User>();
	//private LazyDataModel<User> lazyUserModel;
	

}
