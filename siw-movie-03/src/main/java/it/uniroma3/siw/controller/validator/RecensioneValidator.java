package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.RecensioneRepository;

@Component
public class RecensioneValidator implements Validator {
	@Autowired
	private RecensioneRepository recensioneRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Recensione rec = (Recensione)o;
		if (recensioneRepository.existsByUser(rec.getUser().getId())) {
			errors.reject("recensione.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Recensione.class.equals(aClass);
	}
}
