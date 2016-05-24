package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class ListerPizzaController
 */
public class ListerPizzaController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ListerPizzaController.class.toString());
	
	private IPizzaDao pizzaDao = new PizzaDaoImpl();
       
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
		List<Pizza> pizzas;
		try {
			pizzas = pizzaDao.findAllPizzas();
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
