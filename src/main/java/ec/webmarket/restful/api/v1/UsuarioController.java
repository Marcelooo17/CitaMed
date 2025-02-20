package ec.webmarket.restful.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.dto.v1.UsuarioDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, usuarioService.registrar(dto)), HttpStatus.CREATED);
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(new ApiResponseDTO<>(true, usuarioService.autenticar(dto)));
    }

    @PutMapping("/actualizar-contrasena")
    public ResponseEntity<?> actualizarContrasena(@RequestBody UsuarioDTO dto) {
        usuarioService.actualizarContrasena(dto);
        return ResponseEntity.ok(new ApiResponseDTO<>(true, "Contraseña actualizada correctamente"));
    }
}
