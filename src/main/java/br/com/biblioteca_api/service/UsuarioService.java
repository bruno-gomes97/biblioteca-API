package br.com.biblioteca_api.service;

import br.com.biblioteca_api.dto.LivroDTO;
import br.com.biblioteca_api.dto.UsuarioDTO;
import br.com.biblioteca_api.entity.LivroEntity;
import br.com.biblioteca_api.entity.UsuarioEntity;
import br.com.biblioteca_api.exceptions.RegraDeNegocioException;
import br.com.biblioteca_api.repository.LivroRepository;
import br.com.biblioteca_api.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    private final ObjectMapper objectMapper;

    public UsuarioDTO salvar(UsuarioDTO dto) {
        UsuarioEntity usuario = objectMapper.convertValue(dto, UsuarioEntity.class);
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);
        return objectMapper.convertValue(usuarioSalvo, UsuarioDTO.class);
    }

    public UsuarioDTO obterPorCpf(String cpf) {
        Optional<UsuarioEntity> buscarUsuario = usuarioRepository.findByCpf(cpf);

        if (!buscarUsuario.isPresent()) {
            throw new RegraDeNegocioException("Usuário não encontrado!");
        }

        return objectMapper.convertValue(buscarUsuario, UsuarioDTO.class);
    }

    public void deletar(String cpf) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByCpf(cpf);

        if(usuario.isPresent()) {
            UsuarioEntity entity = objectMapper.convertValue(usuario, UsuarioEntity.class);
            usuarioRepository.delete(entity);
        } else  {
            throw new RegraDeNegocioException("Usuário não encontrado!");
        }
    }

    public UsuarioDTO atualizar (String nome, UsuarioDTO dto) {
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findByNome(nome);

        if (!optionalUsuario.isPresent()) {
            throw new RegraDeNegocioException("Usuário não encontrado!");
        }

        UsuarioEntity usuario = optionalUsuario.get();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setEndereco(dto.getEndereco());
        usuario.setTelefone(dto.getTelefone());

        return objectMapper.convertValue(usuarioRepository.save(usuario), UsuarioDTO.class);
    }

    public List<UsuarioDTO> listarTodosUsuarios() {
        List<UsuarioEntity> lista = usuarioRepository.findAll();

        if (lista.isEmpty()) {
            throw new RegraDeNegocioException("A lista está vazia!");
        }

        return lista.stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

}
