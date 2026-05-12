package edu.unc.comp301;

public class ZombieApocalypse {
  protected static Base base;
  protected static Survivor[] survivors;
  protected static Thread[] survivorThreads;

  public static void main(String[] args) {

    startSimulation(5);
    simulateDayNightCycle(10000, new QuietDayStrategy());
    endSimulation();
  }

  public static void startSimulation(int numSurvivors) {
    base = new Base();
    survivors = new Survivor[numSurvivors];
    survivorThreads = new Thread[numSurvivors];
    for (int i = 0; i < numSurvivors; i++) {
      Survivor s = new Survivor(base);
      survivors[i] = s;
      s.setName("Survivor-" + (i + 1));
      Thread t = new Thread(s, "Survivor-" + (i + 1));
      survivorThreads[i] = t;
      t.start();
    }
  }

  public static void simulateDayNightCycle(int milliseconds, IDayStrategy events) {
    try {
      events.execute(base, milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace(System.err);
    }
  }

  public static void endSimulation() {
    System.out.println("Simulation ending...");
    for (Survivor currSurvivor : survivors) {
      currSurvivor.stop();
    }

    for (Thread currThread : survivorThreads) {
      try {
        currThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace(System.err);
      }
    }

    System.out.println("All survivors have stopped. Simulation over.");
  }
}
