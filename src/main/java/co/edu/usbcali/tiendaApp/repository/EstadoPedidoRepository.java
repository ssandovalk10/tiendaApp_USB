package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido,Integer> {

    Optional<EstadoPedido> findByDescripcionIgnoreCase(String descripcion);
    List<EstadoPedido> findAllByOrderByDescripcionAsc();

    boolean existsByDescripcionIgnoreCaseAndIdNot(String descripcion, Integer id);

}
