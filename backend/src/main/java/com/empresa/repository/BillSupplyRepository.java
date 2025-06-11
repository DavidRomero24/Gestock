package com.empresa.repository;

import com.empresa.model.BillSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillSupplyRepository extends JpaRepository<BillSupply, Integer> {

    /**
     * Busca todas las facturas de un proveedor específico ordenadas por fecha descendente
     * @param supplierId ID del proveedor (ejemplo: "PROV-001")
     * @return Lista de facturas del proveedor
     */
    @Query("SELECT bs FROM BillSupply bs WHERE bs.supplier.id = :supplierId ORDER BY bs.date DESC")
    List<BillSupply> findBySupplierId(String supplierId);

    /**
     * Busca facturas dentro de un rango de fechas ordenadas por fecha descendente
     * @param startDate Fecha de inicio (ejemplo: "2023-01-01")
     * @param endDate Fecha de fin (ejemplo: "2023-12-31")
     * @return Lista de facturas en el rango especificado
     */
    @Query("SELECT bs FROM BillSupply bs WHERE bs.date BETWEEN :startDate AND :endDate ORDER BY bs.date DESC")
    List<BillSupply> findByDateRange(Date startDate, Date endDate);

    /**
     * Busca facturas por proveedor y rango de fechas (combinación de filtros)
     * @param supplierId ID del proveedor
     * @param startDate Fecha de inicio
     * @param endDate Fecha de fin
     * @return Lista de facturas filtradas
     */
    @Query("SELECT bs FROM BillSupply bs WHERE bs.supplier.id = :supplierId AND bs.date BETWEEN :startDate AND :endDate ORDER BY bs.date DESC")
    List<BillSupply> findBySupplierAndDateRange(String supplierId, Date startDate, Date endDate);

    /**
     * Verifica si existe una factura con el ID especificado
     * @param id ID de la factura a verificar
     * @return true si existe, false si no existe
     */
    boolean existsByIdBillSupply(Integer id);
}