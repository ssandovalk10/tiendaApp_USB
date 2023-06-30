package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {

    boolean existsByDescripcionIgnoreCase(String descripcion);
    boolean existsByDescripcionIgnoreCaseAndIdNot(String descripcion, Integer id);
}
