public class NBody {
  static public double readRadius(String filename) {
    In in = new In(filename);
    in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  static public Planet[] readPlanets(String filename) {
    In in = new In(filename);
    int planetNum = in.readInt();
    in.readDouble();

    Planet[] planets = new Planet[planetNum];
    for (int i = 0; i < planetNum; i++) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVol = in.readDouble();
      double yyVol = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVol, yyVol, mass, imgFileName);
    }

    return planets;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);

    StdDraw.setScale(-radius, radius);
    StdDraw.clear();

    StdDraw.enableDoubleBuffering();

    int length = planets.length;
    for (double time = 0; time < T; time += dt) {
      double[] xForces = new double[length];
      double[] yForces = new double[length];
      for (int i = 0; i < length; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }

      for (int i = 0; i < length; i++) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }

      StdDraw.picture(0, 0, "images/starfield.jpg");

      for (Planet p : planets) {
        p.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
