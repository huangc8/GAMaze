import java.util.*;

public class Main {

	public static void main(String[] args) {
		Population myPop = new Population(50, true);

		for (int i = 0; i < 1000; i++) {
			myPop.getFittest().Print();
			System.out.println("Generation: " + i + " Fittest: " + myPop.getFittest().getFitness());
			myPop = Algorithm.evolvePopulation(myPop);
		}

	}

}