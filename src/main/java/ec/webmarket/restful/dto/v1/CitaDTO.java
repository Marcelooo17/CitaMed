package ec.webmarket.restful.dto.v1;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CitaDTO {

    private Long id;
    private String pacienteCedula;
    private String odontologoCedula;
    private Long horarioId;
    private LocalDateTime fechaHora;
    private String estado; 
    private String motivo;
}
