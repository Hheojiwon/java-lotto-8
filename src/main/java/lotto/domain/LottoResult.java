package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> resultMap;

    public LottoResult(List<Rank> ranks) {
        this.resultMap = new EnumMap<>(Rank.class);
        initializeResultMap();
        calculateResult(ranks);
    }

    private void initializeResultMap() {
        for (Rank rank : Rank.values()) {
            if (rank.isWinning()) {
                resultMap.put(rank, 0);
            }
        }
    }

    private void calculateResult(List<Rank> ranks) {
        for (Rank rank : ranks) {
            if (rank.isWinning()) {
                resultMap.compute(rank, (k, currentCount) -> currentCount + 1);
            }
        }
    }

    public int getCount(Rank rank) {
        if (resultMap.containsKey(rank)) {
            return resultMap.get(rank);
        }
        return 0;
    }

    public long getTotalPrize() {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : resultMap.entrySet()) {
            long prize = (long) entry.getKey().getPrize() * entry.getValue();
            totalPrize += prize;
        }
        return totalPrize;
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = getTotalPrize();
        double profitRate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(profitRate * 10) / 10.0;
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }
}