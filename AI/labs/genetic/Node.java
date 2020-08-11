package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	int n;
	List<Integer> state;
	int fitness;

	public Node(int n) {
		this.n = n;
		this.fitness = 0;
		this.state = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Random r = new Random();
			this.state.add(r.nextInt(n));
		}
	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = state;
		this.fitness = 0;
	}
}
