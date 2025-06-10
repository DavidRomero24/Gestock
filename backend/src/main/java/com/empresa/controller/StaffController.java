package com.empresa.controller;

import com.empresa.dto.request.StaffRequestDTO;
import com.empresa.dto.response.PaginationResponseDTO;
import com.empresa.dto.response.StaffResponseDTO;
import com.empresa.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@Tag(name = "Gestión de Empleados", description = "API para la gestión del personal de GESTOCK")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    @Operation(summary = "Crear nuevo empleado", description = "Registra un nuevo miembro del personal")
    @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente")
    public ResponseEntity<StaffResponseDTO> createStaff(@Valid @RequestBody StaffRequestDTO staffDTO) {
        StaffResponseDTO response = staffService.createStaff(staffDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID", description = "Recupera los detalles de un empleado específico")
    public ResponseEntity<StaffResponseDTO> getStaffById(
            @Parameter(description = "ID del empleado", example = "EMP001") 
            @PathVariable String id) {
        StaffResponseDTO response = staffService.getStaffById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar empleados", description = "Recupera lista paginada de empleados")
    public ResponseEntity<PaginationResponseDTO<StaffResponseDTO>> getAllStaff(
            @Parameter(description = "Configuración de paginación y ordenamiento")
            @PageableDefault(size = 10, sort = "lastName") Pageable pageable) {
        PaginationResponseDTO<StaffResponseDTO> response = staffService.getAllStaff(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar empleados", description = "Busca empleados por nombre, apellido o tipo")
    public ResponseEntity<List<StaffResponseDTO>> searchStaff(
            @Parameter(description = "Término de búsqueda", example = "Pérez") 
            @RequestParam String searchTerm,
            @Parameter(description = "Tipo de empleado", example = "VENDEDOR") 
            @RequestParam(required = false) String type) {
        List<StaffResponseDTO> response = staffService.searchStaff(searchTerm, type);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/type/{typeStaff}")
    @Operation(summary = "Filtrar por tipo", description = "Recupera empleados por tipo (VENDEDOR, ADMINISTRATIVO, etc.)")
    public ResponseEntity<PaginationResponseDTO<StaffResponseDTO>> getStaffByType(
            @Parameter(description = "Tipo de empleado", example = "VENDEDOR") 
            @PathVariable String typeStaff,
            @PageableDefault(size = 10) Pageable pageable) {
        PaginationResponseDTO<StaffResponseDTO> response = staffService.getStaffByType(typeStaff, pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado", description = "Actualiza la información de un empleado existente")
    public ResponseEntity<StaffResponseDTO> updateStaff(
            @Parameter(description = "ID del empleado", example = "EMP001") 
            @PathVariable String id,
            @Valid @RequestBody StaffRequestDTO staffDTO) {
        StaffResponseDTO response = staffService.updateStaff(id, staffDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Cambiar estado", description = "Actualiza el estado del empleado (ACTIVO, INACTIVO, etc.)")
    public ResponseEntity<Void> changeStaffStatus(
            @Parameter(description = "ID del empleado", example = "EMP001") 
            @PathVariable String id,
            @Parameter(description = "Nuevo estado", example = "ACTIVO") 
            @RequestParam String status) {
        staffService.changeStaffStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado", description = "Elimina un empleado del sistema")
    @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente")
    public ResponseEntity<Void> deleteStaff(
            @Parameter(description = "ID del empleado", example = "EMP001") 
            @PathVariable String id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}