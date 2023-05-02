package it.uniroma3.siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.News;

public interface NewsRepository extends CrudRepository<News, Long> {
	
	public List<News> findByTitle(String title);
	public List<News> findByDate(LocalDate date);
	public List<News> findByTitleAndDate(String title, LocalDate date);
	
	public boolean existsByDateAndTitle(LocalDate date, String title);

}