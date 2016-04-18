package api.dump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import api.dao.CasosAedesDAO;
import api.model.CasosAedes;
import api.model.CasosAedesStrings;

public class ReadCSVFile {

	private BufferedReader br;
	private String csvSplit;
	private String line;
	private CasosAedesDAO dao;
	private List<String> columnsDB;
	
	public ReadCSVFile() {
		super();
		this.br = null;
		this.csvSplit = ";";
		this.line = "";
		this.columnsDB = getAllColumnsDBCasosAedes();
		this.dao = new CasosAedesDAO();
	}
	
	public void parserCSVtoDB(String pathFile){
		long startTime = System.currentTimeMillis();
		int lineNumber = 0;
		try{
			this.dao.beforeExecuteQuery();
			this.dao.prepareInsertCasosAedesTemp();
			
			this.br = new BufferedReader(new FileReader(pathFile));
			// Faz a indexacao da coluna do CSV
			Map<String, Integer> columnPositionCSV = new LinkedHashMap<String, Integer>();
			// Define o tamanho da lista que contem os valores das linhas
			int rowSizeDefault = 0;
			while ((this.line = br.readLine()) != null) {
				lineNumber++;
				String[] lineValues = this.line.split(this.csvSplit);
				if (lineNumber == 1){
					for (int i = 0; i < lineValues.length; i++) {
						String firstLineColumnCSV = lineValues[i].replace("\"", "");
						if (this.columnsDB.contains(firstLineColumnCSV)){
							columnPositionCSV.put(firstLineColumnCSV, i);
							rowSizeDefault = i;
						}
						// ADD Nome do Bairro para realizar a consulta
						if (firstLineColumnCSV.equals("no_bairro_residencia")){
							columnPositionCSV.put(firstLineColumnCSV, i);
							// caso esta coluna for a ultima do csv
							rowSizeDefault = i;
						}
					}
					System.out.println("** Colunas: "+columnPositionCSV);
				}else{
					
					List<String> allValuesByLine = this.removeMarkRegister(lineValues);
					int rowSizeFromCSV = allValuesByLine.size();
					if (rowSizeFromCSV < rowSizeDefault+1) {
						int diff = rowSizeDefault - rowSizeFromCSV;
						for (int i = 0; i <= diff; i++) {
							allValuesByLine.add(rowSizeFromCSV++, "");
						}
					}
					
					CasosAedesStrings caStr = this.mountObjectCasosAedesStr(allValuesByLine, columnPositionCSV);
					this.dao.insertCasosAedesTemp(caStr);
				}
				
				if (lineNumber%2000 == 0){
					System.out.println("** Registros Inseridos: "+lineNumber);
				}

			}
			
			this.dao.closeInsertQuery();
			this.dao.afterExecuteQuery();

			long endTime = System.currentTimeMillis();
			this.calcTimeExecution(startTime, endTime);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("\n\n*** Total Registro: "+(lineNumber-1));
		System.out.println("*** Fim :)");
	}
	
	/**
	 * Remove as aspas dos valores que vem do CSV
	 * @param line linha do csv
	 * @return list lista com os novos valores sem as aspas
	 */
	private List<String> removeMarkRegister (String[] line){
		List<String> lineNoMarks = new ArrayList<String>();
		for (int i = 0; i < line.length; i++) {
			String columnValue = line[i].replace("\"", "");
			lineNoMarks.add(columnValue);
		}
		return lineNoMarks;
	}
	
	/**
	 * Metodo que monta o objeto para insercao no BD
	 * @param lineOfCsv valores dos atributos
	 * @param columnIndex index onde se encontra o valor de cada atributo
	 * @return ca o objeto
	 */
	private CasosAedes mountObjectCasosAedes(List<String> lineOfCsv, Map<String, Integer> columnIndex) {
		CasosAedes ca = new CasosAedes();
		try {
			for (int i = 0; i < lineOfCsv.size(); i++) {
				ca.setNuNotificacao(Integer.parseInt(lineOfCsv.get(columnIndex.get("nu_notificacao"))));
				ca.setTpNotificacao(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_notificacao"))));
				ca.setCoCid(lineOfCsv.get(columnIndex.get("co_cid")));
				ca.setDtNotificacao(this.convertToTimestamp(lineOfCsv.get(columnIndex.get("dt_notificacao"))));
				ca.setDsSemanaNotificao(Integer.parseInt(lineOfCsv.get(columnIndex.get("ds_semana_notificacao"))));
				ca.setAnoNotificacao(Integer.parseInt(lineOfCsv.get(columnIndex.get("ano_notificacao"))));
				ca.setDtDiagnosticoSintoma(this.convertToTimestamp(lineOfCsv.get(columnIndex.get("dt_diagnostico_sintoma"))));
				ca.setDsSemanaSintoma(Integer.parseInt(lineOfCsv.get(columnIndex.get("ds_semana_sintoma"))));
				ca.setDtNascimento(this.convertToTimestamp(lineOfCsv.get(columnIndex.get("dt_nascimento"))));
				ca.setTpSexo(lineOfCsv.get(columnIndex.get("tp_sexo")));
				ca.setTpGestante(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_gestante"))));
				ca.setTpRacaCor(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_raca_cor"))));
				ca.setTpEscolaridade(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_escolaridade"))));
				ca.setCoUfResidencia(Integer.parseInt(lineOfCsv.get(columnIndex.get("co_uf_residencia"))));
				ca.setCoMunicipioResidencia(Integer.parseInt(lineOfCsv.get(columnIndex.get("co_municipio_residencia"))));
				ca.setCoDistritoResidencia(Integer.parseInt(lineOfCsv.get(columnIndex.get("co_distrito_residencia"))));
				
				// Consulta BD
				String bairroName = lineOfCsv.get(columnIndex.get("no_bairro_residencia"));
				Integer bairroCode = this.dao.findBairroByName(bairroName);
				ca.setCoBairroResidencia(bairroCode);
				
				ca.setTpZonaResidencia(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_zona_residencia"))));
				ca.setTpClassificacaoFinal(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_classificacao_final"))));
				ca.setTpCriterioConfirmacao(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_criterio_confirmacao"))));
				ca.setTpEvolucaoCaso(Integer.parseInt(lineOfCsv.get(columnIndex.get("tp_evolucao_caso"))));
			} 

		}
		catch (SQLException e) {
			System.out.println("Erro na montagem do Objeto: "+e.getMessage());
		}

		return ca;
	}
	
	private CasosAedesStrings mountObjectCasosAedesStr(List<String> lineOfCsv, Map<String, Integer> columnIndex) {
		CasosAedesStrings castr = new CasosAedesStrings();
		try {
			for (int i = 0; i < lineOfCsv.size(); i++) {
				castr.setNuNotificacao(this.checkColumnValue(lineOfCsv.get(columnIndex.get("nu_notificacao"))));
				castr.setTpNotificacao(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_notificacao"))));
				castr.setCoCid(this.checkColumnValue(lineOfCsv.get(columnIndex.get("co_cid"))));
				castr.setDtNotificacao(this.checkColumnValue(lineOfCsv.get(columnIndex.get("dt_notificacao"))));
				castr.setDsSemanaNotificao(this.checkColumnValue(lineOfCsv.get(columnIndex.get("ds_semana_notificacao"))));
				castr.setAnoNotificacao(this.checkColumnValue(lineOfCsv.get(columnIndex.get("ano_notificacao"))));
				castr.setDtDiagnosticoSintoma(this.checkColumnValue(lineOfCsv.get(columnIndex.get("dt_diagnostico_sintoma"))));
				castr.setDsSemanaSintoma(this.checkColumnValue(lineOfCsv.get(columnIndex.get("ds_semana_sintoma"))));
				castr.setDtNascimento(this.checkColumnValue(lineOfCsv.get(columnIndex.get("dt_nascimento"))));
				castr.setTpSexo(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_sexo"))));
				castr.setTpGestante(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_gestante"))));
				castr.setTpRacaCor(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_raca_cor"))));
				castr.setTpEscolaridade(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_escolaridade"))));
				castr.setCoUfResidencia(this.checkColumnValue(lineOfCsv.get(columnIndex.get("co_uf_residencia"))));
				castr.setCoMunicipioResidencia(this.checkColumnValue(lineOfCsv.get(columnIndex.get("co_municipio_residencia"))));
				castr.setCoDistritoResidencia(this.checkColumnValue(lineOfCsv.get(columnIndex.get("co_distrito_residencia"))));
				
				// Consulta BD
				String bairroName = lineOfCsv.get(columnIndex.get("no_bairro_residencia"));
				Integer bairroCode = this.dao.findBairroByName(bairroName);
				castr.setCoBairroResidencia(bairroCode.toString());
				
				castr.setTpZonaResidencia(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_zona_residencia"))));
				castr.setTpClassificacaoFinal(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_classificacao_final"))));
				castr.setTpCriterioConfirmacao(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_criterio_confirmacao"))));
				castr.setTpEvolucaoCaso(this.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_evolucao_caso"))));
			} 

		}
		catch (SQLException e) {
			System.out.println("Erro na montagem do Objeto: "+e.getMessage());
		}

		return castr;
	}
	
	/**
	 * Faz a validacao do campo que vem do CSV, pois
	 * existem campos vazios. Eh necessario retornar null
	 * pois alguns campos sao FKs
	 * @param columnValue valor
	 * @return
	 */
	private String checkColumnValue(String columnValue){
		if (columnValue.isEmpty()){
			return null;
		}else{
			return columnValue;
		}
	}
	
	private Timestamp convertToTimestamp(String timestampSTR) {
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse(timestampSTR);
		    Timestamp timestamp = new Timestamp(parsedDate.getTime());
		    return timestamp;
		}catch(Exception e){//this generic but you can control another types of exception
			System.out.println("Erro na conversao da Data");
			return null;
		}
	}
	
	
	
	/**
	 * Calcula tempo de execucao do import
	 * @param start inicio
	 * @param end fim
	 * @return result fim - inicio
	 */
	private long calcTimeExecution (long start, long end){
		long totalTime = end - start;
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out.print("\n******** Tempo total de execução: " 
				+ formatter.format(totalTime / 1000d) + " segundos \n");
		
		return totalTime;
	}
	
	/*
	 * DAO 
	 */
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

}
