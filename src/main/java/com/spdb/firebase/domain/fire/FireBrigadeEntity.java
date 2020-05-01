package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.BrigadeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fire_brigade")
public class FireBrigadeEntity {

    @EmbeddedId
    private FireBrigadeEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("fireId")
    private FireEntity fire;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("brigadeId")
    private BrigadeEntity brigade;

    @Column(name = "squad_amount")
    private Long squadAmount;
}
