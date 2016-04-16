package api.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe utilitária que facilita o acesso aos valores armazenados nos arquivos
 * de propriedades do projeto (*.properties)
 * 
 * @author juccelino.barros
 */
public class ResourceBundleReader {
	
	private ResourceBundle currentBundle;
		
	/**
	 * Construtor da classe
	 * 
	 * @param bundle
	 *            Arquivo de propriedades que será lido
	 */
	public ResourceBundleReader(AvailableBundle bundle){
		Locale locale = new Locale("pt","BR");
		
		switch (bundle) {
		case CONFIGURACOES:
			this.currentBundle = ResourceBundle.getBundle("Configuracoes", locale);
			break;
		case MESSAGES:
		default:
			this.currentBundle = ResourceBundle.getBundle("Messages", locale);
			break;
		}
	}
	
	/**
	 * Método que retorna o valor armazenado no arquivo de propriedades, para
	 * uma dada chave
	 * 
	 * @param key
	 *            Chave da propriedade desejada
	 * @return Valor da chave passada ou null, caso a chave não esteja presente
	 *         no arquivo
	 */
	public String getString(String key) {
		String value = null;
		if(key != null && !key.isEmpty() && !key.trim().isEmpty() 
				&& this.currentBundle != null){
			value = this.currentBundle.getString(key);
		}
		return value;
	}
	
	/**
	 * Método que retorna o valor armazenado no arquivo de propriedades, para
	 * uma dada chave, em formato inteiro.
	 * 
	 * @param key
	 *            Chave da propriedade desejada
	 * @return Valor da chave passada (inteiro)
	 */
	protected int getInt(String key){
		return Integer.parseInt(this.getString(key).trim());
	}

	/**
	 * Método que retorna o valor armazenado no arquivo de propriedades, para
	 * uma dada chave, em formato booleano.
	 * 
	 * @param key
	 *            Chave da propriedade desejada
	 * @return Valor da chave passada (booleano)
	 */
	protected boolean getBoolean(String key) {
		return Boolean.valueOf(this.getString(key).trim()).booleanValue();
	}

	/**
	 * Método que retorna o valor armazenado no arquivo de propriedades, para
	 * uma dada chave, em formato double.
	 * 
	 * @param key
	 *            Chave da propriedade desejada
	 * @return Valor da chave passada (double)
	 */
	protected double getDouble(String key) {
		return Double.parseDouble(this.getString(key).trim());
	}
}