package br.com.biblioteca_api.entity;

import br.com.biblioteca_api.enums.Genero;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_livro")
public class LivroEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idLivro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "isbn")
    private String isbn;

    @JoinColumn(name = "id_usuario")
    @ManyToOne
    @JsonBackReference
    private UsuarioEntity usuario;
}
