package cl.psep.api;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("comuna-route")
public class ComunaRoute extends RouteBuilder {
	
	@Value("${camel.component.servlet.mapping.context-path}")
	private String contextPath;

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.apiContextPath("/doc")
			.bindingMode(RestBindingMode.json)
			.component("servlet")
			.contextPath(contextPath.substring(0, contextPath.length() - 2))
			.enableCORS(true)
	        .corsAllowCredentials(true)
	        .corsHeaderProperty("Access-Control-Allow-Origin","*")
	        .corsHeaderProperty("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization")
	        .port("{{rest.server.port}}")
			.apiProperty("api.title", "Servicio de prueba de Comunas")
			.apiProperty("api.version", "1.0.0");
		
		rest("/comunas").description("Servicio de comunas")
			.get()
				.description("Lista todas las comunas")
				.produces(MediaType.APPLICATION_JSON)
				.route()
					.to("jpa://cl.psep.api.model.Comuna?namedQuery=Comuna.findAll&consumeDelete=false")
					//.setBody(simple("asdasd"))
		.endRest();
	}

}
