package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberParser {

    private static final String DELIMITER = ",";

    public static List<String> parseInput(String input) {
        String[] parseInput = input.split(DELIMITER);
        return Arrays.stream(parseInput)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
