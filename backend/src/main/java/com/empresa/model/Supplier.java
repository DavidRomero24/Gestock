package com.empresa.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "SUPPLIER", schema = "GESTOCK")
public class Supplier {

    @Id
    @Column(name = "ID_Supplier", length = 15, nullable = false)
    private String id;

    @Column(name = "Name1", length = 50, nullable = false)
    private String name1;

    @Column(name = "Name2", length = 50, nullable = true)
    private String name2;

    @Column(name = "Last_Name1", length = 15, nullable = false)
    private String lastName1;

    @Column(name = "Last_Name2", length = 15, nullable = true)
    private String lastName2;

    @Column(name = "Phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "Company", length = 20, nullable = false)
    private String company;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "ID_Department", nullable = false),
        @JoinColumn(name = "ID_City", nullable = false)
    })
    private City city;

    @OneToMany(mappedBy = "supplier")
    private List<BillSupply> billSupplies;

    // Constructores
    public Supplier() {}

    public Supplier(String id, String name1, String name2,String lastName1,String lastName2, String phone, String email, String company, City city) {
        this.id = id;
        this.name1 = name1;
        this.name2 = name2;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.city = city;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<BillSupply> getBillSupplies() {
    return billSupplies;
}

public void setBillSupplies(List<BillSupply> billSupplies) {
    this.billSupplies = billSupplies;
}

}
