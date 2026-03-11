
public class Exercise5 {
	
	class GreeterTask implements Runnable {

	    @Override
	    public void run() {
	        for (int i = 1; i <= 5; i++) {
	            System.out.println("Hello from " + Thread.currentThread().getName());
	            try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	public class GreetingThreads {
	    public static void main(String[] args) {

	        Thread t1 = new Thread(new GreeterTask(), "Lehman-Thread-1");
	        Thread t2 = new Thread(new GreeterTask(), "Lehman-Thread-2");

	        t1.start();
	        t2.start();
	    }
	}

	
	public class ThreadStatesDemo {

	    public static void main(String[] args) throws InterruptedException {

	        Thread sleeper = new Thread(() -> {
	            try {
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        });

	        System.out.println("After creation: " + sleeper.getState());

	        sleeper.start();

	        System.out.println("After start: " + sleeper.getState());

	        Thread.sleep(500); 

	        System.out.println("While sleeping: " + sleeper.getState());

	        sleeper.join(); 

	        System.out.println("After completion: " + sleeper.getState());
	    }
	}
	
	
	class BankAccount {

	    private int balance = 1000;

	    public synchronized void withdraw(int amount) {
	        if (balance >= amount) {
	            System.out.println(Thread.currentThread().getName() + " is withdrawing...");
	            balance -= amount;
	            System.out.println("Remaining balance: " + balance);
	        } else {
	            System.out.println(Thread.currentThread().getName() + " - Not enough funds!");
	        }
	    }
	}

	public class RaceConditionDemo {

	    public static void main(String[] args) {

	        BankAccount account = new BankAccount();

	        Runnable task = () -> account.withdraw(700);

	        Thread husband = new Thread(task, "Husband");
	        Thread wife = new Thread(task, "Wife");

	        husband.start();
	        wife.start();
	    }
	}
	
	
	public class JoinDemo {

	    static long result = 0;

	    public static void main(String[] args) throws InterruptedException {

	        Thread worker = new Thread(() -> {
	            for (long i = 0; i <= 1_000_000_000L; i++) {
	                result += i;
	            }
	        });

	        worker.start();

	        worker.join();

	        System.out.println("Calculation Finished: " + result);
	    }
	}
	
}