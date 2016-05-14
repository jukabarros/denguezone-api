package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	 * @return protocolo da nova denuncia
	 * @throws SQLException
	 */
	public String insertDenuncia(String bairro, String endereco, String descricao) throws SQLException {
		Date agora = new Date();
		Timestamp dataCriacao = new Timestamp(agora.getTime());
		String protocolo = "p"+dataCriacao.getTime();
		this.beforeExecuteQuery();
		this.query = "INSERT INTO denuncia (protocolo, bairro, endereco, descricao, status, data_criacao)"
				+ " VALUES (?,?,?,?,?,?)";
		this.queryExec = this.connDB.prepareStatement(query);
		this.queryExec.setString(1, protocolo);
		this.queryExec.setString(2, bairro);
		this.queryExec.setString(3, endereco);
		this.queryExec.setString(4, descricao);
		this.queryExec.setString(5, "PENDENTE");
		this.queryExec.setTimestamp(6, dataCriacao);
		this.queryExec.execute();
		
		this.afterExecuteQuery();
		return protocolo;
	}
	
}
