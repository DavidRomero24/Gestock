package com.empresa.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT", schema = "GESTOCK")
public class Department {

    @Id
    @Column(name = "ID_Department", length = 3, nullable = false)
    private String id;

    @Column(name = "Name_Department", length = 20, nullable = false)
    private String name;

    @Column(name = "Department_Code", length = 3, nullable = false)
    private String code;

    // Relación con City (Uno a Muchos)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<City> cities;

    // Constructors
    public Department() {}

    public Department(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
