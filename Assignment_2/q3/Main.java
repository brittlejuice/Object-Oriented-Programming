import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
    Sum357 s357 = new Sum357();
    Prime p = new Prime();
    long a[] = new long[2];
    Thread  t1 = new Thread(() -> p.sieve(n,a)); 
    Thread t2 = new Thread(() -> s357.sumThread(n,a)); 
    t1.start();
    t2.start();
    try {
        t1.join();
        t2.join();
      } catch (InterruptedException e) {
        System.out.println(e + ": Thread interrupted");
      }
      System.out.println(a[0]+" "+a[1]);
    }
}


class Sum357{
    long s(long n,long j)
    {
        long n1 = n/j;
        return n1*(2*j+(n1-1)*j)/2;
    }
    void sumThread(long n,long a[])
    {
        long ans = s(n,3) +s(n,5) + s(n,7) -s(n,15)-s(n,21)-s(n,35)+s(n,105);
        a[1] = ans;
    }
}

class Prime{
    void sieve(int n,long a[])
    {
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;
 
        for (int p = 2; p * p <= n; p++) {
            if (prime[p] == true) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
        long sm = 0;
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                sm+=i;
        }
        a[0] = sm;
    }
}