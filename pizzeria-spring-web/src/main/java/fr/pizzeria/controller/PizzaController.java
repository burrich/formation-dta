package fr.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.repo.IPizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired IPizzaRepository pizzaRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Pizza> get() {
		return pizzaRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Pizza post(@RequestBody Pizza jsonToPizza) {
		return pizzaRepo.save(jsonToPizza);
	}
}
