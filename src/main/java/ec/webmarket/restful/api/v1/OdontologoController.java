package ec.webmarket.restful.api.v1;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.CitaService;
import ec.webmarket.restful.service.crud.OdontologoService;


@RestController
@RequestMapping("/api/v1/odontologos")
public class OdontologoController {

	private final OdontologoService service;
    private final CitaService citaService;

    
    public OdontologoController(OdontologoService service, CitaService citaService) {
        this.service = service;
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OdontologoDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, service.create(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> getByCedula(@PathVariable String cedula) {
        Optional<OdontologoDTO> odontologoOpt = service.findByCedula(cedula);

        if (odontologoOpt.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDTO<>(true, odontologoOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDTO<>(false, "Odontólogo no encontrado"));
        }
    }
    
    @GetMapping("/{cedula}/citas")
    public ResponseEntity<?> getCitasByOdontologo(@PathVariable String cedula) {
        List<CitaDTO> citas = citaService.findByOdontologo(cedula);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citas), HttpStatus.OK);
    }

}
