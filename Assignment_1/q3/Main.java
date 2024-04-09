import java.io.IOException;
import java.util.Scanner;


class CustomException extends Exception
{
  public CustomException(String s)
  {
    super(s);
  }
}


class InitCause{
  void initCause(Throwable ce) throws IOException
  {
    throw new IOException("Chained Exception: Wrong Input taken",ce);
  }
}









public class Main {
    public static void main(String[] args) {
      // main logic as described
      try{
        stringInput();
      }
      catch(CustomException ce)
      {
        try{InitCause i = new InitCause();
        i.initCause(ce);}
        catch(IOException e){
            System.out.println("Caught exception: " + e);
            System.out.println("Original cause: " + e.getCause());
        }
      }
    }
  
    public static void stringInput() throws CustomException {
      // stringInput logic as described
      String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();  
        sc.close();
        int caps = 0,small = 0,words = 1;
        char[] ch = s.toCharArray();
        for(int i = 0;i<s.length();i++)
        {
            if(ch[i] == ' ')words++;
            else if((int)ch[i]>=65 && (int)ch[i]<=90) caps++;
            else if((int)ch[i]>=97 && (int)ch[i]<=122) small++;
        }
        if(words>1)
        {
          throw new CustomException("String contains more than one word");
        }
        else if(caps>=1 && small==0)
        {
          throw new CustomException("String is all capital letters");
        }
        else if(caps==0 && small>=1)
        {
          throw new CustomException("String is all small letters");
        }
        else
        {
          System.out.println(s);
        }
    }
  }
