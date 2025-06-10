// package com.empresa.dto.response;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonInclude;
// import io.swagger.v3.oas.annotations.media.Schema;
// import lombok.Builder;
// import lombok.Data;

// import java.util.Date;

// @Data
// @Builder
// @JsonInclude(JsonInclude.Include.NON_NULL)
// @Schema(description = "DTO para respuesta de empleados")
// public class StaffResponseDTO {

//     @Schema(description = "ID único del empleado", example = "EMP1")
//     private String idStaff;

//     @Schema(description = "Primer nombre del empleado", example = "Juan")
//     private String name1;

//     @Schema(description = "Segundo nombre del empleado", example = "Carlos")
//     private String name2;

//     @Schema(description = "Primer apellido del empleado", example = "Pérez")
//     private String lastName;

//     @Schema(description = "Segundo apellido del empleado", example = "Gómez")
//     private String lastName2;

//     @Schema(description = "Fecha de nacimiento del empleado", example = "1985-05-15")
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//     private Date dateBirth;

//     @Schema(description = "Email del empleado", example = "juan.perez@empresa.com")
//     private String email;

//     @Schema(description = "Número de teléfono del empleado", example = "3101234567")
//     private String numberPhone;

//     @Schema(description = "Salario del empleado", example = "2500000.00")
//     private Double salary;

//     @Schema(description = "Tipo de empleado", example = "VENDEDOR")
//     private String typeStaff;

//     @Schema(description = "Estado actual del empleado", example = "ACTIVO")
//     private String status;

//     @Schema(description = "Fecha de contratación", example = "2020-01-15")
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//     private Date hireDate;
// }

package com.empresa.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO de respuesta para empleados")
public class StaffResponseDTO {

    @Schema(description = "ID único del empleado", example = "EMP001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String idStaff;

    @Schema(description = "Nombre completo del empleado", example = "Juan Carlos Pérez Gómez")
    @JsonProperty("fullName")
    private String fullName;

    @Schema(description = "Primer nombre", example = "Juan")
    private String name1;

    @Schema(description = "Segundo nombre", example = "Carlos")
    private String name2;

    @Schema(description = "Primer apellido", example = "Pérez")
    private String lastName;

    @Schema(description = "Segundo apellido", example = "Gómez")
    private String lastName2;

    @Schema(description = "Fecha de nacimiento", example = "1990-05-15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateBirth;

    @Schema(description = "Edad calculada", example = "33")
    private Integer age;

    @Schema(description = "Correo electrónico", example = "juan.perez@empresa.com")
    private String email;

    @Schema(description = "Número de teléfono", example = "3101234567")
    private String numberPhone;

    @Schema(description = "Salario base", example = "2500000.50")
    private Double salary;

    @Schema(description = "Tipo de empleado", example = "VENDEDOR", allowableValues = {"VENDEDOR", "ADMINISTRATIVO", "GERENTE", "BODEGUERO"})
    private String typeStaff;

    @Schema(description = "Estado del empleado", example = "ACTIVO", allowableValues = {"ACTIVO", "INACTIVO", "VACACIONES", "LICENCIA"})
    private String status;

    @Schema(description = "Fecha de contratación", example = "2020-01-15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date hireDate;

    @Schema(description = "Dirección de residencia", example = "Calle 123 #45-67")
    private String address;

    @Schema(description = "Indica si tiene acceso al sistema", example = "true")
    private Boolean hasSystemAccess;

    @Schema(description = "Fecha de última actualización", example = "2023-05-20T10:30:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date lastUpdate;
}