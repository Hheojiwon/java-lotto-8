package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("당첨 내역을 바탕으로 결과 맵을 올바르게 생성한다.")
    void calculateResultMapCorrectly() {
        // given
        List<Rank> ranks = List.of(Rank.FIRST, Rank.THIRD, Rank.FIFTH, Rank.FIFTH, Rank.NONE);

        // when
        LottoResult lottoResult = new LottoResult(ranks);
        Map<Rank, Integer> resultMap = lottoResult.getResultMap();

        // then
        assertThat(resultMap.get(Rank.FIRST)).isEqualTo(1);
        assertThat(resultMap.get(Rank.SECOND)).isEqualTo(0);
        assertThat(resultMap.get(Rank.THIRD)).isEqualTo(1);
        assertThat(resultMap.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(resultMap.get(Rank.FIFTH)).isEqualTo(2);
        assertThat(resultMap.containsKey(Rank.NONE)).isFalse();
    }

    @Test
    @DisplayName("총 상금을 올바르게 계산한다.")
    void calculateTotalPrize() {
        // given
        List<Rank> ranks = List.of(Rank.FIRST, Rank.FIFTH);
        LottoResult lottoResult = new LottoResult(ranks);

        // when
        long totalPrize = lottoResult.getTotalPrize();

        // then
        long expectedPrize = Rank.FIRST.getPrize() + Rank.FIFTH.getPrize();
        assertThat(totalPrize).isEqualTo(expectedPrize); // 2000005000L
    }

    @Test
    @DisplayName("총 수익률을 올바르게 계산한다 (소수점 둘째 자리에서 반올림).")
    void calculateProfitRate() {
        // given
        List<Rank> ranks = List.of(Rank.FIFTH);
        LottoResult lottoResult = new LottoResult(ranks);
        int purchaseAmount = 8000;

        // when
        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        // then
        assertThat(profitRate).isEqualTo(62.5);
    }
}