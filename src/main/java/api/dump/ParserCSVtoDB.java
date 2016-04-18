package api.dump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import api.dao.CasosAedesDAO;
import api.model.CasosAedesStrings;

/**
 * Classe que faz o parser do CSV para o DB
 * @author juccelino.barros
 *
 */
public class ParserCSVtoDB {

	private BufferedReader br;
	private String csvSplit;
	private String line;
	private CasosAedesDAO dao;
	private List<String> columnsDB;
	
	public ParserCSVtoDB() {
		super();
		this.br = null;
		this.csvSplit = ";";
		this.line = "";
		this.columnsDB = getAllColumnsDBCasosAedes();
		this.dao = new CasosAedesDAO();
	}
	
	/**
	 * Faz a leitura do CSV e Insere no DB
	 * @param pathFile caminho do arquivo
	 */
	public void parserCSVtoDB(String pathFile){
		long startTime = System.currentTimeMillis();
		int lineNumber = 0;
		try{
			this.dao.beforeExecuteQuery();
			this.dao.prepareInsertCasosAedesTemp();
			this.br = new BufferedReader(new FileReader(pathFile));

			// Faz a indexacao da coluna do CSV
			Map<String, Integer> columnPositionCSV = new LinkedHashMap<String, Integer>();
			// Define o tamanho da lista, o index da ultima coluna do CSV referente ao DB
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
							// caso esta coluna seja a ultima do csv
							rowSizeDefault = i;
						}
					}
					System.out.println("** Colunas:\n"+columnPositionCSV);
				}else{
					List<String> allValuesByLine = this.filterLineRegister(lineValues, rowSizeDefault);
					CasosAedesStrings caStr = this.mountObjectCasosAedesStr(allValuesByLine, columnPositionCSV);
					this.dao.insertCasosAedesTemp(caStr);
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
	 * Filtra a linha do CSV, ou seja, realiza o tratamento dos registros
	 * 1 -Remove as aspas dos valores que vem do CSV
	 * 2 - Coloca a linha no tamanho padrao (numero de colunas)
	 * Caso a linha for menor, completa a lista com valor null para nao 
	 * quebrar a integridade das FKs
	 * 
	 * @param line linha do csv
	 * @return list lista com os novos valores sem as aspas
	 */
	private List<String> filterLineRegister (String[] line, int rowSizeDefault){
		List<String> lineFiltered = new ArrayList<String>();
		int sizeCSVLine = line.length;
		for (int i = 0; i < sizeCSVLine; i++) {
			String columnValue = null;
			if(!line[i].isEmpty()){
				columnValue = line[i].replace("\"", "");
			}
			lineFiltered.add(columnValue);
		}
		
		if (rowSizeDefault >= sizeCSVLine) {
			int diff = rowSizeDefault - sizeCSVLine;
			for (int i = 0; i <= diff; i++) {
				lineFiltered.add(sizeCSVLine++, null);
			}
		}
		
		return lineFiltered;
	}
	
	
	private CasosAedesStrings mountObjectCasosAedesStr(List<String> lineOfCsv, Map<String, Integer> columnIndex) {
		try {
			// Consulta BD
			String bairroName = lineOfCsv.get(columnIndex.get("no_bairro_residencia"));
			Integer bairroCode = this.dao.findBairroByName(bairroName);
			// Montagem do Objeto
			CasosAedesStrings castr = new CasosAedesStrings(
					lineOfCsv.get(columnIndex.get("nu_notificacao")),
					lineOfCsv.get(columnIndex.get("tp_notificacao")),
					lineOfCsv.get(columnIndex.get("co_cid")),
					lineOfCsv.get(columnIndex.get("dt_notificacao")),
					lineOfCsv.get(columnIndex.get("ds_semana_notificacao")),
					lineOfCsv.get(columnIndex.get("ano_notificacao")),
					lineOfCsv.get(columnIndex.get("dt_diagnostico_sintoma")),
					lineOfCsv.get(columnIndex.get("ds_semana_sintoma")),
					lineOfCsv.get(columnIndex.get("dt_nascimento")),
					lineOfCsv.get(columnIndex.get("tp_sexo")),
					lineOfCsv.get(columnIndex.get("tp_gestante")),
					lineOfCsv.get(columnIndex.get("tp_raca_cor")),
					lineOfCsv.get(columnIndex.get("tp_escolaridade")),
					lineOfCsv.get(columnIndex.get("co_uf_residencia")),
					lineOfCsv.get(columnIndex.get("co_municipio_residencia")),
					lineOfCsv.get(columnIndex.get("co_distrito_residencia")),
					bairroCode.toString(),
					lineOfCsv.get(columnIndex.get("tp_zona_residencia")),
					lineOfCsv.get(columnIndex.get("tp_classificacao_final")),
					lineOfCsv.get(columnIndex.get("tp_criterio_confirmacao")),
					lineOfCsv.get(columnIndex.get("tp_evolucao_caso"))
					);

			return castr;
		}
		catch (SQLException e) {
			System.out.println("SQL - erro na montagem do Objeto: "+e.getMessage());
			return null;
		} catch (Exception e){
			System.out.println("Erro na montagem do Objeto: "+e.getMessage());
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
