package cl.psep.api;

import javax.ws.rs.core.MediaType;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.psep.api.model.Comuna;
import cl.psep.api.model.Provincia;
import cl.psep.api.model.Region;

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
				.outType(Comuna[].class)
				.route()
					.to("jpa://cl.psep.api.model.Comuna?namedQuery=Comuna.findAll&consumeDelete=false")
		.endRest();
		
		rest("/provincias").description("Servicio de provincias")
			.get()
				.description("Lista todas las provincias")
				.produces(MediaType.APPLICATION_JSON)
				.outType(Provincia[].class)
				.route()
					.to("jpa://cl.psep.api.model.Provincia?namedQuery=Provincia.findAll&consumeDelete=false")
		.endRest();
		
		rest("/regiones").description("Servicios de regiones")
			.get()
				.description("Lista todas las regiones")
				.produces(MediaType.APPLICATION_JSON)
				.outType(Region[].class)
				.route()
					.to("jpa://cl.psep.api.model.Region?namedQuery=Region.findAll&consumeDelete=false")
		.endRest();
	}

}
