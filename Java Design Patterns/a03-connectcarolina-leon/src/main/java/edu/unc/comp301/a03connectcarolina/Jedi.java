package edu.unc.comp301.a03connectcarolina;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Jedi {

  // Map day of week to set of students
  private static Map<String, Set<Student>> enrollment = new HashMap<>();

  // Output day of week
  public static String calculateValidDay(double credits) {
    String day;

    if (credits >= 0 && credits <= 55) {
      day = "Wednesday";
    } else if (credits > 55 && credits <= 100) {
      day = "Tuesday";
    } else if (credits > 100) {
      day = "Monday";
    } else {
      throw new IllegalArgumentException("Invalid credit amount");
    }
    return day;
  }

  public static void initStudents(List<Student> students) {
    enrollment.put("Monday", new HashSet<>());
    enrollment.put("Tuesday", new HashSet<>());
    enrollment.put("Wednesday", new HashSet<>());
    enrollment.put("Thursday", new HashSet<>());
    enrollment.put("Friday", new HashSet<>());

    if (students == null) {
      return;
    }

    for (Student student : students) {
      if (student == null) {
        return;
      }
      try {
        String key = calculateValidDay(student.getCredits());
        enrollment.get(key).add(student);
      } catch (IllegalArgumentException e) {
        System.err.println(student.getName() + " credits invalid");
      }
    }
  }

  public static boolean duoAuthenticate(Student student, String day)
      throws DuoAuthenticationFailedException {
    if (student == null) {
      return false;
    }

    boolean authenticated = false;

    int pid = student.getStudentID();

    String strPid = Integer.toString(pid);
    if (strPid.length() != 9) {
      throw new DuoAuthenticationFailedException("pid not 9 digits");
    }

    if (!enrollment.get(day).contains(student)) {
      throw new DuoAuthenticationFailedException("invalid enrollment date");
    }

    System.out.println("Duo authentication successful! Welcome, " + student.getName() + ".");
    authenticated = true;
    return authenticated;
  }
}
