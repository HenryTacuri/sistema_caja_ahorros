package software.sistema.caja_ahorros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity(name = "diario_caja")
public class DiarioCaja {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToMany(mappedBy = "diarioCaja", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaccion> transacciones;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
    public void setInTransaccion(Transaccion transaccion){
        this.transacciones.add(transaccion);
    }

    // toString
    @Override
    public String toString() {
        return "DiarioCaja{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", transacciones=" + transacciones +
                '}';
    }
}
