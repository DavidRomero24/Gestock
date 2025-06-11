package com.empresa.controller;

import com.empresa.dto.request.BillSupplyRequestDTO;
import com.empresa.dto.response.BillSupplyResponseDTO;
import com.empresa.service.BillSupplyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bill-supplies")
public class BillSupplyController {

    private final BillSupplyService billSupplyService;

    public BillSupplyController(BillSupplyService billSupplyService) {
        this.billSupplyService = billSupplyService;
    }

    @PostMapping
    public ResponseEntity<BillSupplyResponseDTO> create(
            @Valid @RequestBody BillSupplyRequestDTO billSupplyDTO) {
        BillSupplyResponseDTO created = billSupplyService.create(billSupplyDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillSupplyResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(billSupplyService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BillSupplyResponseDTO>> getAll() {
        return ResponseEntity.ok(billSupplyService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        billSupplyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}