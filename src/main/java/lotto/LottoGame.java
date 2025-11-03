package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.LottoWin;
import lotto.exception.LottoException;
import lotto.util.LottoNumberParser;
import lotto.util.RandomNumberGenerator;
import lotto.validator.BonusNumberValidator;
import lotto.validator.WinningNumberValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoGame {

    private final RandomNumberGenerator generator;
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoNumberParser parser;
    private final WinningNumberValidator winningNumberValidator;
    private final BonusNumberValidator bonusNumberValidator;

    public LottoGame(RandomNumberGenerator generator, InputView inputView, OutputView outputView,
                     LottoNumberParser parser, WinningNumberValidator winningNumberValidator,
                     BonusNumberValidator bonusNumberValidator) {
        this.generator = generator;
        this.inputView = inputView;
        this.outputView = outputView;
        this.parser = parser;
        this.winningNumberValidator = winningNumberValidator;
        this.bonusNumberValidator = bonusNumberValidator;
    }

    public void run() {
        Lottos lottos = purchaseLottos();
        LottoWin winningLotto = createWinningLotto();
        LottoResult result = lottos.match(winningLotto);
        printResult(result, lottos);
        inputView.close();
    }

    private Lottos purchaseLottos() {
        outputView.printLottoPriceInputMessage();
        while (true) {
            try {
                String purchaseAmount = inputView.readInput();
                Lottos lottos = new Lottos(purchaseAmount, generator);
                printPurchasedLottos(lottos);
                return lottos;
            } catch (LottoException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printPurchasedLottos(Lottos lottos) {
        outputView.printPurchaseCount(lottos.size());
        for (Lotto lotto : lottos.getLottos()) {
            outputView.printLottoNumbers(lotto.getNumbers());
        }
    }

    private LottoWin createWinningLotto() {
        Lotto winningNumbers = readWinningNumbers();
        int bonusNumber = readBonusNumber();
        while (true) {
            try {
                return new LottoWin(winningNumbers, bonusNumber);
            } catch (LottoException e) {
                outputView.printErrorMessage(e.getMessage());
                bonusNumber = readBonusNumber();
            }
        }
    }

    private Lotto readWinningNumbers() {
        outputView.printLottoWinNumbersInputMessage();
        while (true) {
            try {
                String input = inputView.readInput();
                winningNumberValidator.validate(input);
                List<String> parsedNumbers = parser.parseInput(input);
                winningNumberValidator.validateParsedNumbers(parsedNumbers);
                return new Lotto(convertToIntegerList(parsedNumbers));
            } catch (LottoException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int readBonusNumber() {
        outputView.printLottoBonusNumberInputMessage();
        while (true) {
            try {
                String input = inputView.readInput();
                bonusNumberValidator.validate(input);
                return Integer.parseInt(input.trim());
            } catch (LottoException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> convertToIntegerList(List<String> stringList) {
        List<Integer> integerList = new ArrayList<>();
        for (String str : stringList) {
            integerList.add(Integer.parseInt(str));
        }
        return integerList;
    }

    private void printResult(LottoResult result, Lottos lottos) {
        int purchaseAmount = lottos.size() * 1000;
        double profitRate = result.calculateProfitRate(purchaseAmount);
        printWinningStatistics(result);
        outputView.printProfitRate(profitRate);
    }

    private void printWinningStatistics(LottoResult result) {
        outputView.printResultHeader();
        Map<Rank, Integer> resultMap = result.getResultMap();
        printRankResult(Rank.FIFTH, resultMap);
        printRankResult(Rank.FOURTH, resultMap);
        printRankResult(Rank.THIRD, resultMap);
        printRankResult(Rank.SECOND, resultMap);
        printRankResult(Rank.FIRST, resultMap);
    }

    private void printRankResult(Rank rank, Map<Rank, Integer> resultMap) {
        int count = resultMap.getOrDefault(rank, 0);
        outputView.printRankResult(rank.getDescription(), count);
    }
}
