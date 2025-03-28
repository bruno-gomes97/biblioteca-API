package br.com.biblioteca_api.controller;

import br.com.biblioteca_api.dto.UsuarioDTO;
import br.com.biblioteca_api.exceptions.RegraDeNegocioException;
import br.com.biblioteca_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO dto) throws RegraDeNegocioException {
        usuarioService.salvar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("cpf")
    public ResponseEntity<UsuarioDTO> obterPorCPF(@RequestParam String cpf) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.obterPorCpf(cpf), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodosUsuarios() throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.listarTodosUsuarios(), HttpStatus.OK);
    }

    @DeleteMapping("{cpf}")
    public ResponseEntity<String> deletar(@PathVariable String cpf) throws RegraDeNegocioException {
        usuarioService.deletar(cpf);
        return ResponseEntity.ok("Usuário removido!");
    }

    @PutMapping("nome")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestParam String nome,
                                                @Valid @RequestBody UsuarioDTO dto) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.atualizar(nome, dto), HttpStatus.OK);
    }
}
