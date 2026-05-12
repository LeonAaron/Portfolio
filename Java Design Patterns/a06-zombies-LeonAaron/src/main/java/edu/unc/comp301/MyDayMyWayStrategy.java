package edu.unc.comp301;

public class MyDayMyWayStrategy implements IDayStrategy {
  @Override
  public void execute(Base base, int durationMilliseconds) throws InterruptedException {
    int attackDuration = durationMilliseconds / 10;

    Thread.sleep(attackDuration);

    base.startAttack();
    Thread.sleep(attackDuration);
    base.endAttack();

    Thread.sleep(attackDuration);

    base.startAttack();
    Thread.sleep(attackDuration);
    base.endAttack();

    Thread.sleep(attackDuration);

    base.startAttack();
    Thread.sleep(attackDuration);
    base.endAttack();

    Thread.sleep(attackDuration);

    base.startAttack();
    Thread.sleep(attackDuration);
    base.endAttack();

    Thread.sleep(attackDuration);

    base.startAttack();
    Thread.sleep(attackDuration);
    base.endAttack();
  }
}
