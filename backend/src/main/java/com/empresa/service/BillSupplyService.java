package com.empresa.service;

import com.empresa.model.BillSupply;
import com.empresa.repository.BillSupplyRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillSupplyService {
    
    private final BillSupplyRepository billSupplyRepository;

    public BillSupplyService(BillSupplyRepository billSupplyRepository) {
        this.billSupplyRepository = billSupplyRepository;
    }

    public BillSupply saveBillSupply(BillSupply billSupply) {
        return billSupplyRepository.save(billSupply);
    }

    public BillSupply findById(Integer id) {
        return billSupplyRepository.findById(id).orElse(null);
    }

    public List<BillSupply> findBySupplierId(String supplierId) {
        return billSupplyRepository.findBySupplierId(supplierId);
    }

    public List<BillSupply> findByDateRange(Date startDate, Date endDate) {
        return billSupplyRepository.findByDateRange(startDate, endDate);
    }

    public void deleteBillSupply(Integer id) {
        billSupplyRepository.deleteById(id);
    }
}