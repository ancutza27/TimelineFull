package timelineme.dao;

public class DaoFactory {

	public static AgenteDao getAgenteDao(){
		return AgenteDaoJdbcImpl.getInstance();
	}

}
