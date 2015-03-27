package assign2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;


public class simAnnealing {
	
	//static List<Double> randomNum = new ArrayList<Double>();
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
		List<Double> randomNum1 = new ArrayList<Double>();
		Random rand = new Random();
	
		for (int i = 0; i < d; i++) 
		{
			w = (rand.nextDouble() * (e - q)) + q;
			randomNum1.add(w);
			//System.out.println(randomNum.get(i));
		}
		return(randomNum1);
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

	public static double annealing(int d) 
	{
		//randomNum.clear();
		 List<Double> randomNum = new ArrayList<Double>();
		double T = 10.0;
		Random rand = new Random();
		List<Double> list1 = new ArrayList<Double>();
		List<Double> list2 = new ArrayList<Double>();
		double curmin, potmin,zz,deltaE;
		randomNum = init(d);
		curmin = rastrigin(randomNum,d);
		
		int i = 0;
		while (i < 100) 
		{
			list1 = step(randomNum);
			potmin = rastrigin(list1,d);
			
			while(T > 1){
				
			for(int j=0;j<100;j++)
			{
				list2 = step(list1);
				zz = rastrigin(list2,d);
				deltaE = potmin - zz;
				if (deltaE > 0)
				{
					potmin = zz;
					list1 = list2;
				}
				else
				{
					if(Math.exp((-deltaE)/T) > rand.nextDouble()){
						potmin = zz;
						list1 = list2;
					}
				}
			}
			//T = T - (T*0.1);
			T = T * 0.99;
			}
			if (potmin < curmin) 
			{
				
				curmin = potmin;
				randomNum = list1;
				i = 0;
			} 
			else
				i++;	
			
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
			result[i] = annealing(num);
			System.out.println(result[i]);
		}
		JFrame f = new JFrame("Simulated Annealing");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Plot(result));
        f.setSize(680, 680);
        f.setLocation(0,0);
        f.setVisible(true);
	}
}
