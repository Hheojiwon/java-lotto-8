package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;


public class RandomNumberGeneratorImpl {

    public final int RANDOM_NUMBER_START = 1;
    public final int RANDOM_NUMBER_END = 45;
    public final int RANDOM_NUMBER_RANGE = 6;

    List<Integer> generate(){
        return Randoms.pickUniqueNumbersInRange(RANDOM_NUMBER_START, RANDOM_NUMBER_END, RANDOM_NUMBER_RANGE);
    }
}
