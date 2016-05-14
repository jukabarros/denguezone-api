package api.util;

import java.text.ParseException;
import org.json.JSONObject;

import api.model.Denuncia;

public class DenunciaJsonParser {
	
	public Denuncia jsonToDenunciaInsert(String json) throws ParseException{
		
		JSONObject jsonObj = new JSONObject(json);
		Denuncia d = new Denuncia(null,	null, 
				jsonObj.getString("bairro"),
				jsonObj.getString("endereco"),
				jsonObj.getString("descricao"),
				"PENDENTE",
				null,
				null
				);
		return d;
	}
	
//	private Timestamp convertTimeStamp(JSONObject jsonObj, String fieldName) {
//		try {
//			if (jsonObj.getString(fieldName).equals(null)){
//				return null;
//			}
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String dateJson = jsonObj.getString(fieldName);
//			Timestamp ts = new Timestamp(sdf.parse(dateJson).getTime());
//			return ts;
//		} catch (ParseException e) {
//			e.printStackTrace();
//			System.err.println("Erro ao realizar o parser JSON Date String to Timestamp");
//			return null;
//		}
//	}

}
