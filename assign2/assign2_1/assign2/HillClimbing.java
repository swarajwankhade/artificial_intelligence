package assign2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class HillClimbing {
	
	static List<Double> randomNum = new ArrayList<Double>();
	//static List<Double> randomNum1 = new ArrayList<Double>();
	
	public static double rastrigin(List<Double> e,int n) 
	{
		int a = 10;
		int y = e.size();
		/*for (int i = 0; i < 10; i++) {
			System.out.println("from rast" + randomNum[i]);
		}*/
		double sum = 0;

		for (int i = 0; i < y; i++) 
		{
			double s = 2 * 3.14 * e.get(i);
			double j = ((e.get(i) * e.get(i)) - (a * Math.cos(s)));
			sum = sum + j;
		}
		double z = (a * n) + sum;
		return z;
	}

	// Random Number Generation
	public static List<Double> init(int d) 
	{
		double q = -5.12;
		double e = 5.12;
		double w;
		
		Random rand = new Random();
	
		for (int i = 0; i < d; i++) 
		{
			w = (rand.nextDouble() * (e - q)) + q;
			randomNum.add(w);
			//System.out.println(randomNum.get(i));
		}
		return(randomNum);
	}

	
	 
	public static List<Double> step(List<Double> e) 
	{
		int y = e.size();
		double w;
		
		List<Double> list = new ArrayList<Double>();
		Random rand = new Random();
		for (int i = 0; i < y; i++) 
		{
			w = (rand.nextDouble() - 0.5) * 0.1 + e.get(i);
			while (w < -5.12 || w > 5.12) 
			{
				w = (rand.nextDouble() - 0.5) * 0.1 + e.get(i);
			}
			
			list.add(w);
			/*for (int j = 0; j < z.length; j++) {
				System.out.println(z[i]);
			}*/
		}
		return(list);
	}

	public static double hillclimb(int d) 
	{
		randomNum.clear();
		List<Double> list1 = new ArrayList<Double>();
		double curmin, potmin;
		randomNum = init(d);
		curmin = rastrigin(randomNum,d);
		
		int i = 0;
		while (i < 100) 
		{
			list1 = step(randomNum);
			
			potmin = rastrigin(list1,d);
			if (potmin < curmin) 
			{
				
				curmin = potmin;
				randomNum = list1;
				i = 0;
			} 
			else
				i++;

			//r = step(q);
		}
		return curmin;
	}

	public static void main(String args[]) 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of dimentions");
		int num = in.nextInt();
		in.close();
		double[] result = new double[150];
		for (int i = 0; i < 100 ; i++) 
		{
			// System.out.println("in for");
			result[i] = hillclimb(num);
			System.out.println(result[i]);
		}
		JFrame f = new JFrame("Hill Climbing");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Plot(result));
        f.setSize(680, 680);
        f.setLocation(0,0);
        f.setVisible(true);
	}
}
