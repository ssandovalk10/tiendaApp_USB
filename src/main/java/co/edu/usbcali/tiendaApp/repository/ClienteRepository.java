package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    boolean existsByTipoDocumentoIdAndDocumento(Integer tipoDocumentoId, String documento);
}
