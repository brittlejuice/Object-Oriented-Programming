import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		BufferedReader reader;
        int N;
		try {
			reader = new BufferedReader(new FileReader(args[0]));
			String line = reader.readLine();
            int n = Integer.parseInt(line);
            long[][]  a = new long[n][n];
            long[][]  A = new long[n][n];
            line = reader.readLine();
            int temp[] = new int[n*n];
////////////
            String[] tokens = line.split("\\s+");
            int k = 0;
            for(int z = 0;z<tokens.length;z++)
                {
                    int number = Integer.parseInt(tokens[z]);
                    temp[z] = number;
                }
            for(int i = 0;i<n;i++)
            {
                for(int j = 0;j<n;j++)
                {
                    A[i][j] = temp[k];
                    k++;
                }
            }
///////////
long[][]  B = new long[n][n];
line = reader.readLine();
tokens = line.split("\\s+");
k = 0;
for(int z = 0;z<tokens.length;z++)
    {
        int number = Integer.parseInt(tokens[z]);
        temp[z] = number;
    }
for(int i = 0;i<n;i++)
{
    for(int j = 0;j<n;j++)
    {
        B[i][j] = temp[k];
        k++;
    }
}


long[][]  C = new long[n][n];
line = reader.readLine();
tokens = line.split("\\s+");
k = 0;
for(int z = 0;z<tokens.length;z++)
    {
        int number = Integer.parseInt(tokens[z]);
        temp[z] = number;
    }
for(int i = 0;i<n;i++)
{
    for(int j = 0;j<n;j++)
    {
        C[i][j] = temp[k];
        k++;
    }
}
            long b[][] = new long[n][n];
            //System.out.println(line);
			reader.close();
            RowThread t[] = new RowThread[n];
            int j = 0;
            while(j<n)
            {
                t[j] = new RowThread(a,b,A,B,C,n,j);
                t[j].start();
                j++;
            }
            try{
            for(int i = 0;i<n;i++)
            {
                t[i].join();
            }
            for(int i = 0;i<n;i++)
            {
                for(int g = 0;g<n;g++)
                {
                    if(i!= n-1 || g != n-1)
                    System.out.print(b[i][g]+" ");
                    else
                    System.out.print(b[i][g]);
                }
            }
            }
            catch (InterruptedException e) {
                System.out.println(e + ": Thread interrupted");
            }
		} catch (IOException e) {
			System.out.println(e + ": Error in IO");;
		}
	}
}

class RowMultiplier{
    void rM(long a[][],long A[][],long B[][], int n, int j)
    {
        for(int i = 0;i<n;i++)
        {
            for(int r = 0;r<n;r++)
            {
                a[j][i] += A[j][r]*B[r][i];
            }
        }
    }
    void fullSend(long a[][],long b[][],long A[][],long B[][],long C[][], int n, int j)
    {
        rM(a,A,B,n,j);
        rM(b,a,C,n,j);
    }
}

class RowThread extends Thread{
    RowMultiplier rm = new RowMultiplier();
    long a[][];
    long b[][];
    long A[][];
    long B[][];
    long C[][];
    int n;
    int j;
    RowThread(long a[][],long b[][],long A[][],long B[][],long C[][], int n, int j)
    {
        this.a = a;
        this.b = b;
        this.A = A;
        this.B = B;
        this.C = C;
        this.n = n;
        this.j = j;
    }
    public void run()
    {
        rm.fullSend(a,b,A,B,C,n,j);
    }
}

