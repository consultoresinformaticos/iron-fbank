package py.edu.una.ironbank.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import py.edu.una.ironbank.entity.House;
import py.edu.una.ironbank.service.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService houseService;
	private static final Logger LOGGER = LoggerFactory.getLogger(HouseController.class);

	@GetMapping("/")
	public ModelAndView getHouse() {
		LOGGER.info("get");
		ModelAndView mv = new ModelAndView("house");
		mv.addObject("house", new House());
		mv.addObject("houses", houseService.getHouse());
		return mv;
	}
	
	@PostMapping("/add")
	public String addHouse(@Valid @ModelAttribute("house") House house){
		try {
			houseService.save(house);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:/house/";
	}
	
}
