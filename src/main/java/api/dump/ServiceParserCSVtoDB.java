package api.dump;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.dao.CasosAedesDAO;
import api.model.CasosAedesStrings;

/**
 * Classe que possui as validacoes das FKs
 * Esta classe verifica se os IDs passados nos CSVs
 * existem nas tabelas complementares do BD. 
 * As listas servem para armazenar os valores e fazer consultas futuras,
 * evitando mais consultas no BD
 * 
 * @author juccelino.barros
 *
 */
public class ServiceParserCSVtoDB {
	
	private List<String> tpNotificacaoIDs;
	private List<String> coCIDs;
	private List<String> tpGestanteIDs;
	private List<String> tpRacaCorIDs;
	private List<String> tpEscolaridadeIDs;
	private List<String> coUfResidenciaIDs;
	private List<String> coMunicipioResidenciaIDs;
	private List<String> coDistritoResidenciaIDs;
	private Map<String, Integer> mapBairroCode;
	private List<String> tpZonaResidenciaIDs;
	private List<String> tpClassificacaoFinalIDs;
	private List<String> tpCriterioConfirmacaoIDs;
	private List<String> tpEvolucaoCasoIDs;
	private CasosAedesDAO dao;
	
	public ServiceParserCSVtoDB() {
		
		this.dao = new CasosAedesDAO();
		
		this.tpNotificacaoIDs = new ArrayList<String>();
		this.coCIDs = new ArrayList<String>();
		this.tpGestanteIDs = new ArrayList<String>();
		this.tpRacaCorIDs = new ArrayList<String>();
		this.tpEscolaridadeIDs = new ArrayList<String>();
		this.coUfResidenciaIDs = new ArrayList<String>();
		this.coMunicipioResidenciaIDs = new ArrayList<String>();
		this.coDistritoResidenciaIDs = new ArrayList<String>();
		this.tpZonaResidenciaIDs = new ArrayList<String>();
		this.tpClassificacaoFinalIDs = new ArrayList<String>();
		this.tpCriterioConfirmacaoIDs = new ArrayList<String>();
		this.tpEvolucaoCasoIDs = new ArrayList<String>();
		this.mapBairroCode = new HashMap<String, Integer>();
	}
	
	/*
	 * Metodos responsaveis por gerenciar 
	 * as conexoes e as querys do Banco de Dados
	 */
	public void openConnectionDB() {
		try {
			this.dao.beforeExecuteQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao abrir conexão com o BD:\n"+e.getMessage());
		}
	}
	
	public void closeInsertQuery() {
		try {
			this.dao.closeInsertQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar query de inserção com o BD:\n"+e.getMessage());
		}
	}
	
	public void closeConnectionDB() {
		try {
			this.dao.afterExecuteQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão com o BD:\n"+e.getMessage());
		}
	}
	
	public void prepareInsertCasosAedes() {
		try {
			this.dao.prepareInsertCasosAedes();
		} catch (SQLException e) {
			System.out.println("Erro ao preparar a query de insercao:\n"+e.getMessage());
		}
	}
	
	public void insertCasosAedesRegister(CasosAedesStrings cas) {
		try {
			this.dao.insertCasosAedesRegister(cas);
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o registro no BD:\n"+e.getMessage());
		}
	}
	
	/**
	 * Metodo que valida as chaves estrangeiras. Realiza uma consulta
	 * primeiro no array list, caso nao existe consulta direto no BD e add
	 * o valor na lista.
	 * Desta forma evita que varias consultas repetidas sejam feitas direto
	 * no BD
	 * @param value valor da coluna
	 * @param listOfIDs lista dos IDs ja consultados
	 * @param table tabela do BD que sera feita as consultas
	 * @return
	 */
	public String checkColumnValue(String value, List<String> listOfIDs, String table) {
		if (value != null){
			if (listOfIDs.contains(value)){
				return value;
			}else {
				boolean checkFK = this.checkFKService(value, table);
				if (checkFK) {
					listOfIDs.add(value);
				} else{
					return null;
				}
			}
		}
		return value;
	}
	
	/**
	 * Consulta o bairro pelo nome. Primeiro verifica
	 * se ja existe na lista antes de ir consultar no BD.
	 * Evitando muitas consultas no BD.
	 * 
	 * @param name nomeBairro
	 * @return id codigod do bairro
	 */
	public String getBairroIDByName(String bairroName){
		String bairroCodeStr = null;
		try {
			// Verifica se o bairro ja foi consultado
			if (this.mapBairroCode.containsKey(bairroName)) {
				bairroCodeStr = this.mapBairroCode.get(bairroName).toString();
			}else{
				Integer bairroCode = 0;
				bairroCode = this.dao.findBairroByName(bairroName);
				this.mapBairroCode.put(bairroName, bairroCode);
				bairroCodeStr = bairroCode.toString();
			}
			return bairroCodeStr;
		}catch (SQLException e) {
			System.out.println("SQL - erro na montagem do Objeto <BAIRRO>: \n"+e.getMessage());
			return null;
		}

	}
	
	/**
	 * Faz uma consulta que retorna todas as colunas da tabela 
	 * casos aedes. Na leitura do CSV so sera feita a leitura 
	 * das colunas referentes a esta consulta.
	 * @return lista colunas da tabela casos aedes.
	 */
	public List<String> getAllColumnsDBCasosAedes() {
		CasosAedesDAO casosDAO = new CasosAedesDAO();
		List<String> allColumnsDB = new ArrayList<String>();
		try {
			allColumnsDB = casosDAO.getAllColumns();
		} catch (SQLException e) {
			System.out.println("Erro Casos Aedes DAO: "+e.getMessage());
		}
		return allColumnsDB;
	}
	
	/**
	 * Verifica se existe a chave PK referente a FK 
	 * nas tabelas complementares
	 * @param codeStr codigo que vem do CSV
	 * @param table tabela complementar
	 * @return boolean sim ou nao
	 */
	private boolean checkFKService(String codeStr, String table){
		
		boolean checkFK = false;
		try {
			checkFK = this.dao.checkFKDB(codeStr, table);
		} catch (SQLException e) {
			System.out.println("Erro no checkFK Service Parser CSV\n"+e.getMessage());
		}
		return checkFK;
	}
	
	// GET AND SET
	public List<String> getTpNotificacaoIDs() {
		return tpNotificacaoIDs;
	}

	public void setTpNotificacaoIDs(List<String> tpNotificacaoIDs) {
		this.tpNotificacaoIDs = tpNotificacaoIDs;
	}

	public List<String> getCoCIDs() {
		return coCIDs;
	}

	public void setCoCIDs(List<String> coCIDs) {
		this.coCIDs = coCIDs;
	}

	public List<String> getTpGestanteIDs() {
		return tpGestanteIDs;
	}

	public void setTpGestanteIDs(List<String> tpGestanteIDs) {
		this.tpGestanteIDs = tpGestanteIDs;
	}

	public List<String> getTpRacaCorIDs() {
		return tpRacaCorIDs;
	}

	public void setTpRacaCorIDs(List<String> tpRacaCorIDs) {
		this.tpRacaCorIDs = tpRacaCorIDs;
	}

	public List<String> getTpEscolaridadeIDs() {
		return tpEscolaridadeIDs;
	}

	public void setTpEscolaridadeIDs(List<String> tpEscolaridadeIDs) {
		this.tpEscolaridadeIDs = tpEscolaridadeIDs;
	}

	public List<String> getCoUfResidenciaIDs() {
		return coUfResidenciaIDs;
	}

	public void setCoUfResidenciaIDs(List<String> coUfResidenciaIDs) {
		this.coUfResidenciaIDs = coUfResidenciaIDs;
	}

	public List<String> getCoMunicipioResidenciaIDs() {
		return coMunicipioResidenciaIDs;
	}

	public void setCoMunicipioResidenciaIDs(List<String> coMunicipioResidenciaIDs) {
		this.coMunicipioResidenciaIDs = coMunicipioResidenciaIDs;
	}

	public List<String> getCoDistritoResidenciaIDs() {
		return coDistritoResidenciaIDs;
	}

	public void setCoDistritoResidenciaIDs(List<String> coDistritoResidenciaIDs) {
		this.coDistritoResidenciaIDs = coDistritoResidenciaIDs;
	}

	public Map<String, Integer> getMapBairroCode() {
		return mapBairroCode;
	}

	public void setMapBairroCode(Map<String, Integer> mapBairroCode) {
		this.mapBairroCode = mapBairroCode;
	}

	public List<String> getTpZonaResidenciaIDs() {
		return tpZonaResidenciaIDs;
	}

	public void setTpZonaResidenciaIDs(List<String> tpZonaResidenciaIDs) {
		this.tpZonaResidenciaIDs = tpZonaResidenciaIDs;
	}

	public List<String> getTpClassificacaoFinalIDs() {
		return tpClassificacaoFinalIDs;
	}

	public void setTpClassificacaoFinalIDs(List<String> tpClassificacaoFinalIDs) {
		this.tpClassificacaoFinalIDs = tpClassificacaoFinalIDs;
	}

	public List<String> getTpCriterioConfirmacaoIDs() {
		return tpCriterioConfirmacaoIDs;
	}

	public void setTpCriterioConfirmacaoIDs(List<String> tpCriterioConfirmacaoIDs) {
		this.tpCriterioConfirmacaoIDs = tpCriterioConfirmacaoIDs;
	}

	public List<String> getTpEvolucaoCasoIDs() {
		return tpEvolucaoCasoIDs;
	}

	public void setTpEvolucaoCasoIDs(List<String> tpEvolucaoCasoIDs) {
		this.tpEvolucaoCasoIDs = tpEvolucaoCasoIDs;
	}
}
