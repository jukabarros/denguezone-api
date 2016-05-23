package api.dump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	private ServiceParserCSVChuvatoDB service;

	public ParserCSVChuvastoDB() {
		super();
		this.br = null;
		this.csvSplit = ";";
		this.line = "";

		this.service = new ServiceParserCSVChuvatoDB();
		this.columnsDB = this.service.getAllColumnsDBChuvas();
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
			this.service.prepareInsertChuvas();
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
					ChuvaString chuvaStr = this.mountObjectChuvasStr(allValuesByLine, columnPositionCSV);
					this.service.insertChuvaRegister(chuvaStr);
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

	/**
	 * Monta o objeto ChuvaString
	 * @param lineOfCsv conteudo do CSV
	 * @param columnIndex index da coluna
	 * @return objeto Chuva String
	 */
	private ChuvaString mountObjectChuvasStr(List<String> lineOfCsv, Map<String, Integer> columnIndex) {
		try {
			String dataChuvaBD = this.convertToDateFormatBD(lineOfCsv.get(columnIndex.get("data")));

			// Montagem do Objeto
			ChuvaString chuvasStr = new ChuvaString(
					lineOfCsv.get(columnIndex.get("estacao")),
					dataChuvaBD,
					lineOfCsv.get(columnIndex.get("hora")),
					lineOfCsv.get(columnIndex.get("precipitacao")));

			return chuvasStr;
		} catch (Exception e){
			System.out.println("Erro na montagem do Objeto: "+e.getMessage());
			return null;
		}

	}
	
	/**
	 * Converte a data que vem do CSV em formato BR (dd/MM/yyyy) para
	 * o formato do BD (yyyy-MM-dd) tambem em string
	 * @param dataChuvaBR data que vem do csv
	 */
	private String convertToDateFormatBD(String dataChuvaBR) {
		SimpleDateFormat formatterBR= new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date date = formatterBR.parse(dataChuvaBR);
			SimpleDateFormat formatterBD = new SimpleDateFormat("yyyy-MM-dd");
			String dataBD = formatterBD.format(date);
			return dataBD;
		} catch (ParseException e) {
			e.printStackTrace();
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
