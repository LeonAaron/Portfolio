package edu.unc.comp301.a03connectcarolina;

import java.util.HashMap;
import java.util.Map;

public class Novice {

  private static final Map<String, Double> GRADE_MAP = new HashMap<>();

  static {
    GRADE_MAP.put("A", 4.0);
    GRADE_MAP.put("A-", 3.7);
    GRADE_MAP.put("B+", 3.3);
    GRADE_MAP.put("B", 3.0);
    GRADE_MAP.put("B-", 2.7);
    GRADE_MAP.put("C+", 2.3);
    GRADE_MAP.put("C", 2.0);
    GRADE_MAP.put("C-", 1.7);
    GRADE_MAP.put("D+", 1.3);
    GRADE_MAP.put("D", 1.0);
    GRADE_MAP.put("F", 0.0);
  }

  public static double charToGrade(String grade) {
    if (grade == null) {
      return -1; // handle null input gracefully
    }
    Double value = GRADE_MAP.get(grade.toUpperCase());
    return (value == null) ? -1 : value;
  }

  public static String calculateGPA(int numClasses, String[] grades) {
    if (grades == null || numClasses <= 0) {
      throw new IllegalArgumentException("negative number of classes");
    }
    if (numClasses != grades.length) {
      throw new IllegalArgumentException("mismatch between number of classes and grades");
    }

    double totalPoints = 0.0;

    for (String grade : grades) {
      double numericGrade = charToGrade(grade);

      if (numericGrade == -1) {
        throw new IllegalArgumentException("Invalid grade in grades array");
      }

      totalPoints += numericGrade;
    }

    double totalGPA = totalPoints / numClasses;

    return "Your calculated GPA is: " + totalGPA;
  }

  public static void main(String[] args) {
    String[] grades = {"A"};
    System.out.println(Novice.calculateGPA(1, grades));
  }
}
