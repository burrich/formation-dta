package fr.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.pizzeria.repo.IPerformanceRepository;

@Controller
@RequestMapping("/performance")
public class PerformanceController {
	
@Autowired IPerformanceRepository perfRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listPerformances(Model model) {
		model.addAttribute("performances", perfRepo.findAll());
		return "performances";
	}
	
	@RequestMapping(path="/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String delete(@RequestParam("id") Integer performanceId, Model model) {
        perfRepo.delete(performanceId);
        model.addAttribute("msg", "Performance supprimée");
        return "redirect:/mvc/performance";
    }

    @RequestMapping(path="/deleteall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String delete(Model model) {
        perfRepo.deleteAll();
        model.addAttribute("msg", "Toutes les données ont été supprimées");
        return "redirect:/mvc/performance";
    }
}
