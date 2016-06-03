package api.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import api.model.Chuva;
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

	/**
	 * Consulta um bairro pelo nome.
	 * @param name nome do bairro
	 * @return bairro
	 * @throws SQLException 
	 */
	public List<Chuva> findChuvasByYear(int ano) throws SQLException {
		this.beforeExecuteQuery();
		this.query = "SELECT * FROM chuvas WHERE YEAR(data) = ? ";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setInt(1, ano);
		ResultSet results = this.queryExec.executeQuery();
		List<Chuva> chuvas = new ArrayList<Chuva>();
		while (results.next()){
			Chuva ch = new Chuva(results.getInt("id"),
					results.getString("estacao"), 
					results.getTimestamp("data"),
					results.getInt("hora"), 
					results.getDouble("precipitacao"));
			chuvas.add(ch);
		}
		results.close();
		this.afterExecuteQuery();
		return chuvas;
	}

	/**
	 * Consulta um bairro pelo nome.
	 * @param name nome do bairro
	 * @return bairro
	 * @throws SQLException 
	 */
	public List<Double> findChuvasByYearGroupByMonth(int ano) throws SQLException {
		this.beforeExecuteQuery();
		this.query = "SELECT MONTH(data) as mes, SUM(precipitacao) as quantidade"
				+ " FROM chuvas WHERE YEAR(data) = ? GROUP BY MONTH(data);";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setInt(1, ano);
		ResultSet results = this.queryExec.executeQuery();
		List<Integer> meses = new ArrayList<Integer>();
		List<Double> valoresChuvas = new ArrayList<Double>();
		while (results.next()){
			meses.add(results.getInt("mes"));
			valoresChuvas.add(results.getDouble("quantidade"));
		}
		results.close();
		this.afterExecuteQuery();

		// Add valor 0 caso nao apresente nenhuma notificacao em determinado mes
		if (meses.size() != 12){
			for (int i = 1; i <= 12; i++) {
				if (!meses.contains(i)){
					valoresChuvas.add(i-1, 0.0);
				}
			}
		}
		return valoresChuvas;
	}


}
