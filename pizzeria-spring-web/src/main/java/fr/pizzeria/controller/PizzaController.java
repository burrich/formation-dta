package fr.pizzeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@RequestMapping(method = RequestMethod.GET)
	public String helloPizzas() {
		return "index";
	}
}
