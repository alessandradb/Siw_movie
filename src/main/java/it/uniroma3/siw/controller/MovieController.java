package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.MovieValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.MovieRepository;
import jakarta.validation.Valid;

@Controller
public class MovieController {
	
	@Autowired MovieRepository movieRepository;
	@Autowired ArtistRepository artistRepository;
	@Autowired MovieValidator movieValidator;
	
	@GetMapping("/formNewMovie")
	public String formNewMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "formNewMovie.html";
	}
	
	@GetMapping("/movie/{id}")
	public String getMovie(@PathVariable("id") Long id,Model model) {
		model.addAttribute("movie", this.movieRepository.findById(id).get());
		return "movie.html";
	}
	
	@GetMapping("/formUpdateMovie/{id}")
	public String formUpdateMovie(@PathVariable("id") Long id,Model model) {
		model.addAttribute("movie", this.movieRepository.findById(id).get());
		return "formUpdateMovie.html";
	}
	
	@GetMapping("/movies")
	public String showMovies(Model model) {
		model.addAttribute("movies", this.movieRepository.findAll());
		return "movies.html";
	}
	
	@GetMapping("/manageMovies")
	public String controlMovies(Model model) {
		model.addAttribute("movies", this.movieRepository.findAll());
		return "manageMovies.html";
	}
	
	@GetMapping("/formSearchMovies")
	public String formSearchMovie() {
		return "formSearchMovies.html";
	}
	
	@GetMapping("/indexMovie")
	public String indexMovie() {
		return "indexMovie.html";
	}
	
	@Valid
	@PostMapping("/movies")
	public String newMovie(@ModelAttribute("movie") Movie movie, BindingResult bindingResult, Model model) {
		this.movieValidator.validate(movie, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.movieRepository.save(movie);
			model.addAttribute("movie", movie);
			return "movie.html";
		}
		else {
			model.addAttribute("messaggioErrore", "Questo film esiste già");
			return "formNewMovie.html";
		}
	}
	
	@PostMapping("/searchMovies")
	public String searchMovies(@RequestParam Integer year, Model model) {
		model.addAttribute("movies", this.movieRepository.findByYear(year));
		return "foundMovies.html";
	}
	
	@GetMapping("/addDirectorToMovie/{id}")
	public String addDirector(@PathVariable("id") Long id,Model model) {
		model.addAttribute("artists", this.artistRepository.findAll());
		model.addAttribute("movie", this.movieRepository.findById(id).get());
		return "directorToAdd.html";
	}
	
	@GetMapping("/setDirectorToMovie/{idArtist}/{idMovie}")
	public String setDirector(@PathVariable("idArtist") Long idA,@PathVariable("idMovie") Long idM, Model model) {
		
		 Movie movie=this.movieRepository.findById(idM).get();
		 Artist artist=this.artistRepository.findById(idA).get();
		 
		 movie.setDirector(artist);
		 this.movieRepository.save(movie);
		 
		 model.addAttribute("movie",movie);
		 model.addAttribute("artist",artist);
		
		return "formUpdateMovie.html";
	}
	
	@GetMapping("/updateActorsOnMovie/{id}")
	public String updateActors(@PathVariable("id") Long id, Model model) {
		
		Movie movie=this.movieRepository.findById(id).get();
		List<Artist> notActorMovie = (List<Artist>) this.artistRepository.findAll();
		
		notActorMovie.removeAll(movie.getActor());
		
		model.addAttribute("movie",movie);
		model.addAttribute("movieActors",movie.getActor());
		model.addAttribute("notActorMovie",notActorMovie);
		
		return "actorsToAdd.html";
	}
	
	@GetMapping("/addActorToMovie/{idArtist}/{idMovie}")
	public String addActorToMovie(@PathVariable("idArtist") Long idA,@PathVariable("idMovie") Long idM, Model model) {
		
		Movie movie=this.movieRepository.findById(idM).get();
		Artist actor=this.artistRepository.findById(idA).get();
		
		movie.getActor().add(actor);
		actor.getMovie_actor().add(movie);
		
		this.movieRepository.save(movie);
		this.artistRepository.save(actor);
		
		List<Artist> notActorMovie = (List<Artist>) this.artistRepository.findAll();
		
		notActorMovie.removeAll(movie.getActor());
		
		model.addAttribute("movie",movie);
		model.addAttribute("movieActors",movie.getActor());
		model.addAttribute("notActorMovie",notActorMovie);
		
		return "actorsToAdd.html";
	}
	
	@GetMapping("/removeActorFromMovie/{idArtist}/{idMovie}")
	public String removeActorFromMovie(@PathVariable("idArtist") Long idA,@PathVariable("idMovie") Long idM, Model model) {
		
		Movie movie=this.movieRepository.findById(idM).get();
		Artist actor=this.artistRepository.findById(idA).get();
		
		movie.getActor().remove(actor);
		actor.getMovie_actor().remove(movie);
		
		this.movieRepository.save(movie);
		this.artistRepository.save(actor);
		
		List<Artist> notActorMovie = (List<Artist>) this.artistRepository.findAll();
		
		notActorMovie.removeAll(movie.getActor());
		
		model.addAttribute("movie",movie);
		model.addAttribute("movieActors",movie.getActor());
		model.addAttribute("notActorMovie",notActorMovie);
		
		return "actorsToAdd.html";
	}
}
