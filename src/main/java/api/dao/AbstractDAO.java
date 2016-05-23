package api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import api.config.DBConnect;

/**
 * Classe que possui metodos comuns para todos os DAO.
 * @author juccelino.barros
 *
 */
public abstract class AbstractDAO {
	
	protected Connection connDB;
	protected String query;
	protected PreparedStatement queryExec;
	
	protected PreparedStatement queryExecInsert;
	protected String queryInsert;
	
	public AbstractDAO() {
		this.connDB = null;
		this.query = null;
		this.queryExec = null;
		
		this.queryExecInsert = null;
		this.queryInsert = null;
	}

	/*
	 * Metodo before e after sao usados para
	 * abrir e fechar conexao
	 */
	
	public void beforeExecuteQuery() throws SQLException{
		if (this.connDB == null || this.connDB.isClosed()){
			this.connDB = new DBConnect().connectMysql();
		}
	}
	
	public void afterExecuteQuery() throws SQLException{
		this.queryExec.close();
		this.connDB.close();
	}
	
	public void beforeExecuteImport() throws SQLException{
		if (this.connDB == null || this.connDB.isClosed()){
			this.connDB = new DBConnect().connectMysql();
			this.connDB.setAutoCommit(false);
		}
	}
	public void afterExecuteImport() throws SQLException{
		if (this.queryExec != null) {
			this.queryExec.close();
		}
		this.connDB.commit();
		this.connDB.close();
	}
	
	public void closeInsertQuery() throws SQLException {
		this.queryExecInsert.close();
	}

}
