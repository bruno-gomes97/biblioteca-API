package br.com.biblioteca_api.controller;

import br.com.biblioteca_api.dto.UsuarioDTO;
import br.com.biblioteca_api.service.UsuarioService;
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
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO dto) {
        usuarioService.salvar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("cpf")
    public ResponseEntity<UsuarioDTO> obterPorCPF(@RequestParam String cpf) {
        return new ResponseEntity<>(usuarioService.obterPorCpf(cpf), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodosUsuarios() {
        return new ResponseEntity<>(usuarioService.listarTodosUsuarios(), HttpStatus.OK);
    }

    @DeleteMapping("{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        usuarioService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("nome")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestParam String nome,
                                                @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(usuarioService.atualizar(nome, dto), HttpStatus.OK);
    }
}
