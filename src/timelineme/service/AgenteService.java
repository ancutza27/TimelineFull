package timelineme.service;

import timelineme.model.Agente;
import timelineme.dao.DaoFactory;
import timelineme.dao.PersistenceException;
import timelineme.dao.AgenteDao;




public class AgenteService {

	public Agente findByName(String username) throws PersistenceException {
	
		AgenteDao miAgenteDao = DaoFactory.getAgenteDao();
		return miAgenteDao.findByName(username);
	}

}
