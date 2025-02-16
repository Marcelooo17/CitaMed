package ec.webmarket.restful.api.v1;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.CitaService;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CitaDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.create(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<CitaDTO> citaOpt = service.findById(id);

        if (citaOpt.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDTO<>(true, citaOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDTO<>(false, "Cita no encontrada"));
        }
    }


    @GetMapping("/paciente/{cedula}")
    public ResponseEntity<?> getByPaciente(@PathVariable String cedula) {
        List<CitaDTO> citas = service.findByPaciente(cedula);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citas), HttpStatus.OK);
    }

    @GetMapping("/odontologo/{cedula}")
    public ResponseEntity<?> getByOdontologo(@PathVariable String cedula) {
        List<CitaDTO> citas = service.findByOdontologo(cedula);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citas), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CitaDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.update(dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        service.cancel(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Cita cancelada"), HttpStatus.OK);
    }
}
