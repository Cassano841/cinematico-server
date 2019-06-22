package br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeDAO extends CrudRepository<Filme, Integer>{
    Iterable<Filme> findByNomeFilmeContaining(String nomeFilme);
}
