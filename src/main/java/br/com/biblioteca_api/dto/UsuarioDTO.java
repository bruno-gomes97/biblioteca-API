package br.com.biblioteca_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO implements Serializable {
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;
}
