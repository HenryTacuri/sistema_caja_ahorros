package software.sistema.caja_ahorros.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import software.sistema.caja_ahorros.model.DiarioCaja;

import java.util.List;

public interface DiarioCajaRepository extends JpaRepository<DiarioCaja, Integer> {

    // Encuentra todos los registros por una fecha específica
    List<DiarioCaja> findByFecha(java.util.Date fecha);

    // Consulta personalizada para encontrar registros por rango de fechas
    @Query("SELECT d FROM diario_caja d WHERE d.fecha BETWEEN :startDate AND :endDate")
    List<DiarioCaja> findByFechaBetween(@Param("startDate") java.util.Date startDate, @Param("endDate") java.util.Date endDate);
}

