package com.estudo.azureservicebus.producer.repository;

import com.estudo.azureservicebus.producer.domain.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
