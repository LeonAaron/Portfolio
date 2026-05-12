package com.comp301.a01heist;

import java.util.Random;

public class AccessKey {
  private int uses;
  // final means that field cannot be modified
  private final int key; // Random 6 digit key

  // Constructor
  public AccessKey() {
    this.uses = 0;

    // Create random object and generate random integer within bounds
    Random random = new Random();
    this.key = random.nextInt(100000, 1000000);
  }

  public boolean isValid() {
    // Use this to reference current object
    this.uses++;
    return uses % 3 != 0;
  }

  public static void main(String[] args) {
    AccessKey key = new AccessKey();
    System.out.println(key.isValid());
    System.out.println(key.isValid());
    System.out.println(key.isValid());
  }
}
