package edu.unc.comp301.a03connectcarolina;

public class DuoAuthenticationFailedException extends Exception {
  public DuoAuthenticationFailedException(String message) {
    super("Authentication failed: " + message);
  }
}
