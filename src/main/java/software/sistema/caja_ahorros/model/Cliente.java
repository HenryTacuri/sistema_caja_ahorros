package software.sistema.caja_ahorros.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "cliente")
public class Cliente {

    //Atributos
    @Id
    @NotBlank(groups = Cliente.class)
    @Column(name = "cli_cedula", length = 10)
    private String cedula;

    @NotBlank(groups = Cliente.class)
    @Column(name = "cli_nombre", length = 200)
    private String nombre;

    //Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Cliente{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}

