import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        loop: while(true) {
            System.out.println("--------Welcome to Student Management System--------");
            System.out.println("Please enter your choice: ");
            System.out.println("1. Add new student");
            System.out.println("2. Delete student");
            System.out.println("3. Update information");
            System.out.println("4. Search student");
            System.out.println("5. Quit");

            Scanner sc = new Scanner(System.in);
            String choice = sc.next();

            switch (choice) {
                case "1" -> addStudent(studentList);
                case "2" -> deleteStudent(studentList);
                case "3" -> updateInformation(studentList);
                case "4" -> searchStudent(studentList);
                case "5" -> {
                    break loop;
                }
                default -> System.out.println("Illegal input, please enter your choice again: ");
            }
        }
        System.exit(0);
    }

    public static void addStudent(ArrayList<Student> list){
        Student s = new Student();
        System.out.println("To add a new student, please enter more information.");

        Scanner sc = null;
        String studentId = null;
        while (true) {
            System.out.println("Please input the student ID: ");
            sc = new Scanner(System.in);
            studentId = sc.next();
            if (idExists(studentId, list)) {
                System.out.println("ID already exists, please input a different ID");
                continue;
            } else {
                s.setId(studentId);
                break;
            }
        }

        System.out.println("Please input the student name: ");
        String studentName = sc.next();
        s.setName(studentName);

        System.out.println("Please input the student age: ");
        int studentAge = sc.nextInt();
        s.setAge(studentAge);

        System.out.println("Please input the student address: ");
        String studentAddress = sc.next();
        s.setAddress(studentAddress);

        list.add(s);
        System.out.println("New student added to the list");
    }

    public static void deleteStudent (ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please input the id of the student to be deleted: ");
            String id = sc.next();
            if(!idExists(id, list)) {
                System.out.println("Target student not exists, please input a different ID");
                continue;
            }
            for(int i=0; i<list.size(); i++){
                if(list.get(i).getId().equals(id)){
                    list.remove(i);
                    System.out.println("Target student is deleted");
                }
            }
            break;
        }
    }

    public static void updateInformation(ArrayList<Student> list){
        System.out.println("Please enter the student id to update information");
        Scanner sc = new Scanner(System.in);
        while(true){
            String id = sc.next();
            if(!idExists(id, list)){
                System.out.println("Target student not exists, please enter a different id");
                continue;
            }

            for(int i=0; i<list.size(); i++){
                Student target = list.get(i);
                if(target.getId().equals(id)){
                    System.out.println("Please enter the information to be updated: ");
                    String choice = sc.next();
                    switch(choice){
                        case "name" -> {
                            System.out.println("Please enter a new name: ");
                            String newName = sc.next();
                            target.setName(newName);
                        }
                        case "age" -> {
                            System.out.println("Please enter a new age: ");
                            int newAge = sc.nextInt();
                            target.setAge(newAge);
                        }
                        case "address" -> {
                            System.out.println("Please enter a new address: ");
                            String newAddress = sc.next();
                            target.setAddress(newAddress);
                        }
                        default -> {
                            System.out.println("Illegal choice, please choose name, age or address to be updated.");
                        }
                    }
                    break;
                }
            }
            break;
        }
    }

    public static void searchStudent(ArrayList<Student> list){
        if(list.size() == 0){
            System.out.println("List empty, please add students to the list before searching");
            return;
        }
        System.out.println("Please input the target student's ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        for(int i = 0; i<list.size(); i++){
            Student student = list.get(i);
            if(student.getId().equals(id)){
                System.out.println("Target student is in the list, ID: " + student.getId() + ", name: " + student.getName() + ", age: " + student.getAge() + ", address: " + student.getAddress());
            } else{
                System.out.println("Student not found");
            }
        }
    }

    public static boolean idExists(String id, ArrayList<Student> list){
        for(int i=0; i<list.size();i++){
            Student student = list.get(i);
            if(student.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
}