package com.comp301.a01heist;

import org.junit.jupiter.api.Test;
import static org.juint.jupiter.api.Assertions.*;

public class PracticeTests {

    @Test
    public void unitTest1() {
        // A- Arrange, A- Act, A- Assert
        assertTrue(true);
    }

    @Test
    public void testConstructor() {
        new InventoryImpl();
    }

    @Test
    public void testAddItems() {

    }

    @Test
    public void testAddMultipleItems() {

    }

    @Test
    public void testCapacity() {
        InventoryImpl i = new InventoryImpl();
        int capacity = i.getCapacity();
        assertEquals(0, capacity);
    }

    @Test
    public void testWithdrawReducesBalance() {
        BankAccount acc = new BankAccount(100);
        acc.withdraw(40);
        assertEquals(60, acc.getBalance());
    }

    @Test
    public void testDeposit(){
        BankAccount acc = new BankAccount(100);
        acc.deposit(40);
        assertEquals(140, acc.getBalance());
    }

    @BeforeEach
    public void setUp() {
        BankAccount acc = new BankAccount(100);
    }

    @AfterEach
    public void tearDown() {
        acc = null;
    }

}
