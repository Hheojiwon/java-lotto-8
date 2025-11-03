package lotto.exception;

public enum ErrorMessage {

    INVALID_PURCHASE_AMOUNT_UNDER_MINIMUM("구입 금액은 1,000원 이상이어야 합니다."),
    INVALID_PURCHASE_AMOUNT_NOT_DIVISIBLE("구입 금액은 1,000원 단위로 입력해야 합니다."),
    INVALID_PURCHASE_AMOUNT_EMPTY("구입 금액이 비어 있습니다."),
    INVALID_PURCHASE_AMOUNT_NOT_NUMBER("구입 금액은 숫자 형태로 입력해야 합니다."),

    INVALID_LOTTO_NUMBER_NOT_NUMBER("로또 번호는 숫자 형태로 입력해야 합니다."),
    INVALID_LOTTO_NUMBER_EMPTY("로또 번호가 비어 있습니다."),
    INVALID_LOTTO_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_DUPLICATE("로또 번호가 중복되고 있습니다."),
    DUPLICATE_WINNING_NUMBER("당첨 번호에 중복된 숫자가 있습니다.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
