package api.dump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import api.dao.CasosAedesDAO;

public class ReadCSVFile {

	private BufferedReader br;
	private String csvSplit;
	private String line;
	
	private List<String> columnsDB;
	
	public ReadCSVFile() {
		super();
		this.br = null;
		this.csvSplit = ";";
		this.line = "";
		this.columnsDB = getAllColumnsDBCasosAedes();
	}

	public void parserCSVtoDB(String pathFile){

		int lineNumber = 0;
		try{
			// Abrir conexao DB AQUI
			this.br = new BufferedReader(new FileReader(pathFile));
			Map<String, Integer> columnPositionCSV = new LinkedHashMap<String, Integer>();
			while ((this.line = br.readLine()) != null) {
				lineNumber++;
				String[] columnValue = this.line.split(this.csvSplit);
				for (int i = 0; i < columnValue.length; i++) {
					String firstLineColumnCSV = columnValue[i].replace("\"", "");
					if (this.columnsDB.contains(firstLineColumnCSV)){
						columnPositionCSV.put(firstLineColumnCSV, i);
					}
				if (lineNumber == 1){
					/*
					 * PEGAR O NOME DO BAIRRO
					 */
				}else{
					/*
					 * MONTAR O OBJETO CASOS_AEDES
					 */
					System.out.println("Agravo " + columnValue[columnPositionCSV.get("co_cid")] + " , "
							+ "Semana Notificacao=" + columnValue[columnPositionCSV.get("ds_semana_notificacao")]);
				}

				}
			}
			// Fechar conexao DB AQUI
			
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
		
		System.out.println("\n\n*** Número de Registro: "+(lineNumber-1));
		System.out.println("*** Fim :)");
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

}
