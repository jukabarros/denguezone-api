package api.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.dao.BairroResidenciaDAO;
import api.model.BairroResidencia;
import api.model.Error;

@RestController
@RequestMapping("/bairros")
public class BairroResidenciaController {
	
	@Autowired
	private BairroResidenciaDAO bairroDAO;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getBairros(HttpServletRequest request,
			@RequestParam(value = "name", required = false) String name){
		List<BairroResidencia> bairros = new ArrayList<BairroResidencia>();
	 	try {
	 		bairros = bairroDAO.getAll();
	 		return new ResponseEntity<List<BairroResidencia>>(bairros, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	}	 	
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getBairrosByName(HttpServletRequest request, 
			@PathVariable String name) {
	 	try {
	 		BairroResidencia bairro = this.bairroDAO.findBairroByName(name.toUpperCase());
	 		if (bairro.getNome() == null){
	 			return new ResponseEntity<Error>(new Error(404, "Bairro não encontrado"), HttpStatus.NOT_FOUND); 
	 		}
	 		return new ResponseEntity<BairroResidencia>(bairro, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	} 	
	}

}
