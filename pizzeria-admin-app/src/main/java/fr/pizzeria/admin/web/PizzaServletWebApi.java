package fr.pizzeria.admin.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class PizzaServletWebApi
 */
public class PizzaServletWebApi extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PizzaServletWebApi.class.toString());
	
	private IPizzaDao pizzaDao = new PizzaDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServletWebApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Pizza> pizzas = pizzaDao.findAllPizzas();
			response.getWriter().append(pizzas.toString());
			response.setStatus(200);
		} catch (DaoException e) {
			response.sendError(500, "Erreur :(");
		}
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
				pizzaDao.savePizza(newPizza);
				response.setStatus(201);
				LOG.info("J'ai bien recu le POST pour la pizza : " + newPizza.toString());
			} catch (DaoException e) {
				response.sendError(500, "Erreur :(");
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String data = br.readLine();
		LOG.info(data);
		
		Map<String, String> paramMap = new HashMap<>();
		String[] parameters = data.split("&");
		for (String param : parameters) {
			paramMap.put(param.split("=")[0], param.split("=")[1]);
		}
		LOG.info("map : " + paramMap);
		
		String code = paramMap.get("code");
		String nom = paramMap.get("nom");
		String prix = paramMap.get("prix");
		String categorie = paramMap.get("categorie");
		
		if (StringUtils.isBlank(code) || StringUtils.isBlank(nom) || StringUtils.isBlank(prix) || StringUtils.isBlank(categorie)) {
			LOG.info("J'ai bien recu le PUT mais un des paramètres requis est vide");
		} else {
			Pizza savedPizza = new Pizza(code, nom, Double.parseDouble(prix), CategoriePizza.valueOf(categorie));
			try {
				pizzaDao.updatePizza(code, savedPizza);
				response.setStatus(200);
				LOG.info("J'ai bien recu le PUT pour la pizza : " + savedPizza.toString());
			} catch (DaoException e) {
				response.sendError(500, "Erreur :(");
			}
		}
	}
}
