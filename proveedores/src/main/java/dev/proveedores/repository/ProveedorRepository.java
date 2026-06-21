package dev.proveedores.repository;

import dev.proveedores.model.proveedor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<proveedor, Integer> {
    @Override
    List<proveedor> findAll(Sort sort);

}
