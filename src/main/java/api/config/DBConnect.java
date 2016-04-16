package api.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe conexao com MySQL
 * Informacoes da conexao esta no properties CONFIGURACOES
 * @author juccelino.barros
 *
 */
public class DBConnect {
	
	private Connection conn;
	private ResourceBundleReader resourceReader;
	
	public DBConnect() {
		this.resourceReader = new ResourceBundleReader(AvailableBundle.CONFIGURACOES);
	}
	
	public Connection connectMysql(){
		try{
			String host = this.resourceReader.getString("dataBase.host");
			String user = this.resourceReader.getString("dataBase.user");
			String password = this.resourceReader.getString("dataBase.password");
			String database = this.resourceReader.getString("dataBase.name");
			String port = this.resourceReader.getString("dataBase.port");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, user, password);
			return conn;
		}catch (Exception e){
			System.out.println("Erro ao se conectar com o BD: "+e.getMessage());
		}
		return conn;
	}
	

}
