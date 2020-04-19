package com.spdb.firebase.domain.fire;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FireBrigadeEntityId implements Serializable {
    @Column(name = "fire_id")
    private Long fireId;

    @Column(name = "brigade_id")
    private Long brigadeId;
}
