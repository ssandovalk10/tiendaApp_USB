package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.DetallePedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    boolean existsByPedidoIdAndProductoId(Integer pedidoId, Integer productoId);
    List<DetallePedido> findByPedidoId(Integer id);

}
