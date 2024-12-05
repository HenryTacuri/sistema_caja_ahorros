package software.sistema.caja_ahorros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity(name = "usuario")
public class Usuario {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = Usuario.class)
    @Column(name = "correo", length = 200)
    private String correo;

    @NotBlank(groups = Usuario.class)
    @Column(name = "contrasenia", length = 200)
    private String contrasenia;

    @NotBlank(groups = Usuario.class)
    @Column(name = "tipo", length = 200)
    private String tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_socio")
    private Socio socio;

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", tipo='" + tipo + '\'' +
                ", socio=" + socio +
                '}';
    }
}

