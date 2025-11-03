package lotto.view;

import java.util.List;

public class OutputView {

    public void printLottoPriceInputMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottoWinNumbersInputMessage() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public void printLottoBonusNumberInputMessage() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }

    public void printPurchaseCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public void printLottoNumbers(List<Integer> numbers) {
        System.out.println(numbers);
    }

    public void printResultHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }

    public void printRankResult(String description, int count) {
        System.out.println(description + " - " + count + "개");
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
