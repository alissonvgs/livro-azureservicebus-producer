package com.desafio.softdesign.service.livro;

import com.desafio.softdesign.domain.livro.Livro;
import com.desafio.softdesign.domain.livro.LivroDTO;
import com.desafio.softdesign.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class LivroService {
    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro criarLivro(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setAutor(livroDTO.autor());
        livro.setTitulo(livroDTO.titulo());
        livro.setDescricao(livroDTO.descricao());
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toEpochSecond() * 1000);
        livro.setDataCriacao(date);
        livroRepository.save(livro);
        return livro;
    }

    public List<Livro> findAllLivros() {
        return livroRepository.findAll();
    }

}
