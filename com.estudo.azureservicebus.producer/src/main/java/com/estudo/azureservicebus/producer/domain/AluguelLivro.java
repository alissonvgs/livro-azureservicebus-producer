package com.estudo.azureservicebus.producer.domain;

import com.estudo.azureservicebus.producer.domain.livro.Livro;
import com.estudo.azureservicebus.producer.domain.user.Usuario;
import jakarta.persistence.*;

import java.util.Date;

@Table(name = "alugueis")
@Entity
public class AluguelLivro {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Date dataInicio;
    private Date dataFim;

    public AluguelLivro(Long id, Livro livro, Usuario usuario, Date dataInicio, Date dataFim) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public AluguelLivro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
