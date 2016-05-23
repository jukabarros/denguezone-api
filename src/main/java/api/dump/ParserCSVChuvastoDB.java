package api.dump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import api.model.ChuvaString;

/**
 * Classe que faz o parser do CSV para o DB
 * @author juccelino.barros
 *
 */
public class ParserCSVChuvastoDB {

	private BufferedReader br;
	private String csvSplit;
	private String line;
	private List<String> columnsDB;
	
	private ServiceParserCSVtoDB service;
	
	public ParserCSVChuvastoDB() {
		super();
		this.br = null;
		this.csvSplit = ";";
		this.line = "";
		
		this.service = new ServiceParserCSVtoDB();
		this.columnsDB = this.service.getAllColumnsDBCasosAedes();
	}
	
	/**
	 * Faz a leitura do CSV e Insere no DB
	 * @param pathFile caminho do arquivo
	 */
	public void parserCSVChuvastoDB(String pathFile){
		long startTime = System.currentTimeMillis();
		
		int lineNumber = 0;
		// Faz a indexacao da coluna do CSV
		Map<String, Integer> columnPositionCSV = new LinkedHashMap<String, Integer>();
		// Define o tamanho da lista, o index da ultima coluna do CSV referente ao DB
		int rowSizeDefault = 0;
		try{
			this.service.openConnectionDB();
			this.service.prepareInsertCasosAedes();
			this.br = new BufferedReader(new FileReader(pathFile));
			while ((this.line = br.readLine()) != null) {
				lineNumber++;
				String[] lineValues = this.line.split(this.csvSplit);
				if (lineNumber == 1){
					for (int i = 0; i < lineValues.length; i++) {
						String firstLineColumnCSV = lineValues[i].replace("\"", "");
						if (this.columnsDB.contains(firstLineColumnCSV.toLowerCase())){
							columnPositionCSV.put(firstLineColumnCSV.toLowerCase(), i);
							rowSizeDefault = i;
						}
					}
					if (columnPositionCSV.size() != 4) {
						System.out.println("Erro: número de colunas inválido");
						break;
					}
					System.out.println("** Colunas:\n"+columnPositionCSV);
				}else{
					List<String> allValuesByLine = this.filterLineRegister(lineValues, rowSizeDefault);
					ChuvaString chStr = this.mountObjectChuvasStr(allValuesByLine, columnPositionCSV);
				}

			}
			
			this.service.closeInsertQuery();
			this.service.closeConnectionDB();
			long endTime = System.currentTimeMillis();
			this.calcTimeExecution(startTime, endTime);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			// ADD null na lista ate o index rowSizeDefault
			for (int i = 0; i <= diff; i++) {
				lineFiltered.add(sizeCSVLine++, null);
			}
		}
		
		return lineFiltered;
	}
	
	
	private ChuvaString mountObjectChuvasStr(List<String> lineOfCsv, Map<String, Integer> columnIndex) {
		try {
				// Montagem do Objeto
			ChuvaString chuvasStr = new ChuvaString(
					lineOfCsv.get(columnIndex.get("estacao")),
					lineOfCsv.get(columnIndex.get("data")),
					lineOfCsv.get(columnIndex.get("hora")),
					lineOfCsv.get(columnIndex.get("precipitacao")));

			return chuvasStr;
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
	
}
