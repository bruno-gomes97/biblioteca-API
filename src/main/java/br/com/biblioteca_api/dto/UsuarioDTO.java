package br.com.biblioteca_api.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "O nome do usuário não pode estar vazio.")
    private String nome;
    @NotBlank(message = "O email do usuário não pode estar vazio.")
    private String email;
    @NotBlank(message = "O telefone do usuário não pode estar vazio.")
    private String telefone;
    @NotBlank(message = "O endereco do usuário não pode estar vazio.")
    private String endereco;
    @NotBlank(message = "O cpf do usuário não pode estar vazio.")
    private String cpf;
}
