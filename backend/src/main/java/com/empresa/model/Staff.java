package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STAFF", schema = "GESTOCK")
public class Staff implements Serializable {

    @Id
    @Column(name = "ID_Staff", length = 5, nullable = false)
    private String idStaff;

    @Column(name = "Name1", length = 50, nullable = false)
    private String name1;

    @Column(name = "Name2", length = 50)
    private String name2;

    @Column(name = "Last_Name", length = 15, nullable = false)
    private String lastName;

    @Column(name = "Last_Name2", length = 15)
    private String lastName2;

    @Column(name = "Date_Birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateBirth;

    @Column(name = "Email", length = 50)
    private String email;

    @Column(name = "Address", length = 70)
    private String address;

    @Column(name = "Number_Phone", length = 10, nullable = false)
    private String numberPhone;

    @Column(name = "Salary", nullable = false)
    private Double salary;

    @Column(name = "Type_Staff", length = 20, nullable = false)
    private String typeStaff;

    @Column(name = "Status", length = 20)
    private String status;

    // Relación con facturas (si aplica)
    
    @OneToMany(mappedBy = "staff")
    private List<Bill> bills;
    

    public Staff() {
    }

    // Getters y Setters
    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTypeStaff() {
        return typeStaff;
    }

    public void setTypeStaff(String typeStaff) {
        this.typeStaff = typeStaff;
    }

    
    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}