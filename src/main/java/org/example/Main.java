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

  public Exam(String subject, String topic, String date, String period) {
    this.subject = subject;
    this.topic = topic;
    this.date = date;
    this.period = period;
  }

  @Override
  public String toString() {
    return "Exam on " + date + " during period " + period + " in " + subject + " (" + topic + ")";
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
