import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0]; // Replace "data.txt" with the actual file name
        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        List<Double> doubles = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                strings.clear();
                integers.clear();
                doubles.clear();
                // String egno = scanner.nextLine();
                // System.out.println(egno);
                String type = scanner.nextLine();
                String line = scanner.nextLine();
                String temp;
                if (scanner.hasNextLine()) {
                    temp = scanner.nextLine();
                }
                String[] parts = line.split("\\s+"); // Assuming data is space-separated

                if (type.equals("Integer")) {
                    for (String part : parts) {
                        try {
                            int intValue = Integer.parseInt(part);
                            integers.add(intValue);
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                        }
                    }
                    Lambda<Integer> l = (a) -> {
                        MergeSort ms = new MergeSort();
                        ms.mergeSort(0, a.length - 1, a);
                        for (int i = 0;i<a.length -1;i++) {
                            System.out.print(a[i] + " ");
                        }
                        System.out.println(a[a.length-1]);
                        if(scanner.hasNextLine())
                        System.out.println();
                    };

                    Integer[] arr = new Integer[integers.size()];
                    arr = integers.toArray(arr);
                    Sorter<Integer> s = new Sorter<Integer>(arr);
                    s.sort(l);
                }

                else if (type.equals("String")) {
                    for (String part : parts) {
                        try {
                            strings.add(part);
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                        }
                    }
                    Lambda<String> l = (a) -> {
                        MergeSort ms = new MergeSort();
                        ms.mergeSortS(0, a.length - 1, a);
                        for (int i = 0;i<a.length -1;i++) {
                            System.out.print(a[i] + " ");
                        }
                        System.out.println(a[a.length-1]);
                        if(scanner.hasNextLine())
                        System.out.println();
                    };

                    String[] arr = new String[strings.size()];
                    arr = strings.toArray(arr);
                    Sorter<String> s = new Sorter<String>(arr);
                    s.sort(l);
                }

                else {
                    for (String part : parts) {
                        try {
                            double doubleValue = Double.parseDouble(part);
                            doubles.add(doubleValue);
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                        }
                    }
                        Lambda<Double> l = (a) -> {
                            MergeSort ms = new MergeSort();
                            ms.mergeSortD(0, a.length - 1, a);
                            for (int i = 0;i<a.length -1;i++) {
                                System.out.print(a[i] + " ");
                            }
                            System.out.println(a[a.length-1]);
                            if(scanner.hasNextLine())
                        System.out.println();
                        };
    
                        Double[] arr = new Double[doubles.size()];
                        arr = doubles.toArray(arr);
                        Sorter<Double> s = new Sorter<Double>(arr);
                        s.sort(l);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        // System.out.println("Strings read from file: " + strings);
        // System.out.println("Integers read from file: " + integers);
        // System.out.println("Doubles read from file: " + doubles);
        //////////// Defining sorting lfn///////////////////
    }
}

interface Lambda<T> {
    void sortL(T[] s);
}

class Sorter<T> {
    T[] arr;

    Sorter(T[] arr) {
        this.arr = arr;
    }

    public void sort(Lambda<T> l) {
        l.sortL(arr);
    }
}

class MergeSort{
    public void merge(int l, int mid, int r, Integer a[]) {
        int n1 = mid - l + 1;
        int n2 = r - mid;
        Integer left[] = new Integer[n1];
        Integer right[] = new Integer[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = a[l + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = a[mid + i + 1];
        }
        int k = l;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (left[i] >= right[j]) {
                a[k++] = right[j];
                j++;
            } else {
                a[k++] = left[i];
                i++;
            }
        }
        while (i < n1) {
            a[k++] = left[i];
            i++;
        }
        while (j < n2) {
            a[k++] = right[j];
            j++;
        }
    }

    public void mergeSort(int l, int r, Integer a[]) {
        if (l < r) {
            int mid = (l + r) / 2;

            // Create two separate threads to sort the left and right halves
            mergeSort(l, mid, a);
            mergeSort(mid + 1, r, a);
            merge(l, mid, r, a);
        }
    }
    public void mergeD(int l, int mid, int r, Double a[]) {
        int n1 = mid - l + 1;
        int n2 = r - mid;
        Double left[] = new Double[n1];
        Double right[] = new Double[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = a[l + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = a[mid + i + 1];
        }
        int k = l;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (left[i] >= right[j]) {
                a[k++] = right[j];
                j++;
            } else {
                a[k++] = left[i];
                i++;
            }
        }
        while (i < n1) {
            a[k++] = left[i];
            i++;
        }
        while (j < n2) {
            a[k++] = right[j];
            j++;
        }
    }

    public void mergeSortD(int l, int r, Double a[]) {
        if (l < r) {
            int mid = (l + r) / 2;

            // Create two separate threads to sort the left and right halves
            mergeSortD(l, mid, a);
            mergeSortD(mid + 1, r, a);
            mergeD(l, mid, r, a);
        }
}




public void mergeS(int l, int mid, int r, String a[]) {
        int n1 = mid - l + 1;
        int n2 = r - mid;
        String left[] = new String[n1];
        String right[] = new String[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = a[l + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = a[mid + i + 1];
        }
        int k = l;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            String L = left[i].toLowerCase();
            String R = right[j].toLowerCase();
            // System.out.println(L+" "+R);
            // System.out.println(MergeSort.compare(L,R));
            if (MergeSort.compare(L,R)==1) {
                a[k++] = left[i];
                i++;
            }
            else if(MergeSort.compare(L,R)==-1)
            {
            if (MergeSort.compare(left[i],right[j])==1) {
                a[k++] = left[i];
                i++;
            }
            else
            {
                a[k++] = right[j];
                j++;
            }
        }
            else if(MergeSort.compare(L,R)==0){
                a[k++] = right[j];
                j++;
            }
        }
        while (i < n1) {
            a[k++] = left[i];
            i++;
        }
        while (j < n2) {
            a[k++] = right[j];
            j++;
        }
    }

    public void mergeSortS(int l, int r, String a[]) {
        if (l < r) {
            int mid = (l + r) / 2;

            // Create two separate threads to sort the left and right halves
            mergeSortS(l, mid, a);
            mergeSortS(mid + 1, r, a);
            mergeS(l, mid, r, a);
        }
}

public static int compare(String s1,String s2)
{
    int n = Math.min(s1.length(),s2.length());
    for(int i = 0;i<n;i++)
    {
        if(Math.abs(s1.charAt(i)-s2.charAt(i)) == 32)
        {
            if(s1.charAt(i)<s2.charAt(i))
            {
                return 1;
            }
            else return 0;
        }
        else
        {
            char c1 = s1.charAt(i);
            // String c1d = String.valueOf(c1);
            // c1d = c1d.toLowerCase();
            char c2 = s2.charAt(i);
            // String c2d = String.valueOf(c2);
            // c2d = c2d.toLowerCase();
            if(65<=c1 && c1<=90)
            {
                c1+=32;
            }
            if(65<=c2 && c2<=90)
            {
                c2+=32;
            }
            if(c1 == c2)
            {
                continue;
            }
            else if(c1<c2)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
    if(s1.length() == s2.length())
        {
            return -1;
        }
    else if(s1.length() < s2.length())
    return 1;
    return 0;
}


}

