package br.com.bgrbarbosa.sos_customers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String name;

    private String cpf;

    private String email;

    private String tel;

    private String cel;

    private String address;

    private String neighborhood;

    private String city;

    private String state;

    private String cep;

    @JsonIgnore
    private LocalDate date_insert;

    @JsonIgnore
    private LocalDate date_update;

    @PrePersist
    protected void onCreate() {
        date_insert = LocalDate.now();
        date_update = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        date_update = LocalDate.now();
    }
}
