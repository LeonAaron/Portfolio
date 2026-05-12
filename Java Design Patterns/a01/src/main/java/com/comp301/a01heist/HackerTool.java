package com.comp301.a01heist;

import java.lang.reflect.Field;

public class HackerTool {

  public static void tryBreakVault(Vault vault) {

    try {
      Field secret = Vault.class.getDeclaredField("secret");
      secret.setAccessible(true);
      String value = (String) secret.get(vault);
      System.out.println("Hacked Secret: " + value);
      // For all exceptions (Exception e)
    } catch (Exception e) {
      System.out.println("HackerTool failed.");
    }
  }


}


}




















