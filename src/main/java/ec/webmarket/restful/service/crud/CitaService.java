package ec.webmarket.restful.service.crud;



import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.persistence.CitaRepository;


@Service
public class CitaService {

    @Autowired
    private CitaRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    public CitaDTO create(CitaDTO dto) {
        Cita cita = modelMapper.map(dto, Cita.class);
        return modelMapper.map(repository.save(cita), CitaDTO.class);
    }

    public Optional<CitaDTO> findById(Long id) {
        return repository.findById(id).map(cita -> modelMapper.map(cita, CitaDTO.class));
    }

    public List<CitaDTO> findByPaciente(String pacienteCedula) {
        return repository.findByPacienteCedula(pacienteCedula)
                .stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .toList();
    }

    public List<CitaDTO> findByOdontologo(String odontologoCedula) {
        return repository.findByOdontologoCedula(odontologoCedula)
                .stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .toList();
    }

    public CitaDTO update(CitaDTO dto) {
        if (!repository.existsById(dto.getId())) {
            throw new RuntimeException("Cita no encontrada");
        }
        Cita cita = modelMapper.map(dto, Cita.class);
        return modelMapper.map(repository.save(cita), CitaDTO.class);
    }

    public void cancel(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cita no encontrada");
        }
        repository.deleteById(id);
    }
}
