package assign2;

import java.lang.Math;
import java.io.*;
import java.util.Vector;

public class RankTeams {
	public static void main(String[] args) throws IOException{
		
		
		FileReader fr = new FileReader(args[0]);
		BufferedReader reader = new BufferedReader(fr);
		
		
		String allTeams = reader.readLine();
		
		
		Vector<String> teams = new Vector<String>();
		
		
		int[] bestRanking = new int[40];
		
		
		for(int start = 0; start < allTeams.length(); start++){
			int end = allTeams.indexOf(" ", start);
			if(end != start){
				String team = allTeams.substring(start, end);
				teams.add(team);
			}
			start = end;
		}
		
		
		double[][] probabilities = new double[teams.size()][teams.size()];
		
		
		for(int i = 0; i < teams.size(); i++){
			String line = reader.readLine();
			int k = 0;
			for(int j = line.indexOf(" "); j < line.length(); j++){
				int end = line.indexOf(" ", j);
				if(end != j){
					String probability = line.substring(j, end);
					if(probability.startsWith("0")){
						probabilities[i][k] = Double.parseDouble(probability);
						k++;
					}
					
					else if(i == k){
						probabilities[i][k] = 1.0;
						k++;
					}
					
					else{
						probabilities[i][k] = 1 - probabilities[k][i];
						k++;
					}
					
				}
				j = end;
			}
		}
		
		
		reader.close();
		fr.close();
		
		
		bestRanking = genetic(probabilities);
		
		
		System.out.println("the ranking of the teams is:");
		for(int i = 0; i < 40; i++){
			System.out.println((i+1) + " "+ teams.elementAt(bestRanking[i]));
		}
	}
	
	public static double fitness(int[] ranking, double[][] prob){
		
		double probability = 0;
		for (int i = 0; i < ranking.length-1; i++){
			probability += Math.log(prob[ranking[i]][ranking[i+1]]+1);
		}
		return probability;
	}
	
	public static int[] genetic(double[][] prob){
		
		int[][] population = new int[40][10];
		
		int[] selected = new int[10];
		
		int[][] newPop = new int[40][10];
		
		int[][] children = new int[40][2];
		
		double totalFitness = 0;
		
		double bestTotalFitness = 0;
		
		int[] bestRanking = new int[40];
		
		
		for(int i = 0; i < 10; i++){
			population[0][i] = (int)(Math.random()*40);
			for(int j = 1; j < 40; j++){
				boolean unavailable = false;
				int idx = (int)(Math.random()*40);
				for(int k = 0; k < j; k++){
					if(population[k][i] == idx){
						unavailable = true;
						k = j;
					}
				}
				if(unavailable){
					j--;
				}
				else{
					population[j][i] = idx;
				}
			}
		}
		
		
		double bestFitness = 0;
		for(int i = 0; i < 10; i++){
			int[] ranking = new int[40];
			for(int j = 0; j < 40; j ++){
				ranking[j] = population[j][i];
			}
			
			double currentFitness = fitness(ranking, prob);
			totalFitness += currentFitness;
			if(currentFitness > bestFitness){
				
				bestFitness = currentFitness;
				bestRanking = ranking;
			}
		}
		
		bestTotalFitness = totalFitness;
		
		
		int count = 0;
		while(count<100){
			
			//run selection
			selected = selection(population, prob);
			
			
			for(int k = 0; k < 10; k++){
				for(int l = 0; l < 40; l++){
					newPop[l][k] = population[l][selected[k]];
				}
			}
			
		population = newPop;
		
		//run crossover for every pair of members
		for(int i = 0; i < 10; i += 2){
			int[] parent1 = new int[40];
			int[] parent2 = new int[40];
			for(int j = 0; j < 40; j++){
				parent1[j] = population[j][i];
				parent2[j] = population[j][i+1];
			}
			children = crossover(parent1, parent2);
			
			
			for(int k = 0; k < 40; k++){
				population[k][i] = children[k][0];
				population[k][i+1] = children[k][1];
			}
		}
		
		//run mutation on each member of the population
		for(int i = 0; i < 10; i++){
			int[] mutant = new int[40];
			for(int j = 0; j < 40; j++){
				mutant[j] = population[j][i];
			}
			mutant = mutation(mutant);
			for(int k = 0; k < 40; k++){
				population[k][i] = mutant[k];
			}
		}
		
		//find the fitness values for the new population
		double newTotalFitness = 0;
		double newBestFitness = 0;
		int[] newBestRanking = new int[40];
		for(int i = 0; i < 10; i++){
			int[] ranking = new int[40];
			for(int j = 0; j < 40; j ++){
				ranking[j] = population[j][i];
			}
			double currentFitness = fitness(ranking, prob);
			newTotalFitness += currentFitness;
			if(currentFitness > newBestFitness){
				newBestFitness = currentFitness;
				newBestRanking = ranking;
			}
		}
		
		
		if(newTotalFitness > bestTotalFitness){
		bestRanking = newBestRanking;
		bestTotalFitness = newTotalFitness;
		count = 0;
		}
		
		else{
			count++;
		}
		
		totalFitness = newTotalFitness;
		}
		return bestRanking;
	}
	
	public static int[] selection(int[][] pop, double[][] prob){
		double[] rouletteProb = new double[10];
		int[] rouletteVal = new int[10];
		int[] order = new int[40];
		int[] newPop = new int[10];
		double totalProb = 0;
		double totalNorm = 0;
		double[] accumNorm = new double[10];
		
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 40; j++){
				order[j] = pop[j][i];
			}
			double pro = fitness(order, prob);
			totalProb += pro;
			rouletteProb[i] = pro;
			rouletteVal[i] = i;
		}
		
		for(int k = 0; k < 10; k++){
			rouletteProb[k] = rouletteProb[k]/totalProb;
		}
		
		
		for(int l = 0; l < 10; l++){
			for(int m = l+1; m < 10; m++){
				if(rouletteProb[l] < rouletteProb[m]){
					double temp = rouletteProb[l];
					int temp1 = rouletteVal[l];
					rouletteProb[l] = rouletteProb[m];
					rouletteVal[l] = rouletteVal[m];
					rouletteProb[m] = temp;
					rouletteVal[m] = temp1;
				}
			}
		}
		
		for(int m = 0; m < 10; m++){
			totalNorm += rouletteProb[m];
			accumNorm[m] = totalNorm;
		}
		
		
		int n = 0;
		while(n < 10){
			double dart = Math.random()+ 0.1;
			for(int p = 0; p < 10; p++){
				if(dart < accumNorm[p]){
					newPop[n] = rouletteVal[p];
					p = 10;
				}
			}
			n++;
		}
		
		return newPop;
	}
	
	public static int[] mutation(int[] mutant){
		double mutProb = Math.random();
		int[] mutated = mutant;
		
		
		if(mutProb < 0.1){
		
			int a = (int)(Math.random() * 40);
			int b = (int)(Math.random() * 40);
			while(b == a){
				b = (int)(Math.random() * 40);
		}
		
		int temp = mutated[a];
		mutated[a] = mutated[b];
		mutated[b] = temp;
		
		return mutated;
		}
		else{
			return mutated;
		}
	}
	
	public static int[][] crossover(int[] parent1, int[] parent2){
		int[][] children = new int[40][2];
		
		
		double crossOrNot = Math.random()+0.1;
		if(crossOrNot > .5){
			
			for(int i = 0; i < 40; i++){
				children[i][0] = parent1[i];
				children[i][1] = parent2[i];
			}
			return children;
		}
		else{
			
			int cutoff = (int)(Math.random()*20);
			for(int i = cutoff; i < cutoff+20; i++){
				
				children[i][0] = parent1[i];
				children[i][1] = parent2[i];
			}
			
			
			int j = cutoff + 20;
			int k = cutoff + 20;
			while(j >= cutoff + 20 || j < cutoff){
				int next = parent2[k];
				
				boolean used = false;
				for(int l = cutoff; l < cutoff + 20; l++){
					if(parent1[l] == next){
						used = true;
						l = cutoff + 20;
					}
				}
				if(!used){
					
					children[j][0] = next;
					k++;
					if(k == 40){
						k = 0;
					}
					j++;
					if(j == 40){
						j = 0;	
					}
				}
				else{
					
					k++;
					if(k == 40){
						k = 0;
					}
				}
			}
			
			
			j = cutoff + 20;
			k = cutoff + 20;
			while(j >= cutoff + 20 || j < cutoff){
				int next = parent1[k];
				
				boolean used = false;
				for(int l = cutoff; l < cutoff + 20; l++){
					if(parent2[l] == next){
						used = true;
						l = cutoff + 20;
					}
				}
				if(!used){
					
					children[j][1] = next;
					k++;
					if(k == 40){
						k = 0;
					}
					j++;
					if(j == 40){
					j = 0;	
					}
				}
				else{
					
					k++;
					if(k == 40){
						k = 0;
					}
				}
			}
		}
		return children;
	}
}
