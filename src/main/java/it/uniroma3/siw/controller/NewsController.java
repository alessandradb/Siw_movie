package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.News;
import it.uniroma3.siw.repository.NewsRepository;

@Controller
public class NewsController {
	
	@Autowired NewsRepository newsRepository;
	
	@GetMapping("/formNewNews")
	public String formNewNews(Model model) {
		model.addAttribute("news", new News());
		return "formNewNews.html";
	}
	
	@GetMapping("/generalNews")
	public String showNews(Model model) {
		model.addAttribute("generalNews", this.newsRepository.findAll());
		return "generalNews.html";
	}
	
	@GetMapping("/news/{id}")
	public String getNew(@PathVariable("id") Long id,Model model) {
		model.addAttribute("news", this.newsRepository.findById(id).get());
		return "news.html";
	}
	
	@PostMapping("/generalNews")
	public String newNews(@ModelAttribute("news") News news, Model model) {
		if(!newsRepository.existsByDateAndTitle(news.getDate(), news.getTitle())) {
			this.newsRepository.save(news);
			model.addAttribute("news", news);
			return "news.html";
		}
		else {
			model.addAttribute("messaggioErrore", "Questa news esiste già");
			return "formNewNews.html";
		}
	}

}
