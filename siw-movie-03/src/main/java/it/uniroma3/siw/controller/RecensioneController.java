package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.RecensioneValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.MovieRepository;
import it.uniroma3.siw.repository.RecensioneRepository;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class RecensioneController {
	@Autowired
	private RecensioneRepository recensioneRepository;
	@Autowired
	private RecensioneValidator recensioneValidator;
	@Autowired 
	private CredentialsService credentialsService;
	@Autowired 
	private MovieRepository movieRepository;
	
	@GetMapping(value="{id}/formNewRecensione")
	public String formNewRecensione(Model model, @PathVariable("id") Long id) {
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		Movie movie = this.movieRepository.findById(id).get();
		model.addAttribute("recensione", new Recensione(credentials.getUser(),movie));
		return "formNewRecensione.html";
		
	}
	
	@PostMapping("/recensione")
	public String newRecensione(@Valid @ModelAttribute("recensione") Recensione recensione, BindingResult bindingResult, Model model) {
		
		this.recensioneValidator.validate(recensione, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.recensioneRepository.save(recensione); 
			model.addAttribute("recensione", recensione);
			return "movie.html";
		} else {
			return "formNewRecensione.html"; 
		}
	}
	
	@GetMapping("/recensioni")
	public String getRecensioni(Model model, @RequestParam Movie movie, @PathVariable("id") Long id) {
		model.addAttribute("recensioni", this.recensioneRepository.findAllByFilm(movie));
		return "recensioniFilm.html";
	} 
	
	

}
