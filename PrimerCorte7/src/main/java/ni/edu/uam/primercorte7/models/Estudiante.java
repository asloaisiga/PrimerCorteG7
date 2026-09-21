package ni.edu.uam.primercorte7.models;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    private String codigo;
    private String nombres;
    private String apellidos;
    private String carrera;
    private LocalDate fechaNacimiento;
}