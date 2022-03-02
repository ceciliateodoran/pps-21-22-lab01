import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractBankAccountTest {
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    public abstract void beforeEach();

    @Test
    public void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    public void testDeposit() {
        final int depositedAmount = 100;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        assertEquals(depositedAmount - bankAccount.getFee(), bankAccount.getBalance());
    }

    @Test
    public void testWrongDeposit() {
        final int firstDepositedAmount = 100;
        final int secondDepositedAmount = 50;
        final int newAccountHolderId = 2;
        bankAccount.deposit(accountHolder.getId(), firstDepositedAmount);
        bankAccount.deposit(newAccountHolderId, secondDepositedAmount);
        assertEquals(firstDepositedAmount - bankAccount.getFee(), bankAccount.getBalance());
    }

    @Test
    public void testWithdraw() {
        final int depositedAmount = 100;
        final int withdrawnAmount = 70;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawnAmount);
        assertEquals(depositedAmount - bankAccount.getFee() - withdrawnAmount - bankAccount.getFee(), bankAccount.getBalance());
    }

    @Test
    public void testWrongWithdraw() {
        final int depositedAmount = 100;
        final int withdrawnAmount = 70;
        final int newAccountHolderId = 2;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(newAccountHolderId, withdrawnAmount);
        assertEquals(depositedAmount - bankAccount.getFee(), bankAccount.getBalance());
    }
}
