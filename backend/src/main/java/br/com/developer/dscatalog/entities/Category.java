package br.com.developer.dscatalog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_categorias")
@Builder

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataCriacao;

    @Getter
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataAtualizacao;


    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @PrePersist
    public void prePersist(){
        dataCriacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        dataAtualizacao = Instant.now();
    }

}
