package com.desafio.softdesign.controller;

import com.desafio.softdesign.domain.livro.Livro;
import com.desafio.softdesign.domain.livro.LivroDTO;
import com.desafio.softdesign.service.livro.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
