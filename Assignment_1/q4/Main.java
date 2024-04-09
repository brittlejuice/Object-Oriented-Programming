import java.util.Scanner;



class Solution{
    int divide(int[] arr,int k)
    {
        int n = arr.length;
        int l = 0,r = 0;
        for(int i = 0;i<n;i++)
        {
            r+=arr[i];
            if(l<arr[i]) l = arr[i];
        }
        int ans = 0;
        while(l<=r)
        {
            int part = 1,sm = 0;
            int mid = l + (r-l)/2;
            for(int i = 0;i<n;i++)
            {
                if(sm+arr[i]<=mid)
                {
                    sm+=arr[i];
                }
                else
                {
                    part++;
                    sm = arr[i];
                }
            }
            if(part>k)
            {
                l = mid+1;
            }
            else
            {
                r = mid-1;
                ans = mid;
            }
        }
        return ans;
    }
}



public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        sc.close();
        Solution s = new Solution();
        System.out.println(s.divide(arr,k));
    }

}
