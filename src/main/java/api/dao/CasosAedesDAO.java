package api.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import api.model.CasosAedesStrings;

@Repository("casosAedesDAO")
public class CasosAedesDAO extends AbstractDAO implements Serializable{

	private static final long serialVersionUID = -3992334995663647691L;
	
	public int insertCount;
	
	public CasosAedesDAO() {
		this.insertCount = 0;
	}
	
	/**
	 * Recupera o numero de casos por mes de um determinado
	 * bairro em um determinado ano
	 * @param codigoBairro codigo do bairro
	 * @param ano
	 * @throws SQLException
	 */
	public List<Integer> getValuesByMonthBairro(Integer codigoBairro, Integer ano) throws SQLException {
		this.beforeExecuteQuery();
		this.query = "SELECT MONTH(dt_notificacao) AS mes, COUNT(*) AS quantidade FROM casos_aedes ca, bairro_residencia b "
				+ "WHERE b.codigo = ca.co_bairro_residencia AND b.codigo = ? AND ca.ano_notificacao = ? "
				+ "GROUP BY MONTH(dt_notificacao) ORDER BY MONTH(dt_notificacao);";
		
		this.queryExec = this.connDB.prepareStatement(this.query);
		this.queryExec.setInt(1, codigoBairro);
		this.queryExec.setInt(2, ano);
		
		ResultSet results = this.queryExec.executeQuery();
		List<Integer> valoresGrafico = new ArrayList<Integer>();
		List<Integer> meses = new ArrayList<Integer>();
		while (results.next()){
			meses.add(results.getInt("mes"));
			valoresGrafico.add(results.getInt("quantidade"));
		}
		results.close();
		
		this.afterExecuteQuery();
		// Add valor 0 caso nao apresente nenhuma notificacao em determinado mes
		if (meses.size() != 12){
			for (int i = 1; i <= 12; i++) {
				if (!meses.contains(i)){
					valoresGrafico.add(i-1, 0);
				}
			}
		}
		
		return valoresGrafico;
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
	 * Insert na tabela temporaria (sem as FKs) do BD. 
	 * Usado para realizar os testes do parser
	 * prepara a query para inserir muitos dados
	 * @throws SQLException
	 */
	public void prepareInsertCasosAedesTemp() throws SQLException{
		this.queryInsert = "INSERT INTO casos_aedes_temp"
				+ " (nu_notificacao, tp_notificacao, co_cid, dt_notificacao, ds_semana_notificacao, ano_notificacao,"
				+ "  dt_diagnostico_sintoma, ds_semana_sintoma, dt_nascimento, tp_sexo, tp_gestante, tp_raca_cor,"
				+ "  tp_escolaridade, co_uf_residencia, co_municipio_residencia, co_distrito_residencia, co_bairro_residencia,"
				+ "  tp_zona_residencia, tp_classificacao_final, tp_criterio_confirmacao, tp_evolucao_caso)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		this.queryExecInsert = this.connDB.prepareStatement(this.queryInsert);
	
	}
	
	/**
	 * Insert na tabela principal (com as FKs) do BD. 
	 * Usado para realizar os testes do parser
	 * prepara a query para inserir muitos dados
	 * @throws SQLException
	 */
	public void prepareInsertCasosAedes() throws SQLException{
		this.queryInsert = "INSERT INTO casos_aedes"
				+ " (nu_notificacao, tp_notificacao, co_cid, dt_notificacao, ds_semana_notificacao, ano_notificacao,"
				+ "  dt_diagnostico_sintoma, ds_semana_sintoma, dt_nascimento, tp_sexo, tp_gestante, tp_raca_cor,"
				+ "  tp_escolaridade, co_uf_residencia, co_municipio_residencia, co_distrito_residencia, co_bairro_residencia,"
				+ "  tp_zona_residencia, tp_classificacao_final, tp_criterio_confirmacao, tp_evolucao_caso)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		this.queryExecInsert = this.connDB.prepareStatement(this.queryInsert);
	
	}
	
	public void insertCasosAedesRegister(CasosAedesStrings cas) throws SQLException{
		int index = 0;
		
		this.queryExecInsert.setString(++index, cas.getNuNotificacao());
		this.queryExecInsert.setString(++index, cas.getTpNotificacao());
		this.queryExecInsert.setString(++index, cas.getCoCid());
		this.queryExecInsert.setString(++index, cas.getDtNotificacao());
		this.queryExecInsert.setString(++index, cas.getDsSemanaNotificao());
		this.queryExecInsert.setString(++index, cas.getAnoNotificacao());
		this.queryExecInsert.setString(++index, cas.getDtDiagnosticoSintoma());
		this.queryExecInsert.setString(++index, cas.getDsSemanaSintoma());
		this.queryExecInsert.setString(++index, cas.getDtNascimento());
		this.queryExecInsert.setString(++index, cas.getTpSexo());
		this.queryExecInsert.setString(++index, cas.getTpGestante());
		this.queryExecInsert.setString(++index, cas.getTpRacaCor());
		this.queryExecInsert.setString(++index, cas.getTpEscolaridade());
		this.queryExecInsert.setString(++index, cas.getCoUfResidencia());
		this.queryExecInsert.setString(++index, cas.getCoMunicipioResidencia());
		this.queryExecInsert.setString(++index, cas.getCoDistritoResidencia());
		this.queryExecInsert.setString(++index, cas.getCoBairroResidencia());
		this.queryExecInsert.setString(++index, cas.getTpZonaResidencia());
		this.queryExecInsert.setString(++index, cas.getTpClassificacaoFinal());
		this.queryExecInsert.setString(++index, cas.getTpCriterioConfirmacao());
		this.queryExecInsert.setString(++index, cas.getTpEvolucaoCaso());
		
		
		this.queryExecInsert.addBatch();
		this.queryExecInsert.executeBatch();
		
		this.insertCount++;
		if (this.insertCount%2000==0) {
			System.out.println("** Registros Inseridos: "+this.insertCount);
			this.connDB.commit();
		}
	}
	
	
	/*
	 * Consulta as Tabelas Auxiliares
	 */
	
	/**
	 * Consulta um bairro pelo nome e retorna seu codigo.
	 * Metodo utilizado para inserir o codigo correto na
	 * tabela casos_aedes
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Integer findBairroByName(String name) throws SQLException {
		Integer codigoBairro = 0;
		this.query = "SELECT * FROM bairro_residencia WHERE nome LIKE ? LIMIT 1";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setString(1, "%"+name+"%");
		ResultSet results = this.queryExec.executeQuery();
		
		while (results.next()){
			codigoBairro = results.getInt("codigo");
		}
		results.close();
		return codigoBairro;
	}
	
	/**
	 * Verifica se o ID do que vem  do CSV existe nas 
	 * tabelas que possuem relacionamento com a chave estrangeira da tabela
	 * casos_aedes.
	 * @param id idStr pois é como vem no CSV
	 * @return boolean true or false 
	 * @throws SQLException 
	 */
	public boolean checkFKDB(String codeStr, String table) throws SQLException {
		boolean checkID = false;
		this.query = "SELECT * FROM "+table+" WHERE codigo = ?";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setString(1, codeStr);
		ResultSet results = this.queryExec.executeQuery();
		
		if (results.next()){
			checkID = true;
		}
		results.close();
		return checkID;
		
	}
	
	
}
