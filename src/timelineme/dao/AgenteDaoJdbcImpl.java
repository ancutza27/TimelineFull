package timelineme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import timelineme.model.Agente;
import timelineme.model.Empresa;

public class AgenteDaoJdbcImpl implements AgenteDao {

	private static AgenteDao instance = new AgenteDaoJdbcImpl();

	public static AgenteDao getInstance() {
		return instance;
	}

	public List<Agente> findAll() throws PersistenceException {
		List<Agente> agentes = new LinkedList<Agente>();
		try {
			String query = "select * from agente a inner join empresa e on e.id_empresa = a.id_empresa";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				agentes.add(convertAgente(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return agentes;
	}

	private Agente convertAgente(ResultSet resultSet) throws SQLException, PersistenceException {
		Agente agente = new Agente();
		
		agente.setId(resultSet.getInt("id"));
		agente.setUsername(resultSet.getString("username"));
		agente.setPassword(resultSet.getString("password"));
		Empresa empresa = new Empresa();
		empresa.setId(resultSet.getInt("id"));
		empresa.setNombre(resultSet.getString("nombre"));
		agente.setEmpresa(empresa);

		try {
			String query = "select e.* from agenteEmpresa ae inner join empresa e on e.id_empresa = ae.id_empresa where id_agente = ?";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, agente.getId());
			ResultSet resultSet2 = statement.executeQuery();
			while (resultSet2.next()) {
				agente.addEmpresa(convertEmpresa(resultSet2));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		
		return agente;
	}

	private Empresa convertEmpresa(ResultSet resultSet2) throws SQLException {
		Empresa empresa = new Empresa();

		empresa.setId(resultSet2.getInt("id"));
		empresa.setNombre(resultSet2.getString("nombre"));		
		
		return empresa;
	}

	@Override
	public Agente findByName(String agenteName) throws PersistenceException {
		if (agenteName == null) {
			throw new IllegalArgumentException(
					"El nombre de usuario no debe ser nulo");
		}
		Agente agente = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from agente where username = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, agenteName);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				agente = convertAgente(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return agente;
	}

}
