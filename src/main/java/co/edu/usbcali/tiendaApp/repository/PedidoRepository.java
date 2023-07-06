package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    List<Pedido> findByClienteId(Integer id);
    List<Pedido> findByEstadoPedidoId(Integer id);
}
