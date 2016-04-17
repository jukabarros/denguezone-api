package api.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.model.CasosAedesStrings;

public class CasosAedesDAO extends AbstractDAO implements Serializable{

	private static final long serialVersionUID = -3992334995663647691L;
	
	public int insertCount;
	
	public CasosAedesDAO() {
		this.insertCount = 0;
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
	

	/**
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
	
	public void insertCasosAedesTemp(CasosAedesStrings cas) throws SQLException{
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
		if (this.insertCount%5==0){
			this.connDB.commit();
		}
	}
}
