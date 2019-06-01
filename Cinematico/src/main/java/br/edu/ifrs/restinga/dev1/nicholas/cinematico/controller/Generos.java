package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.GeneroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Generos {
    
    @Autowired
    GeneroDAO generoDAO;
    
    
    
}
