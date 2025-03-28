package br.com.biblioteca_api.controller;

import br.com.biblioteca_api.dto.LivroDTO;
import br.com.biblioteca_api.enums.Genero;
import br.com.biblioteca_api.exceptions.RegraDeNegocioException;
import br.com.biblioteca_api.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroDTO> salvar( @Valid @RequestBody LivroDTO dto) throws RegraDeNegocioException {
        return new ResponseEntity<>(livroService.salvar(dto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroDTO> obterPeloId(@PathVariable UUID id) throws RegraDeNegocioException {
        return new ResponseEntity<>(livroService.obterPorId(id), HttpStatus.OK);
    }

    @GetMapping("/genero")
    public ResponseEntity<List<LivroDTO>> obterPorGenero(@RequestParam Genero genero) throws RegraDeNegocioException {
        return new ResponseEntity<>(livroService.obterPorGenero(genero), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodosLivros() {
        return new ResponseEntity<>(livroService.listarTodosLivros(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) throws RegraDeNegocioException {
        livroService.deletar(id);
        return ResponseEntity.ok("Livro removido com sucesso!");
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable UUID id,
                                              @Valid @RequestBody LivroDTO dto) throws RegraDeNegocioException {
        return new ResponseEntity<>(livroService.atualizar(id, dto), HttpStatus.NO_CONTENT);
    }

    @PutMapping("atualizar")
    public ResponseEntity<LivroDTO> atualizarLivroComUsuario(@RequestParam String nomeLivro,
                                                             @RequestParam String cpf) throws RegraDeNegocioException {
        return new ResponseEntity<>(livroService.autalizarLivroComUsuario(nomeLivro, cpf), HttpStatus.OK);
    }
}
