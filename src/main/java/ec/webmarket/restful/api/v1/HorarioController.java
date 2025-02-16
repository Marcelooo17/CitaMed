package ec.webmarket.restful.api.v1;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.HorarioService;


@RestController
@RequestMapping("/api/v1/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody HorarioDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.create(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> getByFecha(@PathVariable String fecha) {
        LocalDate parsedDate = LocalDate.parse(fecha);
        List<HorarioDTO> horarios = service.findByFecha(parsedDate);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }

    @GetMapping("/odontologo/{cedula}")
    public ResponseEntity<?> getByOdontologo(@PathVariable String cedula) {
        List<HorarioDTO> horarios = service.findByOdontologo(cedula);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }
    
    @GetMapping("/disponibles/{disponible}")
    public ResponseEntity<?> getHorariosPorDisponibilidad(@PathVariable Boolean disponible) {
        List<HorarioDTO> horarios = service.findByDisponibilidad(disponible);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody HorarioDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.update(dto)), HttpStatus.OK);
    }
}
