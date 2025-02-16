package ec.webmarket.restful.api.v1;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.PacienteService;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PacienteDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.create(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> getByCedula(@PathVariable String cedula) {
        Optional<PacienteDTO> pacienteOpt = service.findByCedula(cedula);

        if (pacienteOpt.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDTO<>(true, pacienteOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDTO<>(false, "Paciente no encontrado"));
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PacienteDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.update(dto)), HttpStatus.OK);
    }
}
