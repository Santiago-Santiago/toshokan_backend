package pe.com.toshokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @Column(name = "user_id")
    private String id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 70, message = "El nombre debe tener entre 3 y 70 caracteres")
    @Column(name = "user_nom", length = 45)
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 70, message = "El apellido debe tener entre 3 y 70 caracteres")
    @Column(name = "user_apell", length = 45)
    private String apellido;
    
    @NotBlank(message = "El celular es obligatorio")
    @Size(min = 10, max = 15, message = "El celular debe tener entre 10 y 15 caracteres")
    @Column(name = "user_celu", length = 15)
    private String celular;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Size(max = 70, message = "El email debe tener máximo 70 caracteres")
    @Column(name = "user_email", length = 70)
    private String email;
    
    @NotBlank(message = "El username es obligatorio")
    @Size(min = 3, max = 45, message = "El username debe tener entre 3 y 45 caracteres")
    @Column(name = "username", length = 45)
    private String username;
    
    @NotBlank(message = "El password es obligatorio")
    @Size(min = 8, max = 70, message = "El password debe tener entre 8 y 70 caracteres")
    @Column(name = "password", length = 70)
    private String password;
}
