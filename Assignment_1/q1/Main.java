
import java.util.Scanner;


class Sortable{
    public void sort(int[] arr){}
}

class BubbleSort extends Sortable{
    public void sort(int[] arr)
    {
         int c = 10;
        int n = arr.length;
        while(c != 0)
        {
            c = 0;
            for(int i = 0;i<n-1;i++)                                            //RUNTIME POLYMORPHISM
            {
                if(arr[i]>arr[i+1])
                {
                    int temp = arr[i+1];
                    arr[i+1] = arr[i];
                    arr[i] = temp;
                    c++;
                }
            }
        }
    }
}


class SelectionSort extends Sortable{
    public void sort(int[] arr)
    {
        int n = arr.length;
        for(int i = 0;i<n-1;i++)
        {
            int min = i;
            for(int j = i+1;j<n;j++)
            {
                if(arr[min]>arr[j])
                {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Take input for integer array nums
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //size of array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        // Create an instance of BubbleSort and perform sorting
        Sortable bubbleSort = new BubbleSort();
        bubbleSort.sort(nums);
        printArray(nums);

        // Create an instance of SelectionSort and perform sorting
        Sortable selectionSort = new SelectionSort();
        selectionSort.sort(nums);
        printArray(nums);
    }

    // Method to print the elements of an array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
