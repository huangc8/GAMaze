import java.util.*;

public class Main {

	public static void main(String[] args) {
		int populationSize = 50;
		
		Population myPop = new Population(populationSize, true);
	

		for (int i = 0; i < 1000; i++) {
			myPop.getFittest().Print();
			
			int sum = 0;
			for (int j = 0; j < populationSize; j++) {
				sum += myPop.individuals[j].getFitness();
			}
			
			System.out.println("Generation: " + i + " Fittest: " + myPop.getFittest().getFitness() + " Average: " + sum / populationSize);
			myPop = Algorithm.evolvePopulation(myPop);
		}

	}

}