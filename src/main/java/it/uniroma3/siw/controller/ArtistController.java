package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;

@Controller
public class ArtistController {
	
	@Autowired ArtistRepository artistRepository;
	
	@GetMapping("/formNewArtist")
	public String formNewArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "formNewArtist.html";
	}
	
	@GetMapping("/artists")
	public String showArtist(Model model) {
		model.addAttribute("artists", this.artistRepository.findAll());
		return "artists.html";
	}
	
	@GetMapping("/artist/{id}")
	public String getArtist(@PathVariable("id") Long id,Model model) {
		model.addAttribute("artist", this.artistRepository.findById(id).get());
		return "artist.html";
	}
	
	@PostMapping("/artists")
	public String newArtist(@ModelAttribute("artist") Artist artist, Model model) {
		if(!artistRepository.existsByNameAndSurnameAndDateOfBirthAndNationality(artist.getName(), artist.getSurname(), artist.getDateOfBirth(), artist.getNationality())) {
			this.artistRepository.save(artist);
			model.addAttribute("artist", artist);
			return "artist.html";
		}
		else {
			model.addAttribute("messaggioErrore", "Questo artista esiste già");
			return "formNewArtist.html";
		}
	}

}
