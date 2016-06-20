package api.endpoint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import api.dao.ChuvaDAO;
import api.model.Error;

@RestController
@RequestMapping("/chuvas")
public class ChuvasController {

	@Autowired
	private ChuvaDAO chuvaDAO;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getChuvasBTWDates(HttpServletRequest request, 
			@RequestParam(value = "datainicio", required = true) String dataInicioStr,
			@RequestParam(value = "datafim", required = true) String dataFimStr) {
		try {
			Date dateInit = new SimpleDateFormat("dd-MM-yyyy").parse(dataInicioStr);
			Date dateEnd = new SimpleDateFormat("dd-MM-yyyy").parse(dataFimStr);
			if (dateInit.after(dateEnd)) {
				return new ResponseEntity<Error>(new Error(400, "Intervalo de datas inválido"), HttpStatus.BAD_REQUEST); 
			} else {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String dataInicio = df.format(dateInit);
				String dataFim = df.format(dateEnd);
				
				Map<String, Double> chuvas = this.chuvaDAO.findChuvasBTW2Dates(dataInicio, dataFim);
				if (chuvas.isEmpty()){
					return new ResponseEntity<Error>(new Error(404, "Chuvas não encontradas"), HttpStatus.NOT_FOUND); 
				}
				return new ResponseEntity<Map<String, Double>>(chuvas, HttpStatus.OK);

			}
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
