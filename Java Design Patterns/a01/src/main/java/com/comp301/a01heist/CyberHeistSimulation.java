package com.comp301.a01heist;

public class CyberHeistSimulation {
  public static void main(String[] args) {
    Vault vault = new Vault();
    SecurityLayer securityLayer = new SecurityLayer("hunter2", "retinaScanA");
    AccessKey accessKey = new AccessKey();

    boolean attempt1 = vault.accessVault(securityLayer, accessKey, "incorrectPass", "incorrectBio");
    boolean attempt2 = vault.accessVault(securityLayer, accessKey, "hunter2", "retinaScanA");

    System.out.println("Attempt 1: Access granted? " + attempt1 + " (expected: false)");
    System.out.println("Attempt 2: Access granted? " + attempt2 + " (expected: true)");

    HackerTool.tryBreakVault(vault);
  }
}
