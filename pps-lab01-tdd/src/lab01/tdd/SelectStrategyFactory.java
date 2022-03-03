package lab01.tdd;

public interface SelectStrategyFactory {

    /**
     * Determines if the element is even
     * @return true if the element is even
     */
    SelectStrategy createEvenStrategy();

    /**
     * Determines if an element is a multiple of the given number
     * @param number the number given to check multiplicity
     * @return true if the element is a multiple of the given number
     */
    SelectStrategy createMultipleOfStrategy(final int number);

    /**
     * Determines if an element is equal to the given number
     * @param number the number that has to be checked if it is equal to the element
     * @return true if the element is equal to the given number
     */
    SelectStrategy createEqualsStrategy(final int number);
}
