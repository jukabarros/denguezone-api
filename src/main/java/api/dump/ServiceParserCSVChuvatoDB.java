package api.dump;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.ChuvaDAO;
import api.model.ChuvaString;

/**
 * Classe que possui os metodos auxiliares para o
 * Parser do CSV de Chuva
 * @author juccelino.barros
 *
 */
public class ServiceParserCSVChuvatoDB {
	
	private ChuvaDAO dao;
	
	public ServiceParserCSVChuvatoDB() {
		this.dao = new ChuvaDAO();
	}
	
	/*
	 * Metodos responsaveis por gerenciar 
	 * as conexoes e as querys do Banco de Dados
	 */
	public void openConnectionDB() {
		try {
			this.dao.beforeExecuteImport();
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
			this.dao.afterExecuteImport();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão com o BD:\n"+e.getMessage());
		}
	}
	
	public void prepareInsertChuvas() {
		try {
			this.dao.prepareInsertChuvas();
		} catch (SQLException e) {
			System.out.println("Erro ao preparar a query de insercao:\n"+e.getMessage());
		}
	}
	
	public void insertChuvaRegister(ChuvaString cs) {
		try {
			this.dao.insertChuvasRegister(cs);
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o registro no BD:\n"+e.getMessage());
		}
	}
	
	
	/**
	 * Faz uma consulta que retorna todas as colunas da tabela 
	 * chuvas. Na leitura do CSV so sera feita a leitura 
	 * das colunas referentes a esta consulta.
	 * @return lista colunas da tabela chuvas.
	 */
	public List<String> getAllColumnsDBChuvas() {
		ChuvaDAO chuvaDAO = new ChuvaDAO();
		List<String> allColumnsDB = new ArrayList<String>();
		try {
			allColumnsDB = chuvaDAO.getAllColumns();
		} catch (SQLException e) {
			System.out.println("Erro Chuva DAO: "+e.getMessage());
		}
		return allColumnsDB;
	}
	
}
