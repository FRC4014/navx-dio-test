package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Robot extends TimedRobot {

  // navxDioPin method inspired by: https://pdocs.kauailabs.com/navx-mxp/examples/mxp-io-expansion/
  private static int ROBORIO_DIO_COUNT = 10;
  private static int navxDioPin(int n) {
    return ROBORIO_DIO_COUNT + n + (n > 3 ? 4 : 0);
  }

  // Setup 5 Ultrasonics to cover all the NavX Digital IO pins
  public static Ultrasonic[] US = new Ultrasonic[5];
  static {
    for (int i = 0; i < US.length; i++) {
      int t = i * 2;
      int s = t + 1;
      US[i] = new Ultrasonic(navxDioPin(t), navxDioPin(s));
      US[i].setName("trig:" + t + "   sig:" + (s));
      System.out.println("US[" + i + "] = Ultrasonic(" + t + ", "+s+")");
    }
  }

  // setup RoboRIO's
  private static Ultrasonic ROBORIO_U = new Ultrasonic(8, 9);

  @Override
  public void testInit() {
    for (int i = 0; i < US.length; i++) {
      US[i].setAutomaticMode(true);
    }
    ROBORIO_U.setAutomaticMode(true);
  }

  int testPeriodicCalls = 0;
  @Override
  public void testPeriodic() {
    if (testPeriodicCalls++ % 100 == 0) {
      System.out.println("---");

      // print NavX's
      for (int i = 0; i < US.length; i++) {
        System.out.print(US[i].getName());
        double mm = US[i].getRangeMM();
        System.out.println("    Distance: " + mm + " mm");
      }

      // print roborio's 
      System.out.print(ROBORIO_U.getName());
      double mm = ROBORIO_U.getRangeMM();
      System.out.println("    Distance: " + mm + " mm");
    }
  }

}
