import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;








public class Main { 
    public static void main(String[] args) {
        int i;
        FileInputStream fin;
        int n = 0;
        try {
            fin = new FileInputStream(args[0]);
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf + ": Error opening the specified file");
            return;
        }
        try {
            do {
                i = fin.read();
                if (i != -1 && i == ' ')
                    n++;
            } while (i != -1);
        } catch (IOException io) {
            System.out.println(io + ": Error in IO");
        }
        n++;
        //////// closing file /////////////
        try {
            fin.close();
        } catch (IOException ie) {
            System.out.println(ie + ": Error in IO");
        }
        //System.out.println("n "+n);
        int a[] = new int[n];
        //////// reopening file /////////////
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                int k = 0;
                for (String token : tokens) {
                    int number = Integer.parseInt(token);
                    a[k] = number;
                    k++;
                    //System.out.println("Read number: " + number);
                }
            }
        } catch (IOException e) {
            // Handle IO exception
            System.out.println(e + ": Error in IO");
        }
        //////// closing file /////////////

        try {
            fin.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }


        ////// mergesort ////////
        MergeSort m = new MergeSort();
        m.mergeSort(0,n-1,a);
        m.start();
        try {
            m.join();
        } catch (InterruptedException e) {
            System.out.println(e + ": Thread interrupted");
        }

        for (int s = 0; s < n; s++) {
            if(s != n-1)
            System.out.print(a[s] + " ");
            else if(s == n-1)
            System.out.print(a[s]);
        }
    }
}




class MergeSort extends Thread{
    private static final int MAX_THREADS = 4;
    public void merge(int l,int mid,int r, int a[])
{
    int n1 = mid - l + 1;
    int n2 = r - mid;
    int left[] = new int[n1];
    int right[] = new int[n2];
    for(int i = 0;i<n1;i++)
    {
        left[i] = a[l+i];
    }
    for(int i = 0;i<n2;i++)
    {
        right[i] = a[mid + i+1];
    }
    int k = l;
    int i = 0, j = 0;
    while(i<n1 && j<n2)
    {
        if(left[i]>=right[j])
        {
            a[k++] = right[j];
            j++;
        }
        else
        {
            a[k++] = left[i];
            i++;
        }
    }
    while(i<n1)
    {
            a[k++] = left[i];
            i++;
    }
    while(j<n2)
    {
            a[k++] = right[j];
            j++;
    }
}


public void mergeSort(int l, int r, int a[]) {
    if (l < r)
    {
        int mid = (l + r) / 2;
  
            // Create two separate threads to sort the left and right halves  
                mergeSort(l, mid,a);
                mergeSort(mid + 1, r,a);
                merge(l, mid, r, a);
        }  
    
    }

public void run()
{
    mrgsrt();
}

    public static void tSort(Integer[] a){
      final int length = a.length;
      boolean exact = length%MAX_THREADS == 0;
      int ml = exact? length/MAX_THREADS: length/(MAX_THREADS-1);
      ml = ml < MAX_THREADS? MAX_THREADS : ml;
      Thread SortThreads[] = new Thread[4];
      for(int i=0; i < length; i+=ml){
          int l = i;
          int remain = (length)-i;
          int r = remain < ml? i+(remain-1): i+(ml-1);  
      }
      for(Thread t: SortThreads){
          try{
              t.join();
          } catch(InterruptedException e){System.out.println(e+": Thread interrupted");}
      }
    }

    public void mrgsrt(){

    }
    

public void mergesort(int l, int r, int a[]) {
    if (l < r)
    {
        int mid = (l + r) / 2;
  
            // Create two separate threads to sort the left and right halves  
            Thread leftThread = new Thread(() -> mergeSort(l, mid,a));  
            Thread rightThread = new Thread(() -> mergeSort(mid + 1, r,a));  
  
            // Start both threads  
            leftThread.start();  
            rightThread.start();  
  
            try {  
                // Wait for both threads to complete  
                leftThread.join();  
                rightThread.join();  
  
                // Merge the sorted halves  
                merge(l,mid,r,a);  
            } catch (InterruptedException e)   
            {  
                System.out.println(e+": Thread interrupted");  
            }  
        }  
    
    }
    }
