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
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import api.dao.BairroResidenciaDAO;
import api.model.BairroResidencia;
import api.model.Error;

@Api(value = "Bairro")
@RestController
@RequestMapping("/bairros")
public class BairroResidenciaController {
	
	@Autowired
	private BairroResidenciaDAO bairroDAO;
	
	
	@ApiOperation(value = "Retorna todos os bairros do Recife.", notes = "Retorna todos bairros"
					, response = BairroResidencia.class)
	@ApiResponses(value = {
    		@ApiResponse(code = 500, message = "Erro no Servidor"), 
    		@ApiResponse(code = 400, message = "Entrada inválida"), 
    		@ApiResponse(code = 404, message = "Nenhum bairro encontrado.")})
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getBairros(HttpServletRequest request){
		List<BairroResidencia> bairros = new ArrayList<BairroResidencia>();
	 	try {
	 		bairros = bairroDAO.getAll();
	 		return new ResponseEntity<List<BairroResidencia>>(bairros, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	}	 	
	}
	
	@ApiOperation(value = "Retorna o bairro do Recife especificado pelo nome.", notes = "Retorna apenas 1 bairro ou nenhum"
				, response = BairroResidencia.class)
	@ApiResponses(value = {
    		@ApiResponse(code = 500, message = "Erro no Servidor"), 
    		@ApiResponse(code = 400, message = "Entrada inválida"), 
    		@ApiResponse(code = 404, message = "Nenhum bairro encontrado.")})
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
