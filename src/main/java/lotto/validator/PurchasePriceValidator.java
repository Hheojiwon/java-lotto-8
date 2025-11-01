package lotto.validator;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class PurchasePriceValidator {

    public static void validate(String purchasePriceInput) {
        if (purchasePriceInput == null || purchasePriceInput.trim().isEmpty()) {
            throw new LottoException(ErrorMessage.INVALID_PURCHASE_AMOUNT_EMPTY);
        }

        int purchasePrice;

        try {
            purchasePrice = Integer.parseInt(purchasePriceInput.trim());
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.INVALID_PURCHASE_AMOUNT_NOT_NUMBER);
        }

        if (purchasePrice < 1000) {
            throw new LottoException(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNDER_MINIMUM);
        }
        if (purchasePrice % 1000 != 0) {
            throw new LottoException(ErrorMessage.INVALID_PURCHASE_AMOUNT_NOT_DIVISIBLE);
        }
    }
}
