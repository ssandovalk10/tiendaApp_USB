package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido,Integer> {
}
