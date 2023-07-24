package it.uniroma3.siw.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Recensione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@NotNull
	private String title;
	@NotNull
	@Min(1)
	@Max(5)
	private Integer valutazione;
	private String text;
	@ManyToOne
	private Movie film;
	@ManyToOne
	private User user;
	
	public Recensione(User user, Movie movie) {
		this.id=null;
		this.title=null;
		this.text=null;
		this.film=movie;
		this.user=user;
	}
	
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
	public Integer getValutazione() {
		return valutazione;
	}
	public void setValutazione(Integer valutazione) {
		this.valutazione = valutazione;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Movie getFilm() {
		return film;
	}
	public void setFilm(Movie mov) {
		this.film = mov;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		return Objects.hash(text, title, valutazione);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return Objects.equals(text, other.text) && Objects.equals(title, other.title)
				&& Objects.equals(valutazione, other.valutazione);
	}
	
	

}
