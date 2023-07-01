package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByCategoriaIdAndReferencia(Integer categoriaId, String referencia);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
    boolean existsById( Integer id);
    List<Producto> findByNombreLikeIgnoreCase(String nombre);
    List<Producto> findAllByOrderByNombreAsc();
}
