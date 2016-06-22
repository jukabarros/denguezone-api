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

import api.dao.CasosAedesDAO;
import api.model.Error;

@RestController
@RequestMapping("/casosaedes")
public class CasosAedesController {

	@Autowired
	private CasosAedesDAO casosAedesDAO;

	
	/**
	 * Retorna os valores referentes as notificacoes num periodo
	 * de 2 datas em um bairro especifico
	 * 
	 * @param request request
	 * @param codBairro codigo do bairro
	 * @param ano ano
	 * @return grafico barra
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getQntdEntreDatasByBairro(HttpServletRequest request,
			@RequestParam(value = "codbairro", required = true) int codBairro,
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
				
				Map<String, Double> casosEntreDatasBairro = this.casosAedesDAO.getCasosBTW2DatesByBairro(
						codBairro, dataInicio, dataFim);
				
				if (casosEntreDatasBairro.isEmpty()) {
					return new ResponseEntity<Error>(new Error(404, "Dados não encontrados"), HttpStatus.NOT_FOUND); 
				} 
				return new ResponseEntity<Map<String, Double>>(casosEntreDatasBairro, HttpStatus.OK);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}	 	
	}
	
	/**
	 * Retorna os valores referentes a quantidade de notificacao
	 * por bairro e ano. Usado para o grafico barra
	 * 
	 * @param request request
	 * @param codBairro codigo do bairro
	 * @param ano ano
	 * @return grafico barra
	 */
	@RequestMapping(value = "graficobarra/{codBairro}/{ano}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getQntdByBairrosAno(HttpServletRequest request,
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
	
	/**
	 * Retorna o numero de notificacao de uma cidade por mes e ano.
	 * Usado para criar o grafico linha
	 * @param request request
	 * @param codCidade codigo cidade
	 * @param ano ano
	 * @return valores do grafico linha
	 */
	@RequestMapping(value = "graficolinha/{codCidade}/{ano}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getQntdByCidadeAno(HttpServletRequest request,
			@PathVariable int codCidade,
			@PathVariable int ano){
		try {
			List<Integer> casosPorAnoCidade = this.casosAedesDAO.getValuesByMonthCity(codCidade, ano);
			if (casosPorAnoCidade.isEmpty()) {
				return new ResponseEntity<Error>(new Error(404, "Dados não encontrados"), HttpStatus.NOT_FOUND); 
			} 
			return new ResponseEntity<List<Integer>>(casosPorAnoCidade, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Error>(new Error(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}	 	
	}

}
