package ec.webmarket.restful.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.dto.v1.UsuarioDTO;
import ec.webmarket.restful.persistence.UsuarioRepository;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public UsuarioDTO registrar(UsuarioDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public String autenticar(UsuarioDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(dto.getNombreUsuario());
        if (usuarioOpt.isPresent() && usuarioOpt.get().getClave().equals(dto.getClave())) {
            return "Autenticación exitosa";
        }
        throw new RuntimeException("Credenciales inválidas");
    }

    public void actualizarContrasena(UsuarioDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(dto.getNombreUsuario());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setClave(dto.getClave());
            usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
}
