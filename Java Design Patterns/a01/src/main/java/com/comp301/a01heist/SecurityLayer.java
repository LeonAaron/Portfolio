package com.comp301.a01heist;

public class SecurityLayer {
  private final String passphrase;
  private final String biometric;

  public SecurityLayer(String passphrase, String biometric) {
    this.biometric = biometric;
    // Hash a string
    this.passphrase = Integer.toHexString(passphrase.hashCode());
  }

  public boolean authenticate(String passphraseAttempt, String biometricAttempt) {

    // ASK ABOUT ENCAPSULATION
    passphraseAttempt = Integer.toHexString(passphraseAttempt.hashCode());
    return passphraseAttempt.equals(this.passphrase) && biometricAttempt.equals(this.biometric);
  }
}
