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

import api.dao.BairroResidenciaDAO;
import api.dao.CasosAedesDAO;
import api.model.BairroResidencia;
import api.model.Error;
import api.model.ValoresPorMesBairro;

@RestController
@RequestMapping("/casosaedes")
public class CasosAedesController {
	
	@Autowired
	private CasosAedesDAO casosAedesDAO;
	
	@RequestMapping(value = "/{nomeBairro}/{ano}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8")
	public ResponseEntity<?> getUsers(HttpServletRequest request,
			@PathVariable String nomeBairro,
			@PathVariable int ano){
	 	try {
	 		BairroResidenciaDAO bairroDAO = new BairroResidenciaDAO();
	 		BairroResidencia bairro = bairroDAO.findBairroByName(nomeBairro);
	 		if (bairro.getCodigo() != null) {
	 			List<ValoresPorMesBairro> casosPorMesBairro = this.casosAedesDAO.getValuesByMonthBairro(bairro.getCodigo(), ano);
	 			return new ResponseEntity<List<ValoresPorMesBairro>>(casosPorMesBairro, HttpStatus.OK);
	 		} else {
	 			return new ResponseEntity<Error>(new Error(404, "Bairro não encontrado"), HttpStatus.NOT_FOUND); 
	 		}
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	}	 	
	}

}
