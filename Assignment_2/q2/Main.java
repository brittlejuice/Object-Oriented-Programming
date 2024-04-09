import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class Main {
    public static void main(String[] args) {
    BankAccount b = new BankAccount();
    //File opening
    int i;
    FileInputStream fin;
    int l = 0;
    try{
        fin = new FileInputStream(args[0]);
    }
    catch(FileNotFoundException fnf)
    {
        System.out.println(fnf + ": Error opening the specified file");
        return;
    }
    try{
        do{
            i = fin.read();
            if(i != -1 && i == 'd' || i == 'w') l++;
        }while(i!=-1);
    }
    catch(IOException io)
    {
        System.out.println(io+": Error in IO");
    }
    //////// closing file /////////////
    try
    {
        fin.close();
    }
    catch(IOException ie)
    {
        System.out.println(ie+ ": Error in IO");
    }
    //////// reopening file /////////////

    ThreadBank threads[] = new ThreadBank[l];
    BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(args[0]));
			String line;
            for(int j = 0;j<l;j++)
            {
                line = reader.readLine();
                if(line.charAt(0) == 'd')
                {
                    String s = "";
                    for(int f = 1;f<line.length();f++)
                    {
                        s+=line.charAt(f);
                    }
                    int value = Integer.parseInt(s);
                    threads[j] = new ThreadBank(b,value,0,j);
                }
                else if(line.charAt(0) == 'w')
                {
                    String s = "";
                    for(int f = 1;f<line.length();f++)
                    {
                        s+=line.charAt(f);
                    }
                    int value = Integer.parseInt(s);
                    threads[j] = new ThreadBank(b,value,1,j);
                }
            }
			reader.close();
		} catch (IOException e) {
			System.out.println(e+": Error in IO");;
		}
        for (int j = 0;j < l; j++) {
            threads[j].start();
          }
       
          for (Thread thread: threads) {
            try {
              thread.join();
            } catch (InterruptedException e) {
                System.out.println(e + ": Thread interrupted");
            }
          }
       }
       // main() ends here
       

}



class BankAccount{
    int balance = 0;
    int cur = 0;
    void deposit(int value){
        balance+=value;
        System.out.println(balance);
    }
    void withdraw(int value) throws ArithmeticException{
        if(balance-value<0){throw new ArithmeticException("Illegal mathematical operation");}
        else {balance-=value;
        System.out.println(balance);}
    }
}

class ThreadBank extends Thread{
    BankAccount b;
    int x,value,j;
    ThreadBank(BankAccount b,int value,int x,int j)
    {
        this.b = b;
        this.x = x;
        this.value = value;
        this.j = j;
    }
    
    public void run(){
        synchronized(b)
        {
             try{while(j!=b.cur) b.wait();
            }
             catch(InterruptedException e){System.out.println(e+": Thread interrupted");}
            if(x == 0)
            {
                b.deposit(value);
                b.cur++;
            }
            else
            {
                try{
                    b.withdraw(value);
                    b.cur++;
                }
                catch(ArithmeticException ae)
                {
                    System.out.println(ae);
                    b.cur++;
                }
            }
            b.notifyAll();
        }
    }
}