package lotto.validator;

import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (hasDuplicates(numbers)) {
            throw new LottoException(ErrorMessage.INVALID_LOTTO_NUMBER_DUPLICATE);
        }


        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new LottoException(ErrorMessage.INVALID_LOTTO_RANGE);
            }
        }
    }

    private static boolean hasDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        return uniqueNumbers.size() != numbers.size();
    }
}
