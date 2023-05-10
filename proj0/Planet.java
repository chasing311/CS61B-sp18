public class Planet {
  // current x position
  public double xxPos;
  // current Y position
  public double yyPos;
  // current velocity in the x direction
  public double xxVel;
  // current velocity in the y direction
  public double yyVel;
  // mass
  public double mass;
  public String imgFileName;

  private static final double GRAVITATION = 6.67e-11;

  public Planet(double xP, double yP, double xV,
      double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double xDistance = Math.abs(p.xxPos - xxPos);
    double yDistance = Math.abs(p.yyPos - yyPos);
    return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
  }

  public double calcForceExertedBy(Planet p) {
    double distance = calcDistance(p);
    return GRAVITATION * mass * p.mass / distance / distance;
  }

  public double calcForceExertedByX(Planet p) {
    double xDistance = p.xxPos - xxPos;
    double distance = calcDistance(p);
    double force = calcForceExertedBy(p);
    return xDistance * force / distance;
  }

  public double calcForceExertedByY(Planet p) {
    double yDistance = p.yyPos - yyPos;
    double distance = calcDistance(p);
    double force = calcForceExertedBy(p);
    return yDistance * force / distance;
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    double netForce = 0;
    for (Planet planet : planets) {
      if (!this.equals(planet)) {
        netForce += calcForceExertedByX(planet);
      }
    }
    return netForce;
  }

  public double calcNetForceExertedByY(Planet[] planets) {
    double netForce = 0;
    for (Planet planet : planets) {
      if (!this.equals(planet)) {
        netForce += calcForceExertedByY(planet);
      }
    }
    return netForce;
  }

  public void update(double dt, double xForce, double yForce) {
    double xAccelecation = xForce / mass;
    double yAccelectation = yForce / mass;
    xxVel += xAccelecation * dt;
    yyVel += yAccelectation * dt;
    xxPos += xxVel * dt;
    yyPos += yyVel * dt;
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
