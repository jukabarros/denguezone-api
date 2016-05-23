package api.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import api.model.ChuvaString;


@Repository("chuvaDAO")
public class ChuvaDAO extends AbstractDAO implements Serializable{

	private static final long serialVersionUID = -3992334995663647691L;
	
	public int insertCount;
	
	public ChuvaDAO() {
		this.insertCount = 0;
	}
	
	/**
	 * Retorna todas as colunas da tabela chuva
	 * @return list 
	 * @throws SQLException 
	 */
	public List<String> getAllColumns() throws SQLException{
		List<String> allColumnsTable = new ArrayList<String>();
		
		this.beforeExecuteQuery();
		this.query = "DESC chuvas";
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
	 * Insert na tabela principal (com as FKs) do BD. 
	 * Usado para realizar os testes do parser
	 * prepara a query para inserir muitos dados
	 * @throws SQLException
	 */
	public void prepareInsertChuvas() throws SQLException{
		this.queryInsert = "INSERT INTO chuvas"
				+ " (estacao, data, hora, precipitacao)"
				+ " VALUES (?,?,?,?);";
		
		this.queryExecInsert = this.connDB.prepareStatement(this.queryInsert);
	
	}
	
	public void insertChuvasRegister(ChuvaString cs) throws SQLException{
		int index = 0;
		try{
			
			this.queryExecInsert.setString(++index, cs.getEstacao());
			this.queryExecInsert.setString(++index, cs.getData());
			this.queryExecInsert.setString(++index, cs.getHora());
			this.queryExecInsert.setString(++index, cs.getPrecipitacao());

			this.queryExecInsert.addBatch();
			this.queryExecInsert.executeBatch();

			this.insertCount++;
			if (this.insertCount%100==0) {
				System.out.println("** Registros Inseridos: "+this.insertCount);
				this.connDB.commit();
			}
		} catch(Exception e) {
			System.out.println("Erro na importacao do CSV chuva. Ultimo registro: "+this.insertCount);
			
		}
	}
	
	
}
