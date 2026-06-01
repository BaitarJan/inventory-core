package com.inventory.validation;

import com.inventory.model.Unit;

public class QuantityValidator {

    public boolean isValidQuantity(Unit unit,
                                   double quantity) {

        if (quantity <= 0) {
            return false;
        }

        if (unit == Unit.PCS
                || unit == Unit.BOX
                || unit == Unit.PACK) {

            return quantity % 1 == 0;
        }

        return true;
    }
}