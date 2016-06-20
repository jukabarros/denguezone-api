package api.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	/**
	 * Retorna todos os dias de um intervalo de datas
	 * @param startdate data inicio
	 * @param enddate data fim
	 * @return lista de datas
	 */
	public List<String> getDaysBetweenDates(Date startdate, Date enddate) {
		List<String> allDatesString = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			String dateStr = df.format(result);
			allDatesString.add(dateStr);
			
			calendar.add(Calendar.DATE, 1);
		}
		// Add o ultimo dia 
		String endDateSTR = df.format(enddate.getTime());
		allDatesString.add(endDateSTR);
		
		return allDatesString;
	}
	
}
