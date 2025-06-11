package com.empresa.mapper;

import com.empresa.dto.request.BillSupplyRequestDTO;
import com.empresa.dto.response.BillSupplyDetailResponseDTO;
import com.empresa.dto.response.BillSupplyResponseDTO;
import com.empresa.model.BillSupply;
import com.empresa.model.BillSupplyDetail;
import com.empresa.model.Product;
import com.empresa.model.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T01:00:58-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class BillSupplyMapperImpl implements BillSupplyMapper {

    @Override
    public BillSupply toEntity(BillSupplyRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BillSupply billSupply = new BillSupply();

        billSupply.setSupplier( mapSupplier( dto.getSupplierId() ) );

        billSupply.setDate( new java.util.Date() );

        return billSupply;
    }

    @Override
    public BillSupplyResponseDTO toDto(BillSupply entity) {
        if ( entity == null ) {
            return null;
        }

        BillSupplyResponseDTO billSupplyResponseDTO = new BillSupplyResponseDTO();

        billSupplyResponseDTO.setSupplierId( entitySupplierId( entity ) );
        billSupplyResponseDTO.setSupplierCompany( entitySupplierCompany( entity ) );
        billSupplyResponseDTO.setIdBillSupply( entity.getIdBillSupply() );
        billSupplyResponseDTO.setDate( entity.getDate() );
        billSupplyResponseDTO.setTotal( entity.getTotal() );
        billSupplyResponseDTO.setDetails( billSupplyDetailListToBillSupplyDetailResponseDTOList( entity.getDetails() ) );

        billSupplyResponseDTO.setSupplierName( getSupplierFullName(entity.getSupplier()) );

        return billSupplyResponseDTO;
    }

    @Override
    public BillSupplyDetailResponseDTO detailToDto(BillSupplyDetail detail) {
        if ( detail == null ) {
            return null;
        }

        BillSupplyDetailResponseDTO billSupplyDetailResponseDTO = new BillSupplyDetailResponseDTO();

        billSupplyDetailResponseDTO.setProductId( detailProductIdProduct( detail ) );
        billSupplyDetailResponseDTO.setProductName( detailProductName( detail ) );
        billSupplyDetailResponseDTO.setQuantityProduct( detail.getQuantityProduct() );
        billSupplyDetailResponseDTO.setUnitPrice( detail.getUnitPrice() );
        billSupplyDetailResponseDTO.setSubTotal( detail.getSubTotal() );

        return billSupplyDetailResponseDTO;
    }

    private String entitySupplierId(BillSupply billSupply) {
        if ( billSupply == null ) {
            return null;
        }
        Supplier supplier = billSupply.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entitySupplierCompany(BillSupply billSupply) {
        if ( billSupply == null ) {
            return null;
        }
        Supplier supplier = billSupply.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String company = supplier.getCompany();
        if ( company == null ) {
            return null;
        }
        return company;
    }

    protected List<BillSupplyDetailResponseDTO> billSupplyDetailListToBillSupplyDetailResponseDTOList(List<BillSupplyDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<BillSupplyDetailResponseDTO> list1 = new ArrayList<BillSupplyDetailResponseDTO>( list.size() );
        for ( BillSupplyDetail billSupplyDetail : list ) {
            list1.add( detailToDto( billSupplyDetail ) );
        }

        return list1;
    }

    private String detailProductIdProduct(BillSupplyDetail billSupplyDetail) {
        if ( billSupplyDetail == null ) {
            return null;
        }
        Product product = billSupplyDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String idProduct = product.getIdProduct();
        if ( idProduct == null ) {
            return null;
        }
        return idProduct;
    }

    private String detailProductName(BillSupplyDetail billSupplyDetail) {
        if ( billSupplyDetail == null ) {
            return null;
        }
        Product product = billSupplyDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
