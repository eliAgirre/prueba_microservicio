package com.ibm.repository;

import com.ibm.models.Proveedor;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    List<Proveedor> findByIdCliente(Integer id_cliente);
}
