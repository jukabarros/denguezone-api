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

import api.dao.ChuvaDAO;
import api.model.Chuva;
import api.model.Error;

@RestController
@RequestMapping("/chuvas")
public class ChuvasController {

	@Autowired
	private ChuvaDAO chuvaDAO;
	
	@RequestMapping(value = "/{ano}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getChuvasByYear(HttpServletRequest request, 
			@PathVariable int ano) {
	 	try {
	 		List<Chuva> chuvas = this.chuvaDAO.findChuvasByYear(ano);
	 		if (chuvas.isEmpty()){
	 			return new ResponseEntity<Error>(new Error(404, "Chuvas não encontradas"), HttpStatus.NOT_FOUND); 
	 		}
	 		return new ResponseEntity<List<Chuva>>(chuvas, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	} 	
	}
	
	@RequestMapping(value = "/{ano}/mes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getBairrosByYearGroupbyMonth(HttpServletRequest request, 
			@PathVariable int ano) {
	 	try {
	 		List<Double> qntdChuvas = this.chuvaDAO.findChuvasByYearGroupByMonth(ano);
	 		if (qntdChuvas.isEmpty()){
	 			return new ResponseEntity<Error>(new Error(404, "Chuvas não encontradas"), HttpStatus.NOT_FOUND); 
	 		}
	 		return new ResponseEntity<List<Double>>(qntdChuvas, HttpStatus.OK);
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	} 	
	}
}
