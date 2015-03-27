package assign2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;


public class DEvolution {
	
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

	public static double evolution(int d) 
	{
		 List<Double> y = new ArrayList<Double>();
		 List<Double> s = new ArrayList<Double>();
		
		 List<Double> list2 = new ArrayList<Double>();
		 List<List<Double>> p = new ArrayList<List<Double>>();
		 List<List<Double>> p0 ;
		 List<Double> X = new ArrayList<Double>();
		double C = 0.5;
		double F = 0.8;
		Random rand = new Random();
		List<Double> list1 = new ArrayList<Double>();
		
		
		double curmin, potmin,zz,best,t,u,w,f,l,s1,s2=0,s3=0,s7,d1,d2,d4,d5,d7,d10,cmin;
		int popsize = 10*d;
		int min = 0;
		int max = popsize - 1;
		int r0,r1,r2;
		
		
		for(int i=0;i<popsize;i++)
		{
			list2 = init(d);
			p.add(list2);
			curmin = rastrigin(list2,d);
			y.add(curmin);
		}
		Collections.sort(y);
		best = y.get(0);
		
		int i = 0;
		while (i < 100) 
		{
			
			p0 = new ArrayList<List<Double>>(p);
			/*for(int r1=0;r1<p.size();r1++)
			{
				p0.get(p.clone);
			}*/
			y.clear();
			for(int j=0;j<popsize;j++)
			{
				
				r0 = rand.nextInt((max - min) + 1) + min;
				
				while(r0 == j)
				{
					r0 = rand.nextInt((max - min) + 1) + min;
				}
				r1 = rand.nextInt((max - min) + 1) + min;
				while(r1 == j || r1 == r0)
				{
					r1 = rand.nextInt((max - min) + 1) + min;
				}
				r2 = rand.nextInt((max - min) + 1) + min;
				while(r2 == j || r2 == r1 || r2 == r0)
				{
					r2 = rand.nextInt((max - min) + 1) + min;
				}
				
				List<Double> s4 = new ArrayList<Double>();
				for(int z=0;z<p0.get(r0).size();z++)
				{
					s7=(p0.get(r0)).get(z);
					s4.add(s7);
				}
				List<Double> a = new ArrayList<Double>();
				for(int z=0;z<p0.get(r1).size();z++)
				{
					s7=(p0.get(r1)).get(z);
					a.add(s7);
				}
				List<Double> b = new ArrayList<Double>();
				for(int z=0;z<p0.get(r2).size();z++)
				{
					s7=(p0.get(r2)).get(z);
					b.add(s7);
				}
				List<Double> c = new ArrayList<Double>();
				for(int q=0;q<a.size();q++)
				{
					d1=a.get(q)-b.get(q);
					c.add(d1);
				}
				List<Double> d3 = new ArrayList<Double>();
				for(int r=0;r<a.size();r++)
				{
					d2=c.get(r)*F;
					d3.add(d2);
				}
				List<Double> V = new ArrayList<Double>();
				for(int s9=0;s9<a.size();s9++)
				{
					d4=s4.get(s9)+d3.get(s9);
					V.add(d4);
				}
				//System.out.println(V.size());
				//CollectionUtils.substract(java.util.Collection a, java.util.Collection b);
				for(int z=0;z<V.size();z++)
				{
					if(V.get(z)>5.12)
					{
						V.remove(z);
						V.add(z, 5.12);
					}
					else
					{
						if(V.get(z)<-5.12)
						{
							V.remove(z);
							V.add(z, -5.12);
						}
					}
				}
				List<Double> U = new ArrayList<Double>();
				for(int o=0;o<d;o++)
				{
					f=rand.nextDouble();
					if(f<C)
					{
						l=V.get(o);
						//U.remove(o);
						U.add(o,l);
					}
					else
					{
						l=p0.get(j).get(o);
						//U.remove(o);
						U.add(o,l);
					}
				}
				List<Double> d6 = new ArrayList<Double>();
				for(int z1=0;z1<p0.get(j).size();z1++)
				{
					d5=p0.get(j).get(z1);
					d6.add(d5);
				}
				int z=V.size()-1;
				if(rastrigin(U,d)<rastrigin(d6,d))
				{  
					p.remove(z);
					p.add(z,U);
				}
				else
				{
					p.remove(z);
					p.add(z,d6);
				}
				d10=rastrigin(p.get(z),d);
				y.add(d10);
			}
		
			Collections.sort(y);
			cmin= y.get(0);
			if (cmin < best) 
			{
				
				best = cmin;
				
				i = 0;
			} 
			else
				i++;	
			
		}
		return best;
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
			result[i] = evolution(num);
			System.out.println(result[i]);
		}
		JFrame f = new JFrame("Differential Evolution");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Plot(result));
        f.setSize(680, 680);
        f.setLocation(0,0);
        f.setVisible(true);
	}
}
