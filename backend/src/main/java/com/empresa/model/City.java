package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CITY", schema = "GESTOCK")
public class City implements Serializable {

    @EmbeddedId
    private CityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "ID_Department",
        referencedColumnName = "ID_Department",
        insertable = false,
        updatable = false
    )
    private Department department;

    @Column(name = "Name_City", length = 30, nullable = false)
    private String nameCity;

    @Column(name = "Zip_Code", length = 5, nullable = false)
    private String zipCode;

    // Constructors

    public City() {}

    public City(CityId id, String nameCity, String zipCode) {
        this.id = id;
        this.nameCity = nameCity;
        this.zipCode = zipCode;
    }

    // Getters and Setters

    public CityId getId() {
        return id;
    }

    public void setId(CityId id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // EmbeddedId Class

    @Embeddable
    public static class CityId implements Serializable {

        @Column(name = "ID_Department", length = 3, nullable = false)
        private String departmentId;

        @Column(name = "ID_City", length = 5, nullable = false)
        private String cityId;

        public CityId() {}

        public CityId(String departmentId, String cityId) {
            this.departmentId = departmentId;
            this.cityId = cityId;
        }

        // Getters and Setters

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        // Equals and HashCode

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CityId)) return false;
            CityId that = (CityId) o;
            return Objects.equals(departmentId, that.departmentId) &&
                   Objects.equals(cityId, that.cityId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(departmentId, cityId);
        }
    }
}