import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountTest {

    private BankAccount account;
    private static int count;

    @BeforeAll
    public static void beforeClass() {
        System.out.println("This executes before any test cases. Count = " + count++);
    }

    @BeforeEach
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @Test
    void deposit() {
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
        assertEquals(1200, account.getBalance(), 0);
    }

    @Test
    public void isChecking_true() {
//        assertEquals(true, account.isChecking());
        assertTrue(account.isChecking());
    }

    @Test
    void withdraw_branch() {
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    @Test
    void withdraw_notBranch() {
        // Test qu'on a bien la bonne erreur
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    double balance = account.withdraw(600.00, false);
                }, "IllegalArgumentException was expected.");

        // Test qu'on ait bien le bon message d'erreur
        assertEquals("Error message", thrown.getMessage());
    }

    // In JUnit 4:
//    @Text(expected = IllegalArgumentException.class)
//    void withdraw_notBranch() {
//      double balance = account.withdraw(600.00, false);
//      assertEquals(400.00, balance, 0);
//  }
    @Test
    void getBalance_deposit() {
        account.deposit(200.00, true);
        assertEquals(1200, account.getBalance(), 0);
    }

    @Test
    void getBalance_withdraw() {
        account.withdraw(200.00, true);
        assertEquals(800, account.getBalance(), 0);
    }

    @AfterAll
    public static void afterClass() {
        System.out.println("This executes after any test cases. Count = " +  count++);
    }

    @AfterEach
    public void teardown() {
        System.out.println("Count = " + count++);
    }
}