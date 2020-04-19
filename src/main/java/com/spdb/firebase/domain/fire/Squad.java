package com.spdb.firebase.domain.fire;

import com.spdb.firebase.domain.brigade.Brigade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Squad {
    private Brigade brigade;
    private Long squadAmount;
}
