package br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Genero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroDAO extends CrudRepository<Genero, Integer> {
    Iterable<Genero> findByNomeGenero(String nomeGenero);
    
}
