package api.endpoint;

import java.sql.Timestamp;
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

import api.dao.CasosAedesDAO;
import api.dao.ChuvaDAO;
import api.model.CasosAedesEntreDatas;
import api.model.Error;

@RestController
@RequestMapping("/correlacoes")
public class CorrelacaoController {
	
	@Autowired
	private CasosAedesDAO casosAedesDAO;
	
	@Autowired
	private ChuvaDAO chuvaDAO;
	
	/**
	 * Faz a correlacao de Pearson Casos Aedes com Chuvas
	 * @param request
	 * @param ano
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getCorrelacaoPearson(HttpServletRequest request, 
			@RequestParam(value = "casosaedesInicio", required = true) String casosaedesInicio,
			@RequestParam(value = "casosaedesFim", required = true) String casosaedesFim,
			@RequestParam(value = "chuvaInicio", required = true) String chuvaInicio,
			@RequestParam(value = "chuvaFim", required = true) String chuvaFim,
		@RequestParam(value = "codbairro", required = true) int codBairro) {
	 	try {
	 		// Convertendo as Datas
	 		Date casosAedesInit = new SimpleDateFormat("dd-MM-yyyy").parse(casosaedesInicio);
			Date casosAedesEnd = new SimpleDateFormat("dd-MM-yyyy").parse(casosaedesFim);
			
			Date chuvaInit = new SimpleDateFormat("dd-MM-yyyy").parse(chuvaInicio);
			Date chuvaEnd = new SimpleDateFormat("dd-MM-yyyy").parse(chuvaFim);
			
			if (casosAedesInit.after(casosAedesEnd)) {
				return new ResponseEntity<Error>(new Error(400, "Intervalo de datas inválido *Casos Aedes"), HttpStatus.BAD_REQUEST); 
			} else if (chuvaInit.after(chuvaEnd)) {
				return new ResponseEntity<Error>(new Error(400, "Intervalo de datas inválido *Chuvas"), HttpStatus.BAD_REQUEST); 
			} else {
				// Convertendo as datas no modelo que esta no banco
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				casosaedesInicio = df.format(casosAedesInit);
				casosaedesFim = df.format(casosAedesEnd);
				
				chuvaInicio = df.format(chuvaInit);
				chuvaFim = df.format(chuvaEnd);

				Map<String, Integer> casosEntreDatasBairro = this.casosAedesDAO.getCasosBTW2DatesByBairro(
						codBairro, casosaedesInicio, casosaedesFim);
				
				if (casosEntreDatasBairro.isEmpty()) {
					return new ResponseEntity<Error>(new Error(404, "Dados não encontrados"), HttpStatus.NOT_FOUND); 
				} 
				return new ResponseEntity<Map<String, Integer>>(casosEntreDatasBairro, HttpStatus.OK);
			}
	 		
	 	} catch (Exception e) {
	 		System.err.println(e.getMessage());
	 		return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	 	} 	
	}

}
