package lotto;

import lotto.util.LottoNumberParser;
import lotto.util.RandomNumberGenerator;
import lotto.util.RandomNumberGeneratorImpl;
import lotto.validator.BonusNumberValidator;
import lotto.validator.WinningNumberValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        RandomNumberGenerator generator = new RandomNumberGeneratorImpl();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoNumberParser parser = new LottoNumberParser();
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        BonusNumberValidator bonusNumberValidator = new BonusNumberValidator();

        LottoGame game = new LottoGame(
                generator,
                inputView,
                outputView,
                parser,
                winningNumberValidator,
                bonusNumberValidator
        );

        game.run();
    }
}