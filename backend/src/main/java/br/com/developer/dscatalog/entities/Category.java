package br.com.developer.dscatalog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_category")
@AllArgsConstructor
@NoArgsConstructor
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
    private Instant registroDataCriacao;

    @Getter
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant registroDataUpdate;

    //@ManyToMany(mappedBy = "categories")
    //private List<Product> produtos = new ArrayList<>();


    @PrePersist
    public void prePersist(){
        registroDataCriacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        registroDataUpdate = Instant.now();
    }

}
