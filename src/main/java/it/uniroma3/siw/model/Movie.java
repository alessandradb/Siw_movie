package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;  //chiave primaria
	@NotBlank
	private String title;
	private String urlimage;
	@NotNull
	@Min(1900)
	@Max(2023)
	private Integer year;
	@OneToMany
	private List<News> news;
	@ManyToMany
	private List<Artist> actor;
	@ManyToOne
	private Artist director;
	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrlimage() {
		return urlimage;
	}
	
	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	
	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public List<Artist> getActor() {
		return actor;
	}

	public void setActor(List<Artist> actor) {
		this.actor = actor;
	}

	public Artist getDirector() {
		return director;
	}

	public void setDirector(Artist director) {
		this.director = director;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, year);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Movie other = (Movie) obj;
		return Objects.equals(title, other.title) && Objects.equals(year, other.year);
	}
	
	
	

}
