package edu.unc.comp301;

import static javafx.application.Application.launch;

public class Main {

  public static Robot build() {
    Robot robot = new BasicRobot();
    robot = new JetpackDecorator(robot);
    robot = new LegsArmorDecorator(robot, ArmorType.GOLD);
    robot = new HeadArmorDecorator(robot, ArmorType.DIAMOND);
    robot = new TorsoArmorDecorator(robot, ArmorType.DIAMOND);
    robot = new CustomDecorator(robot);
    // INSERT CUSTOM HEALTH DECORATOR
    robot = new PowerUpDecorator(robot, PowerType.JEDI);

    return robot;

    // CURR- Health: 150, Shield: 120 GOOD, Power: 10 GOOD
  }

  public static void main(String[] args) {
    launch(RobotBuilder.class);
  }
}
