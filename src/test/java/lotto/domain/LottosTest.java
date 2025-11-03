package lotto.domain;

import lotto.util.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestNumberGenerator implements RandomNumberGenerator {
    private final List<Integer> numbers;

    public TestNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generate() {
        return numbers;
    }
}

class LottosTest {

    @Test
    @DisplayName("구입 금액만큼 로또를 생성한다.")
    void generateLottosByPurchaseAmount() {
        // given
        String purchaseAmount = "3000";
        RandomNumberGenerator generator = new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 6));

        // when
        Lottos lottos = new Lottos(purchaseAmount, generator);

        // then
        assertThat(lottos.size()).isEqualTo(3);
        assertThat(lottos.getLottos()).hasSize(3);
        assertThat(lottos.getLottos().get(0).getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("모든 로또와 당첨 번호를 비교하여 LottoResult를 생성한다.")
    void matchLottosWithWinningNumbers() {
        // given
        RandomNumberGenerator generator = new RandomNumberGenerator() {
            private int count = 0;
            private final List<List<Integer>> numbersList = List.of(
                    List.of(1, 2, 3, 4, 5, 6), // 1등
                    List.of(1, 2, 3, 10, 11, 12), // 5등
                    List.of(10, 11, 12, 13, 14, 15) // 꽝
            );

            @Override
            public List<Integer> generate() {
                return numbersList.get(count++);
            }
        };

        Lottos lottos = new Lottos("3000", generator);
        LottoWin winningLotto = new LottoWin(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                7
        );

        // when
        LottoResult result = lottos.match(winningLotto);

        // then
        assertThat(result.getCount(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getCount(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.getCount(Rank.NONE)).isEqualTo(0); // NONE은 0개여야 함
        assertThat(result.getCount(Rank.SECOND)).isEqualTo(0);
    }
}