package api.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CasosAedesDAO extends AbstractDAO implements Serializable{

	private static final long serialVersionUID = -3992334995663647691L;

	public CasosAedesDAO() {
	}
	
	/**
	 * Retorna todas as colunas da tabela casos_aedes
	 * @return list 
	 * @throws SQLException 
	 */
	public List<String> getAllColumns() throws SQLException{
		List<String> allColumnsTable = new ArrayList<String>();
		
		this.beforeExecuteQuery();
		this.query = "DESC casos_aedes";
		this.queryExec = this.connDB.prepareStatement(query);
		ResultSet results = this.queryExec.executeQuery();
		
		while (results.next()){
			String column = results.getString("Field");
			allColumnsTable.add(column);
		}
		results.close();
		this.afterExecuteQuery();
		return allColumnsTable;
	}
	
	/**
	 * Consulta um bairro pelo nome e retorna seu codigo.
	 * Metodo utilizado para inserir o codigo correto na
	 * tabela casos_aedes
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Integer findBairroByName(String name) throws SQLException{
		Integer codigoBairro = 0;
		this.query = "SELECT * FROM bairro_residencia WHERE nome = ?";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setString(1, name);
		ResultSet results = this.queryExec.executeQuery();
		
		while (results.next()){
			codigoBairro = results.getInt("codigo");
		}
		results.close();
		return codigoBairro;
	}
	

}
