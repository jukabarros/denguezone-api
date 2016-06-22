package api.correlation;

import java.text.DecimalFormat;
import java.util.List;


public class CorrelationAedesChuvas {

	private double mediax;
	private double mediay;
	private List<Double> x;
	private List<Double> y;

	public CorrelationAedesChuvas(List<Double> array1, List<Double> array2) {
		this.x = array1;
		this.y = array2;
		this.mediax = this.media(array1);
		this.mediay = this.media(array2);
	}

	/**
	 * Calcula a media do arranjo
	 * @param z arranjo
	 * @return media
	 */
	public double media(List<Double> z) {
		double media=0;
		for (int i = 0; i < z.size(); i++) {
			media+=z.get(i);
		}
		media = media/z.size();
		return media;
	}

	/**
	 * Calcula a variancia do arranjo z
	 * @param z
	 * @return variancia
	 */
	public double variancia(List<Double> z) {
		double mediaz = this.media(z),var=0;

		for (int i = 0; i < z.size(); i++) {
			var += Math.pow((z.get(i)-mediaz),2);
		}
		return Math.sqrt(var);
	}

	/**
	 * Calcula a correlacao de pearson
	 * São utilizadas duas lista do mesmo tamanho, 
	 * 1- contendo os indices pluviometricos; 2 - os casos da doneca
	 * @return
	 */
	public double correlationPearson() {
		double covariancia=0,coef, varianciax = this.variancia(x), 
				varianciay = this.variancia(y);

		for (int i = 0; i < x.size(); i++) {
			covariancia += (x.get(i)-mediax)*(y.get(i)-mediay);
		}
		//calculando coeficiente de pearson com ate 3 casas decimais
		DecimalFormat df = new DecimalFormat("#.###");   
		coef = covariancia/(varianciax*varianciay);
		return Double.valueOf(df.format(coef));
	}
	
	
//	/**
//	 * Calcula a correlacao de spearman
//	 * É usada a lib: org.apache.commons.math3
//	 * @return
//	 */
//	public double correlationSpearman(){
//		SpearmansCorrelation s = new SpearmansCorrelation();
//		return s.correlation(this.x, this.y);
//	}
	
}