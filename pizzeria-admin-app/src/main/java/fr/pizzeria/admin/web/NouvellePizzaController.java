package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class NouvellePizzaController
 */
@WebServlet("/pizzas/new")
public class NouvellePizzaController extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(NouvellePizzaController.class.toString());
	
	@Inject private PizzaService pizzaService;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("CategoriePizza", CategoriePizza.values());
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/nouvellePizza.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String nom = request.getParameter("nom");
		String prix = request.getParameter("prix");
		String categorie = request.getParameter("categorie");
				
		 
		if (StringUtils.isBlank(code) || StringUtils.isBlank(nom) || StringUtils.isBlank(prix) || StringUtils.isBlank(categorie)) {
			LOG.info("J'ai bien recu le POST mais un des paramètres requis est vide");
		} else {
			Pizza newPizza = new Pizza(code, nom, Double.parseDouble(prix), CategoriePizza.valueOf(categorie));
			try {
				pizzaService.savePizza(newPizza);
				response.setStatus(201);
				LOG.info("J'ai bien recu le POST pour la pizza : " + newPizza.toString());
			} catch (DaoException e) {
				response.sendError(500, "Erreur :(");
			}
		}
		
		response.sendRedirect("list");
	}

}
