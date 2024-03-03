package com.estudo.azureservicebus.producer.controller;

import com.estudo.azureservicebus.producer.domain.livro.Livro;
import com.estudo.azureservicebus.producer.domain.livro.LivroDTO;
import com.estudo.azureservicebus.producer.service.livro.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/livro")
public class LivroController {

    private LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Livro> criarLivro(@RequestBody LivroDTO livroDTO) {
        Livro livro = livroService.criarLivro(livroDTO);
        return ResponseEntity.ok().body(livro);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> consultarTodosLivros() {
        List<Livro> livros = livroService.findAllLivros();
        return ResponseEntity.ok().body(livros);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Livro>> consultarTodosLivros(@PathVariable("id") Long id) {
        Optional<Livro> livro = livroService.findById(id);
        System.out.println(livro.toString());
        return ResponseEntity.ok().body(livro);
    }


}
