package api.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

import api.model.Denuncia;

public class DenunciaJsonParser {
	
	public Denuncia jsonToDenuncia(String json) throws ParseException{
		
		JSONObject jsonObj = new JSONObject(json);
		Timestamp dataCriacao = this.convertTimeStamp(jsonObj, "dataCriacao");
		
		Timestamp dataAtualizacao = this.convertTimeStamp(jsonObj, "dataAtualizacao");
		
		Denuncia d = new Denuncia(jsonObj.getInt("id"), jsonObj.getString("protocolo"), 
				jsonObj.getString("bairro"), jsonObj.getString("endereco"), jsonObj.getString("descricao"),
				jsonObj.getString("status"),
				dataCriacao,
				dataAtualizacao
				);
		
		
		return d;
	}
	
	private Timestamp convertTimeStamp(JSONObject jsonObj, String fieldName) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateJson = jsonObj.getString(fieldName);
			Timestamp ts = new Timestamp(sdf.parse(dateJson).getTime());
			return ts;
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar o parser JSON Date String to Timestamp");
			return null;
		}
	}

}
