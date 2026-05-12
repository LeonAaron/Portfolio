package edu.unc.comp301;

import java.util.Random;

public class Survivor implements ISurvivor {
  private Base base;
  private String name;

  Random random = new Random();

  private volatile boolean flag = true;
  private volatile boolean defending = false;

  public Survivor(Base base) {
    this.base = base;
  }

  @Override
  public void stop() {
    this.flag = false;
  }

  @Override
  public void run() {
    while (flag) {
      try {
        if (base.isUnderAttack()) {
          defending = true;
          System.out.println(this.name + " is fighting the zombies.");
          Thread.sleep(2000);
          defending = false;
        } else {
          this.performAction();
        }
      } catch (InterruptedException e) {
        this.stop();
      }
    }
  }

  protected void performAction() throws InterruptedException {
    // Generate random number between 0-2
    int num = random.nextInt(3);
    if (num == 0) {
      this.scavenge();
    } else if (num == 1) {
      this.fortify();
    } else {
      this.rest();
    }
  }

  protected synchronized void scavenge() throws InterruptedException {
    System.out.println("The survivor is scavenging");
    int seconds = random.nextInt(1, 5);
    int milliseconds = seconds * 1000;
    Thread.sleep(milliseconds);
    this.base.addSupplies(2);
  }

  protected void fortify() throws InterruptedException {
    System.out.print("The survivor is fortifying the base and will take 2 seconds to complete.");
    this.base.useTool("fortification");
    Thread.sleep(2000);
  }

  protected void rest() throws InterruptedException {
    System.out.println("The survivor is resting");
    Thread.sleep(2000);
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isDefending() {
    return this.defending;
  }
}
