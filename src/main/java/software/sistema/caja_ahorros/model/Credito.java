package software.sistema.caja_ahorros.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity(name = "credito")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "fecha_acreditada", nullable = false)
    private Date fechaAcreditada;

    @Column(name = "fecha_liquidacion", nullable = false)
    private Date fechaLiquidacion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    public Long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public Date getFechaAcreditada() {
        return fechaAcreditada;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setFechaAcreditada(Date fechaAcreditada) {
        this.fechaAcreditada = fechaAcreditada;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Credito{" +
                "id=" + id +
                ", valor=" + valor +
                ", fechaAcreditada=" + fechaAcreditada +
                ", fechaLiquidacion=" + fechaLiquidacion +
                ", cuenta=" + cuenta +
                '}';
    }
}
