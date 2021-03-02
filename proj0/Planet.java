public class Planet{
    private static final double G = 6.67e-11;

    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double r, dx, dy;
        dx = this.xxPos - p.xxPos;
        dy = this.yyPos - p.yyPos;
        r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy(Planet p){
        double r;
        r = this.calcDistance(p);
        return G * this.mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p){
        double force, force_X, dx, r;
        dx = p.xxPos - this.xxPos;
        r = this.calcDistance(p);
        force = this.calcForceExertedBy(p);
        force_X = force * dx / r;
        return force_X;
    }


    public double calcForceExertedByY(Planet p){
        double force, force_Y, dy, r;
        dy = p.yyPos - this.yyPos;
        r = this.calcDistance(p);
        force = this.calcForceExertedBy(p);
        force_Y = force * dy / r;
        return force_Y;
    }

    public double calcNetForceExertedByX(Planet[] p_array){
        double total_force_x=0;
        for(Planet p: p_array){
            if(!this.equals(p))
                total_force_x += this.calcForceExertedByX(p);
        }
        return total_force_x;
    }

    public double calcNetForceExertedByY(Planet[] p_array){
        double total_force_y=0;
        for(Planet p: p_array){
            if(!this.equals(p))
                total_force_y += this.calcForceExertedByY(p);
        }
        return total_force_y;
    }

    public void update(double dt, double fX, double fY){
        double aX, aY;
        aX = fX / this.mass;
        aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();
    }
}