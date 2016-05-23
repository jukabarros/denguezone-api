package api.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.dao.DenunciaDAO;
import api.model.Denuncia;
import api.model.Error;
import api.util.DenunciaJsonParser;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {
	
	@Autowired
	private DenunciaDAO denunciaDAO;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
	public ResponseEntity<?> getDenuncias(HttpServletRequest request,
			@RequestParam(value = "name", required = false) String name){
		List<Denuncia> denuncias = new ArrayList<Denuncia>();
	 	try {
	 		denuncias= denunciaDAO.getAll();
	 		return new ResponseEntity<List<Denuncia>>(denuncias, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	}	 	
	}
	
	@RequestMapping(value = "/{protocolo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
	public ResponseEntity<?> getDenunciaByProtocolo(HttpServletRequest request, 
			@PathVariable String protocolo) {
	 	try {
	 		Denuncia denuncia = this.denunciaDAO.findDenunciaByProtocolo(protocolo);
	 		if (denuncia.getId() == null){
	 			return new ResponseEntity<Error>(new Error(404, "Denuncia não encontrada"), HttpStatus.NOT_FOUND); 
	 		}
	 		return new ResponseEntity<Denuncia>(denuncia, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	} 	
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
	public ResponseEntity<?> postDenuncia(HttpServletRequest request, @RequestBody String json){
	 	try {
	 		DenunciaJsonParser parser = new DenunciaJsonParser();
	 		Denuncia novaDenuncia = parser.jsonToDenunciaInsert(json);
	 		// Retorna a denuncia ja cadastrada no BD para o usuario ver o Protocolo
	 		String protocolo = this.denunciaDAO.insertDenuncia(novaDenuncia.getBairro(),
	 				novaDenuncia.getEndereco(),
	 				novaDenuncia.getDescricao());
	 		return new ResponseEntity<String>(protocolo, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	}	 	
	}

}
