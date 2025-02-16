package ec.webmarket.restful.service.crud;



import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.persistence.OdontologoRepository;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    public OdontologoDTO create(OdontologoDTO dto) {
        Odontologo odontologo = modelMapper.map(dto, Odontologo.class);
        return modelMapper.map(repository.save(odontologo), OdontologoDTO.class);
    }

    public Optional<OdontologoDTO> findByCedula(String cedula) {
        return repository.findById(cedula).map(odontologo -> modelMapper.map(odontologo, OdontologoDTO.class));
    }
}
