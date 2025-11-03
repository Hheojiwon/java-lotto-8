package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinTest {

    private LottoWin lottoWin;

    @BeforeEach
    void setUp() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        lottoWin = new LottoWin(winningNumbers, bonusNumber);
    }

    @Test
    @DisplayName("1등: 6개 번호 일치")
    void calculateRankFirst() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        Rank rank = lottoWin.calculateRank(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("2등: 5개 번호 + 보너스 번호 일치")
    void calculateRankSecond() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // when
        Rank rank = lottoWin.calculateRank(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("3등: 5개 번호 일치 (보너스 불일치)")
    void calculateRankThird() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        // when
        Rank rank = lottoWin.calculateRank(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4등: 4개 번호 일치")
    void calculateRankFourth() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));

        // when
        Rank rank = lottoWin.calculateRank(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("5등: 3개 번호 일치")
    void calculateRankFifth() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));

        // when
        Rank rank = lottoWin.calculateRank(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("꽝: 2개 이하 번호 일치")
    void calculateRankNone() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 8, 9, 10, 11)); 

        // when
        Rank rank = lottoWin.calculateRank(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.NONE);
    }
}