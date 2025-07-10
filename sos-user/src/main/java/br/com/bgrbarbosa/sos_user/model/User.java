package br.com.bgrbarbosa.sos_user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDate date_insert;

    private LocalDate date_update;

    @Enumerated(EnumType.STRING)
    private Role role;

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
