package ec.webmarket.restful.service.crud;


import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.persistence.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    public PacienteDTO create(PacienteDTO dto) {
        Paciente paciente = modelMapper.map(dto, Paciente.class);
        return modelMapper.map(repository.save(paciente), PacienteDTO.class);
    }

    public Optional<PacienteDTO> findByCedula(String cedula) {
        return repository.findById(cedula).map(paciente -> modelMapper.map(paciente, PacienteDTO.class));
    }

    public PacienteDTO update(PacienteDTO dto) {
        if (!repository.existsById(dto.getCedula())) {
            throw new RuntimeException("Paciente no encontrado");
        }
        Paciente paciente = modelMapper.map(dto, Paciente.class);
        return modelMapper.map(repository.save(paciente), PacienteDTO.class);
    }
}
