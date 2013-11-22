package timelineme.service;


import timelineme.model.Agente;
import timelineme.dao.PersistenceException;



public class LoginService {

	public Boolean authenticate(String username, String password) throws PersistenceException {
		Agente usuarioActual = findByName(username);
		return usuarioActual.getUsername().equals(username) && usuarioActual.getPassword().equals(password);
	}
	
	public Agente findByName(String username) throws PersistenceException{
		AgenteService personaSvc = new AgenteService();
		return personaSvc.findByName(username);
		
	}
	
}
