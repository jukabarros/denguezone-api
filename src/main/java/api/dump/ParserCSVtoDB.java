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
	private List<String> columnsDB;
	
	private ServiceParserCSVtoDB service;
	
	public ParserCSVtoDB() {
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
	public void parserCSVtoDB(String pathFile){
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
					if (columnPositionCSV.size() != 22) {
						System.out.println("Erro: número de colunas inválido");
						break;
					}
					// Fazer validacao se possuem todas as colunas da tabela?
					System.out.println("** Colunas:\n"+columnPositionCSV);
				}else{
					List<String> allValuesByLine = this.filterLineRegister(lineValues, rowSizeDefault);
					CasosAedesStrings caStr = this.mountObjectCasosAedesStr(allValuesByLine, columnPositionCSV);
					this.service.insertCasosAedesRegister(caStr);
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
	
	
	private CasosAedesStrings mountObjectCasosAedesStr(List<String> lineOfCsv, Map<String, Integer> columnIndex) {
		try {
			String bairroName = lineOfCsv.get(columnIndex.get("no_bairro_residencia"));
			String bairroCodeStr = null;
			
			// Faz a mudanca do codigo do Bairro
			if (bairroName != null){
				bairroCodeStr = this.service.getBairroIDByName(bairroName);
			}
			
			String tpNotificacao = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_notificacao")),
					this.service.getTpNotificacaoIDs(), "tipo_notificacao");
			
			String tpGestante = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_gestante")),
					this.service.getTpGestanteIDs(), "tipo_gestante" );
			
			String tpRacaCor = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_raca_cor")),
					this.service.getTpRacaCorIDs(), "raca_cor");
			
			String tpEscolaridade = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_escolaridade")),
					this.service.getTpEscolaridadeIDs(), "escolaridade");
			
			String coUfResidencia = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("co_uf_residencia")),
					this.service.getCoUfResidenciaIDs(), "uf_residencia");
			
			String coMunicipioResidencia = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("co_municipio_residencia")),
					this.service.getCoMunicipioResidenciaIDs(), "municipio_residencia");
			
			String coDistritoResidencia = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("co_distrito_residencia")),
					this.service.getCoDistritoResidenciaIDs(), "distrito_residencia");
			
			String tpZonaResidencia = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_zona_residencia")),
					this.service.getTpZonaResidenciaIDs(), "zona_residencia");
			
			String tpClassificacaoFinal = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_classificacao_final")),
					this.service.getTpClassificacaoFinalIDs(), "classificacao_final");
			
			String tpCriterioConfirmacao = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_criterio_confirmacao")),
					this.service.getTpCriterioConfirmacaoIDs(), "criterio_confirmacao");
			
			String tpEvolucaoCaso = this.service.checkColumnValue(lineOfCsv.get(columnIndex.get("tp_evolucao_caso")),
					this.service.getTpEvolucaoCasoIDs(), "evolucao_caso");
			
			// Montagem do Objeto
			CasosAedesStrings castr = new CasosAedesStrings(
					lineOfCsv.get(columnIndex.get("nu_notificacao")),
					tpNotificacao,
					lineOfCsv.get(columnIndex.get("co_cid")),
					lineOfCsv.get(columnIndex.get("dt_notificacao")),
					lineOfCsv.get(columnIndex.get("ds_semana_notificacao")),
					lineOfCsv.get(columnIndex.get("ano_notificacao")),
					lineOfCsv.get(columnIndex.get("dt_diagnostico_sintoma")),
					lineOfCsv.get(columnIndex.get("ds_semana_sintoma")),
					lineOfCsv.get(columnIndex.get("dt_nascimento")),
					lineOfCsv.get(columnIndex.get("tp_sexo")),
					tpGestante,
					tpRacaCor,
					tpEscolaridade,
					coUfResidencia,
					coMunicipioResidencia,
					coDistritoResidencia,
					bairroCodeStr,
					tpZonaResidencia,
					tpClassificacaoFinal,
					tpCriterioConfirmacao,
					tpEvolucaoCaso );

			return castr;
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
