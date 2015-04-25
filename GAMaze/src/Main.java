import java.util.*;

public class Main {

	public static void main(String[] args) {
		int num_genes = 300;
		
		ArrayList<Double> genes = new ArrayList<Double>();
		for (int i = 0; i < num_genes; i++) {
			genes.add(Math.random());
		}
		
		Maze maze = new Maze(genes);
	}

}