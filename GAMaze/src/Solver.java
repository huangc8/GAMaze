public class Solver {

	char[][] maze;
	boolean[][] visited;
	char[][] solution;
	boolean done;
	int iter;
	int rowSize = 0, colSize = 0, row = 0, col = 0;
	int branches = 0;

	// constructor
	public Solver() {
		reset();
	}

	// start the recursion solve
	public float startSolve(char[][] maz) {
		reset();
		maze = maz;
		solution = maz.clone();
		rowSize = maz.length;
		colSize = maz[0].length;
		visited = new boolean[rowSize][colSize];

		findStart();
		solve(row, col);
		computeFitness();
		branches -= 2; // remove S and E
		// printSolution();
		return branches;
	}

	// reset
	private void reset() {
		maze = null;
		visited = null;
		solution = null;
		done = false;
		iter = 0;
		rowSize = 0;
		colSize = 0;
		branches = 0;
	}

	// print the solution
	private void printSolution() {
		for (int r = 0; r < rowSize; r++) {
			for (int c = 0; c < colSize; c++) {
				System.out.print(solution[r][c]);
			}
			System.out.println();
		}
	}

	// find the starting point
	private void findStart() {
		for (int r = 0; r < rowSize; r++) {
			for (int c = 0; c < colSize; c++) {
				if (maze[r][c] == 'S') {
					row = r;
					col = c;
				}
			}
		}
	}

	// check if valid move
	private boolean moveCheck(int x, int y) {
		if (x >= 0 && x < rowSize && y >= 0 && y < colSize) {
			if (maze[x][y] != '*' && maze[x][y] != 'X') {
				return true;
			}
		}
		return false;
	}

	private void computeFitness() {
		for (int r = 0; r < rowSize; r++) {
			for (int c = 0; c < colSize; c++) {
				if (solution[r][c] == 'X') {
					// check all direction
					// up
					if (moveCheck(r, c + 1)) {
						branches++;
					}
					// right
					if (moveCheck(r + 1, c)) {
						branches++;
					}
					// down
					if (moveCheck(r, c - 1)) {
						branches++;
					}
					// left
					if (moveCheck(r - 1, c)) {
						branches++;
					}
				}
			}
		}
	}

	// recursion solve
	private void solve(int x, int y) {

		// end conditions
		if (done || visited[x][y]) {
			return;
		}

		// update visited
		visited[x][y] = true;

		// reached the goal
		if (maze[x][y] == 'E') {
			done = true;
		}

		/*
		 * if (solution[x][y] != 'S' && solution[x][y] != 'E') { solution[x][y]
		 * = '#'; }
		 */

		// expand in all direction
		// up
		if (moveCheck(x, y + 1)) {
			solve(x, y + 1);
		}
		// right
		if (moveCheck(x + 1, y)) {
			solve(x + 1, y);
		}
		// down
		if (moveCheck(x, y - 1)) {
			solve(x, y - 1);
		}
		// left
		if (moveCheck(x - 1, y)) {
			solve(x - 1, y);
		}

		// mark solution
		if (done) {
			if (solution[x][y] != 'S' && solution[x][y] != 'E') {
				solution[x][y] = 'X';
			}
		}
	}
}
