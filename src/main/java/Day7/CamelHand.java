package Day7;

public enum CamelHand {
    HIGH_CARD(1),
    ONE_PAIR(2),
    TWO_PAIR(3),
    THREE_OF_A_KIND(4),
    FULL_HOUSE(5),
    FOUR_OF_A_KIND(6),
    FIVE_OF_A_KIND(7);


    private final int value;

    CamelHand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
