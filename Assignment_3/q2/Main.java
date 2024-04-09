import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.io.Serializable;
import java.text.DecimalFormat;


public class Main {
    public static void main(String[] args) {
        String fileName = args[0]; // Replace "data.txt" with the actual file name
        Student[] s_arr = new Student[0];
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+"); // Assuming data is space-separated

                if (parts[0].equals("insert")) {
                    try {
                        Student s = new Student(Integer.parseInt(parts[1]), parts[2], Programme.valueOf(parts[3]),
                                Double.parseDouble(parts[4]));
                        s_arr = StudentRecordOps.insert(s_arr, s);
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae + " : Programme name invalid");
                    } catch (DuplicateID dd) {
                        System.out.println(dd);
                    }
                }

                else if (parts[0].equals("delete")) {
                    try {
                        s_arr = StudentRecordOps.delete(s_arr, Integer.parseInt(parts[1]));
                    }
                    catch (IndexNotFound nf) {
                        System.out.println(nf);
                    }
                }
                
                else if (parts[0].equals("display"))
                {
                    try{
                        StudentRecordOps.display(s_arr, Integer.parseInt(parts[1]));
                    }
                    catch (IndexNotFound nf) {
                        System.out.println(nf);
                    }
                }
                else if(parts[0].equals("stats"))
                {
                    StudentRecordOps.stats(s_arr);
                }
                else if(parts[0].equals("save"))
                {
                    StudentRecordOps.save(s_arr,parts[1]);
                }
                else if(parts[0].equals("dump"))
                {
                    StudentRecordOps.dump(s_arr);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

    }
}

class Student implements Serializable{
    int ID;
    String Name;
    Programme p;
    double CGPA;

    Student(int ID, String Name, Programme p, double CGPA) {
        this.ID = ID;
        this.Name = Name;
        this.p = p;
        this.CGPA = CGPA;
    }
}

class StudentRecordOps {
    public static Student[] insert(Student[] s_arr, Student new_student) throws DuplicateID {
        List<Student> a = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < s_arr.length; i++) {
            if (s_arr[i].ID == new_student.ID) {
                throw new DuplicateID(new_student.ID);
            } else if (s_arr[i].ID > new_student.ID && t == 0) {
                a.add(new_student);
                t = 1;
            }
            a.add(s_arr[i]);
        }
        if(t == 0)
            {
                a.add(new_student);
            }
        System.out.println("Insert OK id: " + new_student.ID);
        Student x[] = new Student[a.size()];
        x = a.toArray(x);
        return x;
    }

    public static Student[] delete(Student[] s_arr, int id) throws IndexNotFound {
        List<Student> a = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < s_arr.length; i++) {
            if (s_arr[i].ID == id) {
                t = 1;
            }
            else
            {
                a.add(s_arr[i]);
            }
        }
        if(t == 0){throw new IndexNotFound(id);}
        System.out.println("Delete OK id: " + id);
        Student x[] = new Student[a.size()];
        x = a.toArray(x);
        return x;

    }

    public static void display(Student[] s_arr,int id) throws IndexNotFound{
        int t = 0;
        int idx = 0;
        for (int i = 0; i < s_arr.length; i++) {
            if (s_arr[i].ID == id) {
                t = 1;
                idx = i;
                break;
            }
        }
        if(t == 0){throw new IndexNotFound(id);}
        Student s = s_arr[idx];
        System.out.println(s.ID + " " + s.Name + " " + s.p + " " + String.format("%.2f", s.CGPA));
    }

    public static void stats(Student[] s_array)
    {
        int numofelems = s_array.length;
        double average = 0;
        for(int i = 0;i<s_array.length;i++)
        {
            average+=s_array[i].CGPA;
        }
        if(numofelems != 0)
        average/=numofelems;
        System.out.println("#records: "+numofelems+"; avg CGPA: "+String.format("%.2f", average));
    }
    public static void save(Student[] s_arr, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(s_arr);
            System.out.println("Save OK filename: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void dump(Student s_array[])
    {
        try
        {
        for(int i = 0;i<s_array.length;i++)
        {
            StudentRecordOps.display(s_array,s_array[i].ID);
        }
        }
        catch (IndexNotFound nf) {
            System.out.println(nf);
        }
    }
}

enum Programme {
    CSIS, EEE, Mechanical, Chemical, Civil, Maths, Biology, Physics, Chemistry, Pharmacy, EcoFin, HSS;
}

class DuplicateID extends Exception {
    int ID;

    DuplicateID(int ID) {
        this.ID = ID;
    }

    public String toString() {
        return "Exception: Duplicate ID " + ID;
    }
}
class IndexNotFound extends Exception {
    int ID;

    IndexNotFound(int ID) {
        this.ID = ID;
    }

    public String toString() {
        return "Exception: Non-existent ID " + ID;
    }
}