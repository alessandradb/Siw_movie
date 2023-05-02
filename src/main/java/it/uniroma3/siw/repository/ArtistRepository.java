package it.uniroma3.siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
	
	public List<Artist> findByNameAndSurname(String name, String surname);
	
	public boolean existsByNameAndSurnameAndDateOfBirthAndNationality(String name, String surname, LocalDate dateOfBirth, String nationality);

}