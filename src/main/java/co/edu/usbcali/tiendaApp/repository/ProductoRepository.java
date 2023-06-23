package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByCategoriaIdAndReferencia(Integer categoriaId, String referencia);
}
