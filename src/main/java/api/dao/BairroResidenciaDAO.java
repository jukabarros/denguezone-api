package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import api.model.BairroResidencia;

@Repository("bairroDAO")
public class BairroResidenciaDAO extends AbstractDAO {
	
	
	/**
	 * Consulta um bairro pelo nome.
	 * @param name nome do bairro
	 * @return bairro
	 * @throws SQLException 
	 */
	public List<BairroResidencia> getAll() throws SQLException {
		List<BairroResidencia> bairros = new ArrayList<>();
		this.beforeExecuteQuery();
		this.query = "SELECT * FROM bairro_residencia ORDER BY nome";
		this.queryExec = this.connDB.prepareStatement(query);
		ResultSet results = this.queryExec.executeQuery();
		while (results.next()){
			BairroResidencia bairro = new BairroResidencia(results.getInt("codigo"),
					results.getString("nome"),
					results.getString("municipio"));
			
			bairros.add(bairro);
		}
		results.close();
		this.afterExecuteQuery();
		return bairros;
	}
	
	/**
	 * Consulta um bairro pelo nome.
	 * @param name nome do bairro
	 * @return bairro
	 * @throws SQLException 
	 */
	public BairroResidencia findBairroByName(String name) throws SQLException {
		this.beforeExecuteQuery();
		this.query = "SELECT * FROM bairro_residencia WHERE nome = ?";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setString(1, name);
		ResultSet results = this.queryExec.executeQuery();
		BairroResidencia bairro = new BairroResidencia();
		while (results.next()){
			bairro.setCodigo(results.getInt("codigo"));
			bairro.setNome(results.getString("nome"));
			bairro.setMunicipio(results.getString("municipio"));
		}
		results.close();
		this.afterExecuteQuery();
		return bairro;
	}

}
