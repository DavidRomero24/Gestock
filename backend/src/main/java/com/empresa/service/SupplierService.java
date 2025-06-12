package com.empresa.service;

import com.empresa.dto.request.SupplierRequestDTO;
import com.empresa.dto.response.SupplierResponseDTO;
import java.util.List;

/**
 * Interfaz que define las operaciones del servicio para la gestión de proveedores.
 */
public interface SupplierService {

    /**
     * Crea un nuevo proveedor basado en los datos proporcionados.
     *
     * @param supplierDTO Datos del proveedor a crear.
     * @return El proveedor creado encapsulado en un DTO de respuesta.
     */
    SupplierResponseDTO createSupplier(SupplierRequestDTO supplierDTO);

    /**
     * Obtiene los detalles de un proveedor por su identificador único.
     *
     * @param id Identificador del proveedor.
     * @return DTO con los datos del proveedor encontrado.
     */
    SupplierResponseDTO getSupplierById(String id);

    /**
     * Obtiene una lista con todos los proveedores registrados.
     *
     * @return Lista de proveedores encapsulados en DTOs de respuesta.
     */
    List<SupplierResponseDTO> getAllSuppliers();

    /**
     * Actualiza los datos de un proveedor existente.
     *
     * @param id Identificador del proveedor a actualizar.
     * @param supplierDTO DTO con los nuevos datos del proveedor.
     * @return DTO con los datos actualizados del proveedor.
     */
    SupplierResponseDTO updateSupplier(String id, SupplierRequestDTO supplierDTO);

    /**
     * Elimina un proveedor del sistema.
     *
     * @param id Identificador del proveedor a eliminar.
     */
    void deleteSupplier(String id);
}