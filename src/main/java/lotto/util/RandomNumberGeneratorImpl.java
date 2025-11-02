package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomNumberGeneratorImpl implements RandomNumberGenerator {

    private static final int RANDOM_NUMBER_START = 1;
    private static final int RANDOM_NUMBER_END = 45;
    private static final int RANDOM_NUMBER_RANGE = 6;

    @Override
    public List<Integer> generate(){
        return Randoms.pickUniqueNumbersInRange(RANDOM_NUMBER_START, RANDOM_NUMBER_END, RANDOM_NUMBER_RANGE);
    }
}
