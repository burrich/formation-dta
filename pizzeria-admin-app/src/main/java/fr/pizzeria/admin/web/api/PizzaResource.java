package fr.pizzeria.admin.web.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.Produces;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Path("/pizzas")
public class PizzaResource {

	@Inject private PizzaService pizzaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> findAllPizza() throws DaoException {
//		ResponseBuilder responseBuilder = Response.ok();
//		responseBuilder.entity(pizzaService.findAllPizzas());
//		responseBuilder.header("Access-Control-Allow-Origin",  "http://localhost");
//		return responseBuilder.build();
		
		return pizzaService.findAllPizzas();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void savePizza(Pizza newPizza) throws DaoException {
		pizzaService.savePizza(newPizza);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePizza(Pizza updatePizza) throws DaoException {
		pizzaService.updatePizza(updatePizza.getCode(), updatePizza);
	}
	
	@DELETE
	@Path("/{code}")
	public void deletePizza(@PathParam("code") String codePizza) throws DaoException {
		pizzaService.deletePizza(codePizza);
	}
}
