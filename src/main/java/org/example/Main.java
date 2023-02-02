package org.example;

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    List<Student> students = new ArrayList<>(List.of(
        new Student("Alice"),
        new Student("Bob"),
        new Student("Charlie")
    ));

//    checkAttendance(students);

    Exam exam = new Exam("CS", "Java",
        "03-02-2023", "4a");
    System.out.println(exam);

//    Exam exam2 = new Exam();
//    System.out.println(exam2);

    Exam exam3 = new Exam(students);
    exam3.mark();
    System.out.println(exam3);
  }

  private static void checkAttendance(List<Student> students) {
    Scanner scanner = new Scanner(System.in);
    for (int i = 0; i < students.size(); i++) {
      while (true) {  // wait until the user enters either "y" or "n"

        Student student = students.get(i);
        System.out.print("Please enter if "
            + student.getName()
            + " is present [y/n]\n>>> ");
        String response = scanner.nextLine();

        if (response.equals("y")) {
          student.setPresent(true);
          break;
        } else if (response.equals("n")) {
          student.setPresent(false);
          break;
        }

      }
    }


    String message = "Attendance record:\n"; // even better: use a
    // StringBuilder
    for (Student student : students) {  // enhanced for-loop
      message += student + "\n";
    }
    System.out.print(message);

    FileWriter fileWriter;
    try {
      fileWriter = new FileWriter("attendance-record.txt");
      PrintWriter writer = new PrintWriter(fileWriter);
      writer.println(message);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

class Exam {
  private String subject;
  private String topic;
  private String date;
  private String period;
  private Map<Student, Optional<Integer>> marks = new HashMap<>();
  private Boolean marked = false;

  public Exam(String subject, String topic, String date, String period) {
    this.subject = subject;
    this.topic = topic;
    this.date = date;
    this.period = period;
  }

  public Exam(List<Student> students) {
    this();
    for (Student student : students) {
      marks.put(student, null);
    }
  }

  public Exam() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter subject\n>>> ");
    subject = scanner.next();
    System.out.print("Enter topic\n>>> ");
    topic = scanner.next();
    System.out.print("Enter date\n>>> ");
    date = scanner.next();
    System.out.print("Enter period\n>>> ");
    period = scanner.next();
  }

  public void mark() {
    Scanner scanner = new Scanner(System.in);
    for (Student student : marks.keySet()) {
      while (true) {  // wait until the user enters either "y" or "n"

        System.out.print(
            "Please enter the mark for "
                + student.getName()
                + " [0-100]\n>>> ");
        int mark = scanner.nextInt();
        if (mark >= 0 && mark <= 100) {
          marks.put(student, Optional.of(mark));
          break;
        }

      }
    }

    marked = true;
  }

  @Override public String toString() {
    String basicInfo = "Exam on " + date
        + " during period " + period
        + " in " + subject
        + " (" + topic + ")";
    if (!marked) {
      return basicInfo;
    }
    basicInfo += "\nMarks:";
    for (Student student : marks.keySet()) {
      String name = student.getName();
      int mark = marks.get(student).get();
      basicInfo += "\n" + name + ": " + mark;
    }
    return basicInfo;
  }
}

class Student {
  private String name;
  private Boolean present;

  public Student(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setPresent(Boolean present) {
    this.present = present;
  }

  @Override public String toString() {
    String presence = present ? "present" : "absent"; // ternary operator,
    // could also use if statement here
    return name + ": " + presence;
  }
}
