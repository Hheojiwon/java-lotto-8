package lotto.validator;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.List;

public class LottoNumberValidator {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public static void validate(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_EMPTY);
        }

        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT);
        }

        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new LottoException(ErrorMessage.INVALID_LOTTO_RANGE);
            }
        }
    }
}
