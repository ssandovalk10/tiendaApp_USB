package co.edu.usbcali.tiendaApp.repository;

import co.edu.usbcali.tiendaApp.domain.Cliente;
import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    boolean existsByTipoDocumentoIdAndDocumento(Integer tipoDocumentoId, String documento);
    List<Cliente> findByNombresLikeIgnoreCase(String nombres);
}
