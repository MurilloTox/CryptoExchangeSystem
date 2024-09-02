package com.globant.model.Interfaces;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public interface Fluctuable {
     default BigDecimal generateNewValue(BigDecimal currentValue) {
         Random rd = new Random();
         double minVariation = -1000.0;
         double maxVariation = 1000.0;
         double randomNumber = minVariation +
                 (maxVariation - minVariation) * rd.nextDouble();
         String variation = String.valueOf(randomNumber);
         BigDecimal variationNumber = new BigDecimal(variation).setScale(2, RoundingMode.HALF_UP);
         BigDecimal possibleValue = currentValue.add(variationNumber);
         if (possibleValue.compareTo(BigDecimal.ZERO) > 0) {
             return currentValue.add(variationNumber);
         } else {
             return generateNewValue(currentValue);
         }
    }
}
