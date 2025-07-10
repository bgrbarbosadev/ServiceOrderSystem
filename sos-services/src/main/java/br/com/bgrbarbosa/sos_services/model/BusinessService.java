package br.com.bgrbarbosa.sos_services.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_service")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String description;

    private Double vl_service;

    @JsonIgnore
    private LocalDate date_insert;

    @JsonIgnore
    private LocalDate date_update;

    @ManyToOne
    @JoinColumn(name = "category_uuid")
    @JsonIgnore
    private Category category;

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
