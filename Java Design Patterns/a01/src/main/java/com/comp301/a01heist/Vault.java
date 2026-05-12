package com.comp301.a01heist;

public class Vault {
  private final String secret;
  private boolean isLocked;
  private LogMonitor logMonitor;

  public Vault(String secret) {
    this.secret = secret;
    this.isLocked = true;
    this.logMonitor = new LogMonitor();
  }

  // Constructor #2
  public Vault() {
    this.secret = "TOP_SECRET_LAUNCH_CODES";
    this.isLocked = true;
    this.logMonitor = new LogMonitor();
  }

  public boolean accessVault(
      SecurityLayer layer, AccessKey key, String passAttempt, String bioAttempt) {
    if (this.logMonitor.isLocked()) {
      return false;
    }

    if (layer.authenticate(passAttempt, bioAttempt)) {
      if (key.isValid()) {
        this.isLocked = false;
        return true;
      }
    }
    this.logMonitor.recordFailure();
    return false;
  }

  public boolean isLocked() {
    return this.isLocked;
  }
}
