package software.sistema.caja_ahorros.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "socio")
public class Socio {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(groups = Usuario.class)
    @Column(name = "nombre", length = 200)
    private String nombre;
    @NotBlank(groups = Usuario.class)
    @NotBlank(groups = Socio.class)
    @Column(name = "cedula", length = 200)
    private String cedula;
    @NotBlank(groups = Usuario.class)
    @NotBlank(groups = Socio.class)
    @Column(name = "direccion", length = 200)
    private String direccion;
    @NotBlank(groups = Usuario.class)
    @NotBlank(groups = Socio.class)
    @Column(name = "telefono", length = 200)
    private String telefono;
    @OneToOne(mappedBy = "socio")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;

    //Getters y Setters
    public Long getId() {
        return id;
    }
    public List<Cuenta> getCuentas() {
        return cuentas;
    }
    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //toString
    @Override
    public String toString() {
        return "Socio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario=" + usuario +
                ", cuentas=" + cuentas +
                '}';
    }
}