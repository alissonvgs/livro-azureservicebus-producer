package com.desafio.softdesign.domain.livro;

import java.util.Date;

public record LivroDTO(String autor, String titulo, String descricao, Date dataCriacao) {
}
