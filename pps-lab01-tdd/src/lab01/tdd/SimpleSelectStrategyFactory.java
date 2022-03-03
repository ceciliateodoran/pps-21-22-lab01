package lab01.tdd;

public class SimpleSelectStrategyFactory implements SelectStrategyFactory {
    @Override
    public SelectStrategy createEvenStrategy() {
        return element -> element % 2 == 0;
    }

    @Override
    public SelectStrategy createMultipleOfStrategy(final int number) {
        return element -> element % number == 0;
    }

    @Override
    public SelectStrategy createEqualsStrategy(final int number) {
        return element -> element == number;
    }
}
