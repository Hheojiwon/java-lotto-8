package lotto.domain;

import java.util.List;

public class LottoWin {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public LottoWin(Lotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateRank(Lotto lotto) {
        int matchCount = calculateMatchCount(lotto);
        boolean matchBonus = lotto.getNumbers().contains(bonusNumber);
        return Rank.findByMatchCount(matchCount, matchBonus);
    }

    private int calculateMatchCount(Lotto lotto) {
        int count = 0;
        List<Integer> winningNumberList = winningNumbers.getNumbers();
        List<Integer> lottoNumberList = lotto.getNumbers();

        for (int number : lottoNumberList) {
            count = getCount(number, winningNumberList, count);
        }
        return count;
    }

    private int getCount(int number, List<Integer> winningNumberList, int count) {
        if (winningNumberList.contains(number)) {
            count++;
        }
        return count;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.getNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
