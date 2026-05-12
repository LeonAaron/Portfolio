package com.comp301.a01heist;

public class LogMonitor {
  private int failedAttempts;
  private static final int MAX_FAILURES = 5;

  public LogMonitor() {
    this.failedAttempts = 0;
  }

  public void recordFailure() {
    this.failedAttempts++;
  }

  public boolean isLocked() {
    return this.failedAttempts >= LogMonitor.MAX_FAILURES;
  }
}
