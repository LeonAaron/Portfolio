package edu.unc.comp301.a03connectcarolina;

public class CLEAlreadyScannedException extends Exception {
  public CLEAlreadyScannedException() {
    super("CLE ALREADY SCANNED!");
  }

  public CLEAlreadyScannedException(String message) {
    super(message);
  }
}
