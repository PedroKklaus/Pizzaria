package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.FuncionarioDTO;
import br.com.uniamerica.pizzaria.pizarria.dto.UsuarioDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.FuncionarioEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaUsuario (final UsuarioDTO usuarioDTO){

        var usuario = new UsuarioEntity();
        BeanUtils.copyProperties(usuarioDTO,usuario);

        Assert.isTrue(!usuario.getNomeUsuario().equals(""), "Nome de usuário não pode ser nulo");
        Assert.isTrue(usuario.getNomeUsuario().length() <= 100, "Nome de usuário acima do limite de caracteres");
        Assert.isTrue(!usuario.getCpf().equals(""), "CPF não pode ser nulo");
        Assert.isTrue(!usuario.getEndereco().equals(""), "Endereco não pode ser nulo");

        this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaUsuario (final UsuarioEntity usuario){
        Assert.isTrue(!usuario.getNomeUsuario().equals(""), "Nome de usuário não pode ser nulo");
        Assert.isTrue(usuario.getNomeUsuario().length() <= 100, "Nome de usuário acima do limite de caracteres");
        Assert.isTrue(!usuario.getCpf().equals(""), "CPF não pode ser nulo");
        Assert.isTrue(!usuario.getEndereco().equals(""), "Endereco não pode ser nulo");

        this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletaUsuario (final Long id){

        final UsuarioEntity usuario1 = this.usuarioRepository.findById(id).orElse(null);

        if (usuario1 == null || !usuario1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o usuário.");
        }

        this.usuarioRepository.delete(usuario1);
    }
}
