import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class student {
    private String name;
    private int rollNumber;
    private String grade;
    
    public student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public String getGrade() {
        return grade;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

 class StudentManagementSystem {
    private List<student> students;
    private static final String FILE_PATH = "students.txt";
    
    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudentsFromFile();
    }
    
    public void addStudent(String name, int rollNumber, String grade) {
        student student = new student(name, rollNumber, grade);
        students.add(student);
        saveStudentsToFile();
    }
    
    public void removeStudent(int rollNumber) {
        for (int i = 0; i < students.size(); i++) {
            student student = students.get(i);
            if (student.getRollNumber() == rollNumber) {
                students.remove(i);
                saveStudentsToFile();
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }
    
    public void searchStudent(int rollNumber) {
        for (student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student found:");
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " was  not found in the file.");
    }
    
    public void displayAllStudents() {
        System.out.println("All students:");
        for (student student : students) {
            System.out.println(student);
        }
    }
    
    private void loadStudentsFromFile() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int rollNumber = Integer.parseInt(parts[1].trim());
                    String grade = parts[2].trim();
                    student student = new student(name, rollNumber, grade);
                    students.add(student);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_PATH);
        }
    }
    
    private void saveStudentsToFile() {
        try (PrintWriter writer = new PrintWriter(new File(FILE_PATH))) {
            for (student student : students) {
                writer.println(student.getName() + ", " + student.getRollNumber() + ", " + student.getGrade());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong !! Error saving students to file: " + FILE_PATH);
        }
    }
}

class StudentManagementSystemApp {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    system.addStudent(name, rollNumber, grade);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter roll number of the student to remove: ");
                    int rollToRemove = scanner.nextInt();
                    system.removeStudent(rollToRemove);
                    break;
                case 3:
                    System.out.print("Enter roll number of the student to search: ");
                    int rollToSearch = scanner.nextInt();
                    system.searchStudent(rollToSearch);
                    break;
                case 4:
                    system.displayAllStudents();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            
            System.out.println();
        }
    }
}