package br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Produtora;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoraDAO extends CrudRepository<Produtora, Integer>{
    
}
