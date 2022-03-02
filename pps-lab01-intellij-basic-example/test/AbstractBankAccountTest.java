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
        assertEquals(0, this.bankAccount.getBalance());
    }

    @Test
    public void testDeposit() {
        final int depositedAmount = 100;
        this.bankAccount.deposit(this.accountHolder.getId(), depositedAmount);
        assertEquals(depositedAmount - this.bankAccount.getFee(), this.bankAccount.getBalance());
    }

    @Test
    public void testWrongDeposit() {
        final int firstDepositedAmount = 100;
        final int secondDepositedAmount = 50;
        final int newAccountHolderId = 2;
        this.bankAccount.deposit(this.accountHolder.getId(), firstDepositedAmount);
        this.bankAccount.deposit(newAccountHolderId, secondDepositedAmount);
        assertEquals(firstDepositedAmount - this.bankAccount.getFee(), this.bankAccount.getBalance());
    }

    @Test
    public void testWithdraw() {
        final int depositedAmount = 100;
        final int withdrawnAmount = 70;
        this.bankAccount.deposit(this.accountHolder.getId(), depositedAmount);
        this.bankAccount.withdraw(this.accountHolder.getId(), withdrawnAmount);
        assertEquals(depositedAmount - this.bankAccount.getFee() - withdrawnAmount - this.bankAccount.getFee(), this.bankAccount.getBalance());
    }

    @Test
    public void testWrongWithdraw() {
        final int depositedAmount = 100;
        final int withdrawnAmount = 70;
        final int newAccountHolderId = 2;
        this.bankAccount.deposit(this.accountHolder.getId(), depositedAmount);
        this.bankAccount.withdraw(newAccountHolderId, withdrawnAmount);
        assertEquals(depositedAmount - this.bankAccount.getFee(), this.bankAccount.getBalance());
    }
}
