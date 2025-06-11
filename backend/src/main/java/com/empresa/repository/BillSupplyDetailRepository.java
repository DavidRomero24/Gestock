package com.empresa.repository;

import com.empresa.model.BillSupplyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillSupplyDetailRepository extends JpaRepository<BillSupplyDetail, BillSupplyDetail.BillSupplyDetailId> {

    /**
     * Busca todos los detalles de una factura de proveedor específica
     * @param billSupplyId ID de la factura de proveedor
     * @return Lista de detalles de la factura
     */
    @Query("SELECT d FROM BillSupplyDetail d WHERE d.id.billSupplyId = :billSupplyId ORDER BY d.product.name")
    List<BillSupplyDetail> findByBillSupplyId(@Param("billSupplyId") Integer billSupplyId);

    /**
     * Busca todos los detalles que contengan un producto específico
     * @param productId ID del producto
     * @return Lista de detalles que contienen el producto
     */
    @Query("SELECT d FROM BillSupplyDetail d WHERE d.id.productId = :productId")
    List<BillSupplyDetail> findByProductId(@Param("productId") String productId);

    /**
     * Calcula la cantidad total comprada de un producto en todas las facturas
     * @param productId ID del producto
     * @return Suma total de cantidades compradas
     */
    @Query("SELECT SUM(d.quantityProduct) FROM BillSupplyDetail d WHERE d.id.productId = :productId")
    Integer sumQuantityByProductId(@Param("productId") String productId);


}