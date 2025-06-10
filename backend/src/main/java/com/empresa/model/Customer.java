package com.empresa.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER", schema = "GESTOCK")
public class Customer implements Serializable {

    @Id
    @Column(name = "ID_Customer", length = 15)
    private String id;

    @Column(name = "Name1", length = 15, nullable = false)
    private String name1;

    @Column(name = "Name2", length = 15)
    private String name2;

    @Column(name = "Last_Name1", length = 15, nullable = false)
    private String lastName1;

    @Column(name = "Last_Name2", length = 15)
    private String lastName2;

    @Column(name = "Phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "E_mail", length = 50, nullable = false)
    private String email;

    @Column(name = "Address", length = 70)
    private String address;

    // Constructor vacío obligatorio para JPA
    public Customer() {}

    // Getters y Setters
    // Puedes usar Lombok si prefieres no escribir estos métodos manualmente
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName1() { return name1; }
    public void setName1(String name1) { this.name1 = name1; }

    public String getName2() { return name2; }
    public void setName2(String name2) { this.name2 = name2; }

    public String getLastName1() { return lastName1; }
    public void setLastName1(String lastName1) { this.lastName1 = lastName1; }

    public String getLastName2() { return lastName2; }
    public void setLastName2(String lastName2) { this.lastName2 = lastName2; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
