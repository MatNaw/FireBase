package com.spdb.firebase.domain.brigade;

import com.spdb.firebase.domain.fire.FireBrigadeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brigade")
public class BrigadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "squad_max_amount", nullable = false)
    private Long squadMaxAmount;

    @OneToMany(mappedBy = "brigade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FireBrigadeEntity> fires;
}
