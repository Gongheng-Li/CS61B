/** 
 * the class that perform the simulation of the planets.
 * @author Li Gongheng
 */
public class NBody{

	/** 
	 * read radius of the universe from the given file.
	 * @param file_name url of the file
	 */
	public static double readRadius(String file_name){
		In in = new In(file_name);
		int num_of_planets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	/** 
	 * read planetsin the universe from the given file.
	 * @param file_name url of the file
	 */
	public static Planet[] readPlanets(String file_name){
		In in = new In(file_name);
		int num_of_planets = in.readInt();
		double radius = in.readDouble();
		Planet[] planets_in_file = new Planet[num_of_planets];
		for(int i = 0; i < num_of_planets; i++){
			planets_in_file[i] = new Planet(0, 0, 0, 0, 0, "");
			planets_in_file[i].xxPos = in.readDouble();
			planets_in_file[i].yyPos = in.readDouble();
			planets_in_file[i].xxVel = in.readDouble();
			planets_in_file[i].yyVel = in.readDouble();
			planets_in_file[i].mass = in.readDouble();
			planets_in_file[i].imgFileName = in.readString();
		}
		return planets_in_file;
	}

	/**
	 * main function
	 * @param args[0] the total simulation time T
	 * @param args[1] time interval dt
	 * @param args[2] url of the file that contains information of planets
	 */
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		In in = new In(filename);
		int num_of_planets = in.readInt();
		double radius = readRadius(filename);
		Planet[] planets = new Planet[num_of_planets];
		planets = readPlanets(filename);
		double time;
		double[] xForces = new double[num_of_planets];
		double[] yForces = new double[num_of_planets];

		/* Draw the Background */
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.show();

		/* Draw all the planets */
		for(int i = 0; i < planets.length; i++){
			planets[i].draw();
		}
		
		StdDraw.enableDoubleBuffering();

		for(time = 0; time < T; time += dt){
			for(int i = 0; i < num_of_planets; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < num_of_planets; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			// StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i = 0; i < num_of_planets; i++){
				planets[i].draw();
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