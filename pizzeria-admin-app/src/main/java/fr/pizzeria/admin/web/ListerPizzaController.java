package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class ListerPizzaController
 */
@WebServlet("/pizzas/list")
public class ListerPizzaController extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(ListerPizzaController.class.toString());
	
	@Inject private PizzaService pizzaService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerPizzaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("LIST PIZZAS");
		List<Pizza> pizzas;
		try {
			pizzas = pizzaService.findAllPizzas().stream().sorted(Comparator.comparing(Pizza::getCode)).collect(Collectors.toList()); // sorted(Comparator.comparing(Pizza::getCode))
			request.setAttribute("pizzas", pizzas);
		} catch (DaoException e) {
			response.sendError(500, "Erreur :(");
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
