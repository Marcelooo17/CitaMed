package ec.webmarket.restful.service.crud;

import java.time.LocalDate;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.persistence.HorarioRepository;


@Service
public class HorarioService {

    @Autowired
    private HorarioRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    public HorarioDTO create(HorarioDTO dto) {
        Horario horario = modelMapper.map(dto, Horario.class);
        return modelMapper.map(repository.save(horario), HorarioDTO.class);
    }
    
    public List<HorarioDTO> findByDisponibilidad(Boolean disponible) {
        return repository.findByDisponible(disponible)
                .stream()
                .map(horario -> modelMapper.map(horario, HorarioDTO.class))
                .toList();
    }

    public List<HorarioDTO> findByFecha(LocalDate fecha) {
        return repository.findByFecha(fecha)
                .stream()
                .map(horario -> modelMapper.map(horario, HorarioDTO.class))
                .toList();
    }

    public List<HorarioDTO> findByOdontologo(String odontologoCedula) {
        return repository.findByOdontologoCedula(odontologoCedula)
                .stream()
                .map(horario -> modelMapper.map(horario, HorarioDTO.class))
                .toList();
    }

    public HorarioDTO update(HorarioDTO dto) {
        if (!repository.existsById(dto.getId())) {
            throw new RuntimeException("Horario no encontrado");
        }
        Horario horario = modelMapper.map(dto, Horario.class);
        return modelMapper.map(repository.save(horario), HorarioDTO.class);
    }
}
