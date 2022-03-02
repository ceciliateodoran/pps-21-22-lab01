package lab01.example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account With Atm allows always the deposit using the ATM
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 * and each transaction done with the ATM implies paying a 1$ fee
 */
public class SimpleBankAccountWithAtm extends AbstractBankAccount {
    private static final int FEE = 1;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    public final int getFee() {
        return this.FEE;
    }

}
