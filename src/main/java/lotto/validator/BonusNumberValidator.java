package lotto.validator;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class BonusNumberValidator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public void validate(String bonusNumberInput) {
        if (bonusNumberInput == null || bonusNumberInput.trim().isEmpty()) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_EMPTY);
        }

        int bonusNumber = parseNumber(bonusNumberInput);
        validateRange(bonusNumber);
    }

    private int parseNumber(String bonusNumberInput) {
        try {
            return Integer.parseInt(bonusNumberInput.trim());
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_NOT_NUMBER);
        }
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_RANGE);
        }
    }
}
