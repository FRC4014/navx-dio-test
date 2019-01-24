package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Robot extends TimedRobot {

  // navxDioPin method inspired by: https://pdocs.kauailabs.com/navx-mxp/examples/mxp-io-expansion/
  private static int ROBORIO_DIO_COUNT = 10;
  private static int navxDioPin(int n) {
    return ROBORIO_DIO_COUNT + n + (n > 3 ? 4 : 0);
  }

  private static int TRIG = navxDioPin(2);
  private static int ECHO = navxDioPin(3);
  private static Ultrasonic U = new Ultrasonic(TRIG, ECHO);

  @Override
  public void testInit() {
    U.setName("Trig: " + TRIG + "   Echo: " + ECHO);
    U.setAutomaticMode(true);
  }

  int testPeriodicCalls = 0;
  @Override
  public void testPeriodic() {
    if (testPeriodicCalls++ % 100 == 0) {
      System.out.println("---");
      log(U);
    }
  }

  private void log(Ultrasonic u) {
    System.out.print(u.getName());
    double mm = u.getRangeMM();
    System.out.println("    Distance: " + mm + " mm");
  }

}
