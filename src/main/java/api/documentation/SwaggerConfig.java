package api.documentation;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.alternates.AlternateTypeRule;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * Classe que configura o Swagger
 * 
 * @author juccelino.barros
 *
 */
@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * Implementa o plugin MVC para o Spring
	 * 
	 * @return SwaggerSpringMvcPlugin
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				// This info will be used in Swagger. See realisation of ApiInfo
				// for more details.
				.apiInfo(new ApiInfo("Aedes Zone API",
						"Mais descrição aqui.<br><h5><b>Versão: 1.0 </b></h5>",
						"", "", "", ""))
				.apiVersion("1.0")
				.alternateTypeRules(alternateTypeRulesTimestamp(), alternateTypeRulesDate())
				// Here we specify URI patterns which will be included in
				// Swagger docs. Use regex for this purpose.
				.includePatterns("/bairros.*");
	}

	/**
	 * Cria regras de substituição do modelo: Transforma o Timestamp em String
	 * 
	 * @return AlternateTypeRule
	 */
	private AlternateTypeRule alternateTypeRulesTimestamp() {
		TypeResolver typeResolver = new TypeResolver();
		return new AlternateTypeRule(typeResolver.resolve(Timestamp.class),
				typeResolver.resolve(String.class));
	}

	/**
	 * Cria regras de substituição do modelo: Transforma o Date em String
	 * 
	 * @return AlternateTypeRule
	 */
	private AlternateTypeRule alternateTypeRulesDate() {
		TypeResolver typeResolver = new TypeResolver();
		return new AlternateTypeRule(typeResolver.resolve(Date.class),
				typeResolver.resolve(String.class));
	}

}