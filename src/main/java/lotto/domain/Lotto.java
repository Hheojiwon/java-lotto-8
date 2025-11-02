package lotto.domain;

import lotto.exception.LottoException;
import lotto.validator.LottoNumberValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoNumberValidator.validate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
