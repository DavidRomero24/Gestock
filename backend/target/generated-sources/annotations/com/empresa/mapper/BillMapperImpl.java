package com.empresa.mapper;

import com.empresa.dto.request.BillDetailRequestDTO;
import com.empresa.dto.request.BillRequestDTO;
import com.empresa.dto.request.PaymentRequestDTO;
import com.empresa.dto.request.ServiceDetailRequestDTO;
import com.empresa.dto.response.BillDetailResponseDTO;
import com.empresa.dto.response.BillResponseDTO;
import com.empresa.dto.response.PaymentResponseDTO;
import com.empresa.dto.response.ServiceDetailResponseDTO;
import com.empresa.model.Bill;
import com.empresa.model.BillDetail;
import com.empresa.model.Customer;
import com.empresa.model.Payment;
import com.empresa.model.ServiceDetail;
import com.empresa.model.Staff;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T08:42:13-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class BillMapperImpl extends BillMapper {

    @Autowired
    private BillDetailMapper billDetailMapper;
    @Autowired
    private ServiceDetailMapper serviceDetailMapper;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Bill toEntity(BillRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setCustomer( mapCustomer( dto.getCustomerId() ) );
        bill.setStaff( mapStaff( dto.getStaffId() ) );
        bill.setBillDetails( billDetailRequestDTOListToBillDetailList( dto.getBillDetails() ) );
        bill.setServiceDetails( serviceDetailRequestDTOListToServiceDetailList( dto.getServiceDetails() ) );
        bill.setPayments( paymentRequestDTOListToPaymentList( dto.getPayments() ) );
        bill.setDate( dto.getDate() );
        bill.setTotal( dto.getTotal() );
        bill.setStatus( dto.getStatus() );

        setDefaultValues( bill );

        return bill;
    }

    @Override
    public BillResponseDTO toDto(Bill bill) {
        if ( bill == null ) {
            return null;
        }

        BillResponseDTO billResponseDTO = new BillResponseDTO();

        billResponseDTO.setCustomerId( billCustomerId( bill ) );
        billResponseDTO.setStaffId( billStaffIdStaff( bill ) );
        billResponseDTO.setBillDetails( billDetailListToBillDetailResponseDTOList( bill.getBillDetails() ) );
        billResponseDTO.setDate( bill.getDate() );
        billResponseDTO.setId( bill.getId() );
        billResponseDTO.setPayments( paymentListToPaymentResponseDTOList( bill.getPayments() ) );
        billResponseDTO.setServiceDetails( serviceDetailListToServiceDetailResponseDTOList( bill.getServiceDetails() ) );
        billResponseDTO.setTotal( bill.getTotal() );

        billResponseDTO.setCustomerName( getCustomerFullName(bill.getCustomer()) );
        billResponseDTO.setStaffName( getStaffFullName(bill.getStaff()) );
        billResponseDTO.setPaidAmount( calculatePaidAmount(bill) );
        billResponseDTO.setPendingAmount( calculatePendingAmount(bill) );
        billResponseDTO.setStatus( determineStatus(bill) );

        return billResponseDTO;
    }

    protected List<BillDetail> billDetailRequestDTOListToBillDetailList(List<BillDetailRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<BillDetail> list1 = new ArrayList<BillDetail>( list.size() );
        for ( BillDetailRequestDTO billDetailRequestDTO : list ) {
            list1.add( billDetailMapper.toEntity( billDetailRequestDTO ) );
        }

        return list1;
    }

    protected List<ServiceDetail> serviceDetailRequestDTOListToServiceDetailList(List<ServiceDetailRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ServiceDetail> list1 = new ArrayList<ServiceDetail>( list.size() );
        for ( ServiceDetailRequestDTO serviceDetailRequestDTO : list ) {
            list1.add( serviceDetailMapper.toEntity( serviceDetailRequestDTO ) );
        }

        return list1;
    }

    protected List<Payment> paymentRequestDTOListToPaymentList(List<PaymentRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Payment> list1 = new ArrayList<Payment>( list.size() );
        for ( PaymentRequestDTO paymentRequestDTO : list ) {
            list1.add( paymentMapper.toEntity( paymentRequestDTO ) );
        }

        return list1;
    }

    private String billCustomerId(Bill bill) {
        if ( bill == null ) {
            return null;
        }
        Customer customer = bill.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String billStaffIdStaff(Bill bill) {
        if ( bill == null ) {
            return null;
        }
        Staff staff = bill.getStaff();
        if ( staff == null ) {
            return null;
        }
        String idStaff = staff.getIdStaff();
        if ( idStaff == null ) {
            return null;
        }
        return idStaff;
    }

    protected List<BillDetailResponseDTO> billDetailListToBillDetailResponseDTOList(List<BillDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<BillDetailResponseDTO> list1 = new ArrayList<BillDetailResponseDTO>( list.size() );
        for ( BillDetail billDetail : list ) {
            list1.add( billDetailMapper.toDto( billDetail ) );
        }

        return list1;
    }

    protected List<PaymentResponseDTO> paymentListToPaymentResponseDTOList(List<Payment> list) {
        if ( list == null ) {
            return null;
        }

        List<PaymentResponseDTO> list1 = new ArrayList<PaymentResponseDTO>( list.size() );
        for ( Payment payment : list ) {
            list1.add( paymentMapper.toDto( payment ) );
        }

        return list1;
    }

    protected List<ServiceDetailResponseDTO> serviceDetailListToServiceDetailResponseDTOList(List<ServiceDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<ServiceDetailResponseDTO> list1 = new ArrayList<ServiceDetailResponseDTO>( list.size() );
        for ( ServiceDetail serviceDetail : list ) {
            list1.add( serviceDetailMapper.toDto( serviceDetail ) );
        }

        return list1;
    }
}
