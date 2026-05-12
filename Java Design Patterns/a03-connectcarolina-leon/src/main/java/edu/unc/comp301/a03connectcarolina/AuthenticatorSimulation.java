package edu.unc.comp301.a03connectcarolina;

import java.util.ArrayList;
import java.util.List;

public class AuthenticatorSimulation {
  public static void main(String[] args)
      throws CLEAlreadyScannedException, CLEEventNotFoundException {
    Student student = new Student("Leon", 123456789, 0);
    Adept adept = new Adept();

    List<String> events = new ArrayList<>();

    adept.validateScan("Leadership Summit", student.getScannedCLEEvents());

    student.setCLEEvents(adept.getCLECredits("Leadership Summit", events));
    System.out.println("Addition of Leadership Summit successful");

    List<Student> students = new ArrayList<>();
    students.add(student);
    Jedi.initStudents(students);

    boolean authenticationWindow;
    try {
      Jedi.duoAuthenticate(student, "Wednesday");
      authenticationWindow = true;
    } catch (DuoAuthenticationFailedException e) {
      System.err.println(e.getMessage());
      authenticationWindow = false;
    } finally {
      System.out.println("Thank you for visiting ConnectCarolina");
    }

    if (authenticationWindow) {
      System.out.println("You are authenticated for the next 10 minutes");
    } else {
      System.out.println("You will have to authenticate again");
    }
  }
}
