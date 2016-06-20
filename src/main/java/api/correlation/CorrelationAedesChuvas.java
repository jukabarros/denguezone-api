package api.correlation;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

public class CorrelationAedesChuvas {

	private double mediax;
	private double mediay;
	private double[] x;
	private double[] y;

	public CorrelationAedesChuvas(double[]array1, double[]array2) {
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
	public double media(double[]z) {
		double media=0;
		for (int i = 0; i < z.length; i++) {
			media+=z[i];
		}
		media = media/z.length;
		return media;
	}

	/**
	 * Calcula a variancia do arranjo z
	 * @param z
	 * @return variancia
	 */
	public double variancia(double[] z) {
		double mediaz = this.media(z),var=0;

		for (int i = 0; i < z.length; i++) {
			var += Math.pow((z[i]-mediaz),2);
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

		for (int i = 0; i < x.length; i++) {
			covariancia += (x[i]-mediax)*(y[i]-mediay);
		}
		//calculando coeficiente de pearson
		coef = covariancia/(varianciax*varianciay);
		return coef;
	}
	
	
	/**
	 * Calcula a correlacao de spearman
	 * É usada a lib: org.apache.commons.math3
	 * @return
	 */
	public double correlationSpearman(){
		SpearmansCorrelation s = new SpearmansCorrelation();
		return s.correlation(this.x, this.y);
	}
	
}