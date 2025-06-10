package com.empresa.service;

import com.empresa.model.BillDetail;
import com.empresa.repository.BillDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailService {
    
    private final BillDetailRepository billDetailRepository;

    public BillDetailService(BillDetailRepository billDetailRepository) {
        this.billDetailRepository = billDetailRepository;
    }

    public BillDetail saveBillDetail(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    public List<BillDetail> findByProductId(String productId) {
        return billDetailRepository.findByProductId(productId);
    }

    public List<BillDetail> findByBillId(Integer billId) {
        return billDetailRepository.findByBillId(billId);
    }

    public void deleteBillDetail(BillDetail.BillDetailId id) {
        billDetailRepository.deleteById(id);
    }
}