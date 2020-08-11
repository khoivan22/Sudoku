package labs3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	int n;
	List<Integer> state;

	public Node(int n) {
		this.n = n;
		this.state = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Random r = new Random();
			this.state.add(r.nextInt(n));
		}
//		this.state.add(3);
//		this.state.add(2);
//		this.state.add(2);
//		this.state.add(1);
	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = state;
	}
}
