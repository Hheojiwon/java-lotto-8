package lotto.domain;

import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("Lotto 객체 생성 시 번호가 오름차순으로 정렬된다.")
    void lottoNumbersShouldBeSorted() {
        // given
        List<Integer> unsortedNumbers = List.of(6, 5, 4, 3, 2, 1);

        // when
        Lotto lotto = new Lotto(unsortedNumbers);

        // then
        List<Integer> sortedNumbers = lotto.getNumbers();
        assertThat(sortedNumbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
    void lottoShouldThrowExceptionForInvalidSize() {
        // given
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5); // 5개

        // when & then
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(LottoException.class);
    }

    @Test
    @DisplayName("로또 번호에 범위를 벗어난 숫자가 있으면 예외가 발생한다.")
    void lottoShouldThrowExceptionForInvalidRange() {
        // given
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5, 46); // 46은 범위 밖

        // when & then
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(LottoException.class);
    }
}