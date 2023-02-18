package org.example;

import java.util.*;
import java.io.*;

public class Attendance implements Serializable {
  private String[] names;
  private Map<String, Boolean> presence = new HashMap<>();

  public Attendance(String[] names) {
    this.names = names;
    for (String name : this.names) {
      presence.put(name, false);
    }
  }

  public String[] getNames() {
    return names;
  }

  public void printAttendance() {
    String message = "";
    for (String name : names) {  // enhanced for-loop
      boolean present = presence.get(name);
      String status = present ? "present" : "absent";
      message += name + ": " + status + "\n";
    }
    System.out.print(message);
  }

  public void setPresence(int index, boolean present) {
    String name = names[index];
    presence.put(name, present);
  }

  public boolean getPresence(int index) {
    return presence.get(names[index]);
  }
}
