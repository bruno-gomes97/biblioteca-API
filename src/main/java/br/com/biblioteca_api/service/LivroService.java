package br.com.biblioteca_api.service;

import br.com.biblioteca_api.dto.LivroDTO;
import br.com.biblioteca_api.entity.LivroEntity;
import br.com.biblioteca_api.entity.UsuarioEntity;
import br.com.biblioteca_api.enums.Genero;
import br.com.biblioteca_api.exceptions.RegraDeNegocioException;
import br.com.biblioteca_api.repository.LivroRepository;
import br.com.biblioteca_api.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public LivroDTO salvar(LivroDTO dto) throws RegraDeNegocioException {
        Optional<LivroEntity> optionalLivro = livroRepository.findByTitulo(dto.getTitulo());

        if(optionalLivro.isPresent()) {
            throw new RegraDeNegocioException("O livro já está cadastrado!", HttpStatus.BAD_REQUEST);
        }

        LivroEntity livroSalvo = optionalLivro.get();
        return objectMapper.convertValue(livroSalvo, LivroDTO.class);
    }

    public LivroDTO obterPorId(UUID id) throws RegraDeNegocioException {
        Optional<LivroEntity> optionalLivro = livroRepository.findById(id);

        if (!optionalLivro.isPresent()) {
            throw new RegraDeNegocioException("Id do livro não encontrado!", HttpStatus.BAD_REQUEST);
        }

        return objectMapper.convertValue(optionalLivro, LivroDTO.class);
    }

    public void deletar(UUID id) throws RegraDeNegocioException {
        Optional<LivroEntity> optionalLivro = livroRepository.findById(id);

        if(optionalLivro.isPresent()) {
            livroRepository.deleteById(id);
        } else {
            throw new RegraDeNegocioException("Id não encontrado!", HttpStatus.BAD_REQUEST);
        }
    }

    public List<LivroDTO> obterPorGenero(Genero genero) throws RegraDeNegocioException {
        List<LivroEntity> optionalLivro = livroRepository.findByGenero(genero);

        if(!optionalLivro.isEmpty()) {
            return optionalLivro.stream()
                    .map(livro -> objectMapper.convertValue(livro, LivroDTO.class))
                    .collect(Collectors.toList());
        } else  {
            throw new RegraDeNegocioException("Gênero não encontrado!", HttpStatus.BAD_REQUEST);
        }
    }

    public LivroDTO atualizar(UUID id, LivroDTO dto) throws RegraDeNegocioException {
        Optional<LivroEntity> optionalLivro = livroRepository.findById(id);

        if (!optionalLivro.isPresent()) {
            throw new RegraDeNegocioException("Livro não encontrado!", HttpStatus.BAD_REQUEST);
        }

        LivroEntity livro = optionalLivro.get();

        livro.setTitulo(dto.getTitulo());
        livro.setGenero(dto.getGenero());
        livro.setAutor(dto.getAutor());
        livro.setDataLancamento(dto.getDataLancamento());
        livro.setIsbn(dto.getIsbn());

        return objectMapper.convertValue(livroRepository.save(livro), LivroDTO.class);
    }

    public Page<LivroDTO> listarTodosLivros(Integer pagina, Integer tamanhoPagina) {
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);
        Page<LivroEntity> livroPage = livroRepository.findAll(pageRequest);

        return livroPage.map(livro -> objectMapper.convertValue(livro, LivroDTO.class));

    }

    public LivroDTO autalizarLivroComUsuario(String titulo, String cpf) throws RegraDeNegocioException {
        Optional<LivroEntity> optionalLivro = livroRepository.findByTitulo(titulo);
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findByCpf(cpf);


        if(optionalLivro.isEmpty()) {
            throw new RegraDeNegocioException("Livro não encontrado", HttpStatus.BAD_REQUEST);
        }

        if(optionalUsuario.isEmpty()) {
            throw new RegraDeNegocioException("Usuário não encontrado!", HttpStatus.BAD_REQUEST);
        }

        LivroEntity livro = optionalLivro.get();
        livro.setUsuario(optionalUsuario.get());

        return objectMapper.convertValue(livroRepository.save(livro), LivroDTO.class);
    }
}
