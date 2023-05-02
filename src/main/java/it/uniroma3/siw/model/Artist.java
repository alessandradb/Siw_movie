package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Artist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private LocalDate dateOfBirth;
	
	private String nationality;
	
	@ManyToMany(mappedBy="actor")
	private List<Movie> movie_actor;
	
	@OneToMany(mappedBy="director")
	private List<Movie> movie_director;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String nome) {
		this.name = nome;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String cognome) {
		this.surname = cognome;
	}
	

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Movie> getMovie_actor() {
		return movie_actor;
	}

	public void setMovie_actor(List<Movie> movie_actor) {
		this.movie_actor = movie_actor;
	}

	public List<Movie> getMovie_director() {
		return movie_director;
	}

	public void setMovie_director(List<Movie> movie_director) {
		this.movie_director = movie_director;
	}

	@Override
	public int hashCode() {
		return Objects.hash(surname, name, dateOfBirth, nationality);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(surname, other.surname) && Objects.equals(name, other.name) && Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(nationality, other.nationality);
	}

	
	
}
