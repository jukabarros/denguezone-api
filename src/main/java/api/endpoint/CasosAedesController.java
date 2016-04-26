package api.endpoint;

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

import api.dao.CasosAedesDAO;
import api.model.Error;

@RestController
@RequestMapping("/casosaedes")
public class CasosAedesController {

	@Autowired
	private CasosAedesDAO casosAedesDAO;

	@RequestMapping(value = "/{codBairro}/{ano}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
	public ResponseEntity<?> getUsers(HttpServletRequest request,
			@PathVariable int codBairro,
			@PathVariable int ano){
		try {
			List<Integer> casosPorMesBairro = this.casosAedesDAO.getValuesByMonthBairro(codBairro, ano);
			if (casosPorMesBairro.isEmpty()) {
				return new ResponseEntity<Error>(new Error(404, "Dados não encontrados"), HttpStatus.NOT_FOUND); 
			} 
			return new ResponseEntity<List<Integer>>(casosPorMesBairro, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}	 	
	}

}
