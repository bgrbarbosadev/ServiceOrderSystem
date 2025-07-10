package br.com.bgrbarbosa.sos_service_order.model;

import br.com.bgrbarbosa.sos_service_order.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_order_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    @Column
    private LocalDate openingDate;

    @Column
    private LocalDate closingDate;

    @Column
    private EnumStatus status;

    @Column
    private String description;

    @Column
    private Double vl_order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_has_services",
            joinColumns = @JoinColumn(name = "order_uuid"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<BusinessService> businessService;

    @PrePersist
    protected void onCreate() {
        openingDate = LocalDate.now();
        status = EnumStatus.OPEN;
        calculatedVlOder();
    }

    @PreUpdate
    protected void onUpdate(){
        calculatedVlOder();
    }

    private void calculatedVlOder() {
        vl_order = 0.00;
        businessService.forEach( x -> {
            vl_order = vl_order +  x.getVl_service();
        });
        this.setVl_order(vl_order);
    }

}
