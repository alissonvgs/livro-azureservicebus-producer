package com.estudo.azureservicebus.producer.service.livro;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.estudo.azureservicebus.producer.domain.livro.Livro;
import com.estudo.azureservicebus.producer.domain.livro.LivroDTO;
import com.estudo.azureservicebus.producer.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private LivroRepository livroRepository;
    private ServiceBusSenderClient serviceBusSenderClient;

    @Autowired
    public LivroService(LivroRepository livroRepository, ServiceBusSenderClient serviceBusSenderClient) {
        this.livroRepository = livroRepository;
        this.serviceBusSenderClient = serviceBusSenderClient;
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

        serviceBusSenderClient.sendMessage(new ServiceBusMessage(livroDTO.toJson()));
        System.out.println("Mensagem enviada para a fila: " + livroDTO.toJson());
        return livro;
    }

    public List<Livro> findAllLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> findById(Long id) { return livroRepository.findById(id); }

}
