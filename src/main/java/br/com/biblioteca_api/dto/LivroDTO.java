package br.com.biblioteca_api.dto;

import br.com.biblioteca_api.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "O titulo do livro não pode estar vazio.")
    private String titulo;
    @NotBlank(message = "O autor do livro não pode estar vazio.")
    private String autor;
    @NotNull(message = "O gênero não pode ser nulo")
    private Genero genero;
    @NotNull(message = "Campo obrigatório")
    private LocalDate dataLancamento;
    @NotBlank(message = "O isbn do livro não pode estar vazio.")
    private String isbn;
}
