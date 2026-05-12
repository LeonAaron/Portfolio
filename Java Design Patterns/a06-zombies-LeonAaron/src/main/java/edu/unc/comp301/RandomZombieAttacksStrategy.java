package edu.unc.comp301;

public class RandomZombieAttacksStrategy implements IDayStrategy {
  @Override
  public void execute(Base base, int durationMilliseconds) throws InterruptedException {
    int attackDuration = durationMilliseconds / 5;

    Thread.sleep(attackDuration);

    base.startAttack();
    Thread.sleep(attackDuration);
    base.endAttack();

    Thread.sleep(attackDuration);

    base.startAttack();
    Thread.sleep(attackDuration);
    base.endAttack();

    Thread.sleep(attackDuration);
  }
}
