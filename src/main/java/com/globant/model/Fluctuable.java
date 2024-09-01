package com.globant.model;

import java.math.BigDecimal;

public interface Fluctuable {
     default BigDecimal generateValue(){
        return BigDecimal.ZERO;
    }
}
