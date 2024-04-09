import java.util.Scanner;

class Node {
    public int data;
    public Node next;
    Node(int data)
    {
        this.data = data;
        next = null;
    }
}

class LinkedList {
  Node head;

  public LinkedList() {
    head = null;
  }

  public void add(int data) {
    Node cur = head;
    if(cur == null)
    {
        head = new Node(data);
        return;
    }
    while(cur.next!=null)
    {
        cur = cur.next;
    }
    cur.next = new Node(data);
  }

  public void remove(int data) {
    if(head == null) return;
    if(head.data == data)
    {
        head = head.next;
        return;
    }
    Node cur = head;
    while(cur != null && cur.next.data != data)
    {
        cur = cur.next;
    }
    if(cur != null)
    {
        cur.next = cur.next.next;
    }
    return;
  }

  public void display() {
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }
}


public class Main {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      // Menu:
      // 1. Add
      // 2. Remove
      // 3. Display
      // 4. Exit
      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          int valueToAdd = scanner.nextInt();
          list.add(valueToAdd);
          break;
        case 2:
          int valueToRemove = scanner.nextInt();
          list.remove(valueToRemove);
          break;
        case 3:
          list.display();
          break;
        case 4:
          scanner.close();
          System.exit(0);
      }
    }
  }
}