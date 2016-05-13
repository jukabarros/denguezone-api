package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import api.model.Denuncia;

@Repository("denunciaDAO")
public class DenunciaDAO extends AbstractDAO {
	
	/**
	 * Lista todas as denuncias ordenadas pela data de criacao
	 * @return lista de denuncias
	 * @throws SQLException
	 */
	public List<Denuncia> getAll() throws SQLException {
		List<Denuncia> denuncias = new ArrayList<>();
		this.beforeExecuteQuery();
		this.query = "SELECT * FROM denuncia ORDER BY data_criacao";
		this.queryExec = this.connDB.prepareStatement(query);
		ResultSet results = this.queryExec.executeQuery();
		while (results.next()){
			Denuncia denuncia = new Denuncia(results.getInt("id"),
					results.getString("protocolo"),
					results.getString("bairro"),
					results.getString("endereco"),
					results.getString("descricao"),
					results.getString("status"),
					results.getTimestamp("data_criacao"),
					results.getTimestamp("data_atualizacao"));
			
			denuncias.add(denuncia);
		}
		results.close();
		this.afterExecuteQuery();
		return denuncias;
	}
	
	/**
	 * Realiza uma consulta pelo protocolo
	 * @param protocolo protocolo a ser consultado
	 * @return
	 * @throws SQLException
	 */
	public Denuncia findDenunciaByProtocolo(String protocolo) throws SQLException {
		this.beforeExecuteQuery();
		this.query = "SELECT * FROM denuncia WHERE protocolo = ?";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setString(1, protocolo);
		ResultSet results = this.queryExec.executeQuery();
		Denuncia denuncia = new Denuncia();
		while (results.next()){
			denuncia.setProtocolo(results.getString("protocolo"));
			denuncia.setBairro(results.getString("bairro"));
			denuncia.setEndereco(results.getString("endereco"));
			denuncia.setDescricao(results.getString("descricao"));
			denuncia.setStatus(results.getString("status"));
			denuncia.setDataCriacao(results.getTimestamp("data_criacao"));
			denuncia.setDataAtualizacao(results.getTimestamp("data_atualizacao"));
			
		}
		results.close();
		this.afterExecuteQuery();
		return denuncia;
	}
	
	/**
	 * Insere uma nova denuncia no sistema. Retorna a nova denuncia para o usuario saber o protocolo
	 * @param d Denuncia nova
	 * @return denuncia registro ja cadastro no BD
	 * @throws SQLException
	 */
	public Denuncia insertDenuncia(Denuncia d) throws SQLException {
		this.beforeExecuteQuery();
		this.query = "INSERT INTO denuncia (bairro, endereco, descricao, status, data_criacao)"
				+ " VALUES (?,?,?,?,?,?)";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setString(1, d.getBairro());
		this.queryExec.setString(2, d.getEndereco());
		this.queryExec.setString(3, d.getDescricao());
		this.queryExec.setString(4, "PENDENTE");
		Timestamp dataCriacao = new Timestamp(new Date().getTime());
		this.queryExec.setTimestamp(5, dataCriacao);
		this.queryExec.execute();
		
		this.afterExecuteQuery();
		Denuncia denuncia = this.findDenunciaByProtocolo(d.getProtocolo());
		return denuncia;
	}
	
	/**
	 * Metodo que cria um protocolo para a nova denuncia
	 * @param id o protocolo é baseado no ID e na Data de Criacao 
	 * @return protocolo
	 * @throws SQLException 
	 */
	// TODO
	private String generateProtocolo(Timestamp dataCriacao) throws SQLException {
		String protocolo = null;
		int lastID = this.getLastID();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		return protocolo;
	}
	
	/**
	 * Realiza uma consulta pelo protocolo
	 * @param protocolo protocolo a ser consultado
	 * @return
	 * @throws SQLException
	 */
	private Integer getLastID() throws SQLException {
		this.query = "SELECT * FROM denuncia ORDER BY id DESC;";
		this.queryExec = this.connDB.prepareStatement(query);
		ResultSet results = this.queryExec.executeQuery();
		int id = 0;
		while (results.next()){
			id = results.getInt("id");
			
		}
		results.close();
		return id;
	}

}
