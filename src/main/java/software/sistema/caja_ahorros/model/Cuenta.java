package software.sistema.caja_ahorros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "cuenta")
public class Cuenta {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = Cuenta.class)
    @Column(name = "numero_cuenta")
    private int numeroCuenta;

    @NotBlank(groups = Cuenta.class)
    @Column(name = "saldo", precision = 5, scale = 2)
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    @JsonIgnore
    private Socio socio;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Transaccion> transacciones;


    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal  saldo) {
        this.saldo = saldo;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    //toString
    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", numeroCuenta=" + numeroCuenta +
                ", saldo=" + saldo +
                ", socio=" + socio +
                ", transaccion=" + transacciones +
                '}';
    }
}
