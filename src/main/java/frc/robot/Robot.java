package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Robot extends TimedRobot {

  // navxDioPin method inspired by: https://pdocs.kauailabs.com/navx-mxp/examples/mxp-io-expansion/
  private static int ROBORIO_DIO_COUNT = 10;
  private static int navxDioPin(int n) {
    return ROBORIO_DIO_COUNT + n + (n > 3 ? 4 : 0);
  }

  // setup RoboRIO's
  private static int ROBORIO_U_TRIG = 8;
  private static int ROBORIO_U_SIG = 9;
  private static Ultrasonic ROBORIO_U = new Ultrasonic(ROBORIO_U_TRIG, ROBORIO_U_SIG);

  // Setup 5 Ultrasonics to cover all the NavX Digital IO pins
  public static Ultrasonic[] US = new Ultrasonic[5];

  static {
    ROBORIO_U.setName("RoboRIO trig:" + ROBORIO_U_TRIG + "    sig:" + ROBORIO_U_SIG + " ");
    System.out.println("RoboRIO's is Ultrasonic(" + ROBORIO_U_TRIG + ", " + ROBORIO_U_SIG + ")");

    for (int i = 0; i < US.length; i++) {
      int t = i * 2;
      int s = t + 1;
      int nt = navxDioPin(t);
      int ns = navxDioPin(s);
      US[i] = new Ultrasonic(nt, ns);
      US[i].setName("NavX    trig:" + nt + "   sig:" + ns);
      System.out.println("US[" + i + "] is for NavX: Ultrasonic(" + nt + ", " + ns + ")" +
                         "   Pin numbers on NavX: " + t + " and " + s);
    }
  }

  @Override
  public void testInit() {
    ROBORIO_U.setAutomaticMode(true);

    for (int i = 0; i < US.length; i++) {
      US[i].setAutomaticMode(true);
    }
  }

  int testPeriodicCalls = 0;
  @Override
  public void testPeriodic() {
    if (testPeriodicCalls++ % 100 == 0) {
      System.out.println("---");

      log(ROBORIO_U);

      for (int i = 0; i < US.length; i++) {
        log(US[i]);
      }
    }
  }

  private void log(Ultrasonic u) {
    System.out.print(u.getName());
    double mm = u.getRangeMM();
    System.out.println("    Distance: " + mm + " mm");
  }

}
