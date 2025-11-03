package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberParserTest {

    @Test
    @DisplayName("쉼표(,)를 기준으로 문자열을 파싱한다.")
    void parseInputWithDelimiter() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<String> result = LottoNumberParser.parseInput(input);

        // then
        assertThat(result).containsExactly("1", "2", "3", "4", "5", "6");
    }

    @Test
    @DisplayName("파싱 시 각 숫자의 앞뒤 공백을 제거한다.")
    void parseInputWithWhitespace() {
        // given
        String input = " 1 , 2, 3 ,4,5, 6 ";

        // when
        List<String> result = LottoNumberParser.parseInput(input);

        // then
        // " 1 " -> "1", " 2" -> "2", " 3 " -> "3"
        assertThat(result).containsExactly("1", "2", "3", "4", "5", "6");
    }
}