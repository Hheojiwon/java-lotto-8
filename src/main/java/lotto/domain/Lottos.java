package lotto.domain;

import lotto.util.RandomNumberGenerator;
import lotto.validator.PurchasePriceValidator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(String purchaseAmount, RandomNumberGenerator generator) {
        PurchasePriceValidator.validate(purchaseAmount);
        this.lottos = generateLottos(purchaseAmount, generator);
    }

    private List<Lotto> generateLottos(String purchaseAmount, RandomNumberGenerator generator) {
        int count = calculateLottoCount(purchaseAmount);
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = generator.generate();
            lottoList.add(new Lotto(numbers));
        }
        return lottoList;
    }

    private int calculateLottoCount(String purchaseAmount) {
        int amount = Integer.parseInt(purchaseAmount.trim());
        return amount / 1000;
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
