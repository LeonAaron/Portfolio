#include <stdio.h>

typedef struct {
    int account_number;
    double balance;
} Account;

void deposit(Account *acc, double amount) {
    if (amount <= 0) {
        printf("Invalid deposit amount.\n");
        return;
    }

    // Update balance
    acc->balance += amount;

    // Print deposit information
    printf("Deposited $%.2f into account #%d.\n",
           amount, acc->account_number);
}

int main() {
    Account acc1 = {1001, 150.0};
    Account acc2 = {1002, 200.0};

    // Make deposit of $50 to acc1
    deposit(&acc1, 50.0);

    // Make deposit of $100 to acc2
    deposit(&acc2, 100.0);

    // Print acc1 account information
    printf("Account #%d balance: $%.2f\n",
           acc1.account_number, acc1.balance);

    // Print acc2 account information
    printf("Account #%d balance: $%.2f\n",
           acc2.account_number, acc2.balance);

    return 0;
}
