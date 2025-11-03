package lotto.validator;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.List;

public class WinningNumberValidator {

    private static final int WINNING_NUMBER_SIZE = 6;

    public void validate(String winningNumberInput) {
        if (winningNumberInput == null || winningNumberInput.trim().isEmpty()) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_EMPTY);
        }
    }

    public void validateParsedNumbers(List<String> parsedNumbers) {
        if (parsedNumbers.size() != WINNING_NUMBER_SIZE) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT);
        }

        for (String number : parsedNumbers) {
            validateNumber(number);
        }
    }

    private void validateNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_NOT_NUMBER);
        }
    }
}
