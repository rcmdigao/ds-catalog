package br.com.developer.dscatalog.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
}
