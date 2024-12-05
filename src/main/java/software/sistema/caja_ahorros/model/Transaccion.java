package software.sistema.caja_ahorros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "transaccion")
public class Transaccion {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = Transaccion.class)
    @Column(name = "tipo", length = 20)
    private String tipo;

    @NotBlank(groups = Transaccion.class)
    @Column(name = "fecha_pago")
    private Date fechaPago;

    @NotBlank(groups = Transaccion.class)
    @Column(name = "monto", precision = 5, scale = 2)
    private BigDecimal monto;

    @NotBlank(groups = Transaccion.class)
    @Column(name = "numero_cuenta_destino")
    private int numeroCuentaDestino;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonIgnore
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "diario_caja_id")
    @JsonIgnore
    private DiarioCaja diarioCaja;

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto( BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago( Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(int numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    //toString
    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", fechaPago=" + fechaPago +
                ", monto=" + monto +
                ", numeroCuentaDestino=" + numeroCuentaDestino +
                ", cuenta=" + cuenta +
                '}';
    }
}
