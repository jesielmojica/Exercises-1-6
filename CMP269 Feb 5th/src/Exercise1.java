
import java.util.ArrayList;

public class Exercise1 {

    interface Payable {
        void processPayment(double amount);
        String getPaymentStatus();
    }

    static abstract class PaymentMethod implements Payable {

        protected String accountHolder;
        protected double balance;
        protected static int totalTransactions = 0;

        public PaymentMethod(String accountHolder, double balance) {
            this.accountHolder = accountHolder;
            this.balance = balance;
        }

        public abstract void validateAccount();

        @Override
        public String getPaymentStatus() {
            return "Balance: $" + balance;
        }
    }

    static class CreditCard extends PaymentMethod {

        private double creditLimit;

        public CreditCard(String accountHolder, double balance, double creditLimit) {
            super(accountHolder, balance);
            this.creditLimit = creditLimit;
        }

        @Override
        public void validateAccount() {
        }

        @Override
        public void processPayment(double amount) {
            if (amount > balance + creditLimit) {
                System.out.println("Transaction Declined.");
            } else {
                balance -= amount;
                totalTransactions++;
                System.out.println("Credit Card payment approved for $" + amount);
            }
        }
    }

    static class MealPlan extends PaymentMethod {

        public MealPlan(String accountHolder, double balance) {
            super(accountHolder, balance);
        }

        @Override
        public void validateAccount() {
            if (balance < 0) {
                throw new IllegalStateException("Meal plan balance cannot be negative.");
            }
        }

        @Override
        public void processPayment(double amount) {
            if (amount > balance) {
                System.out.println("Meal Plan payment declined.");
            } else {
                balance -= amount;
                totalTransactions++;
                System.out.println("Meal Plan payment approved for $" + amount);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Payable> paymentQueue = new ArrayList<>();

        paymentQueue.add(new CreditCard("Alex Carter", 100.0, 200.0));
        paymentQueue.add(new MealPlan("Jordan Lee", 75.0));

        for (Payable payment : paymentQueue) {
            payment.processPayment(50.0);
        }

        System.out.println("Total Transactions: " + PaymentMethod.totalTransactions);
    }
}
