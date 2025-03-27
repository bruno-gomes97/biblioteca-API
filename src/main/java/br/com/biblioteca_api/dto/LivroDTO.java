package br.com.biblioteca_api.dto;

import br.com.biblioteca_api.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroDTO implements Serializable {
    private String titulo;
    private String autor;
    private Genero genero;
    private LocalDate dataLancamento;
    private String isbn;
}
