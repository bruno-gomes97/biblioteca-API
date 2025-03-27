package br.com.biblioteca_api.controller;

import br.com.biblioteca_api.dto.LivroDTO;
import br.com.biblioteca_api.enums.Genero;
import br.com.biblioteca_api.service.LivroService;
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
    public ResponseEntity<LivroDTO> salvar(@RequestBody LivroDTO dto) {
        livroService.salvar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroDTO> obterPeloId(@PathVariable UUID id) {
        return new ResponseEntity<>(livroService.obterPorId(id), HttpStatus.OK);
    }

    @GetMapping("/genero")
    public ResponseEntity<List<LivroDTO>> obterPorGenero(@RequestParam Genero genero) {
        return new ResponseEntity<>(livroService.obterPorGenero(genero), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodosLivros() {
        return new ResponseEntity<>(livroService.listarTodosLivros(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable UUID id,
                                              @RequestBody LivroDTO dto) {
        return new ResponseEntity<>(livroService.atualizar(id, dto), HttpStatus.NO_CONTENT);
    }
}
