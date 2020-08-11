package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
	List<Node> population = new ArrayList<>();
	final int n = 1000;

	// tạo quần thể ngẫu nhiên
	public void randomPopulation(int nQueen) {
		for (int i = 0; i < n; i++) {
			Node node = new Node(nQueen);
			node.fitness = getFitness(node);
			population.add(node);
		}
	}

	public Node selectRandomNode() {
		return this.population.get(new Random().nextInt(n - 1));
	}

	public void reproduction() {
		Node goal = null;

		while (true) {
			List<Node> new_population = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				System.out.println("→ " + i);
				Node x = selectRandomNode();
				Node y = selectRandomNode();
				if (x.fitness == 28) {
					goal = x;
					System.out.println("X : " + goal.state + " : " + goal.fitness);
					break;
				}
				if (y.fitness == 28) {
					goal = y;
					System.out.println("X : " + goal.state + " : " + goal.fitness);
					break;
				}


				List<Integer> state1 = new ArrayList<>();
				List<Integer> state2 = new ArrayList<>();

				state1.addAll(x.state.subList(0, 3));
				state1.addAll(y.state.subList(3, 8));
				state2.addAll(y.state.subList(0, 3));
				state2.addAll(x.state.subList(3, 8));

				Node node1 = new Node(x.n, state1);
				Node node2 = new Node(x.n, state2);

				node1.fitness = getFitness(node1);
				if (node1.fitness == 28) {
					goal = node1;
					System.out.println(goal.state + " : " + goal.fitness);
					break;
				}
				node2.fitness = getFitness(node2);
				if (node2.fitness == 28) {
					goal = node2;
					System.out.println(goal.state + " : " + goal.fitness);
					break;
				}

				System.out.println(node1.state + " : " + node1.fitness);
				System.out.println(node2.state + " : " + node2.fitness);

				mutation(node1);
				if (node1.fitness == 28) {
					goal = node1;
					System.out.println(goal.state + " : " + goal.fitness);
					break;
				}
				mutation(node2);
				if (node2.fitness == 28) {
					goal = node2;
					System.out.println(goal.state + " : " + goal.fitness);
					break;
				}

				new_population.add(new Node(x.n, state1));
				new_population.add(new Node(x.n, state2));
			}
			if (goal != null)
				break;
			population = new_population;
		}
	}


	public void mutation(Node node) {
		node.state.set(new Random().nextInt(node.n - 1), new Random().nextInt(node.n - 1));
	}

	//trả về tổng xung đột ngang trong một node
	private int checkHorizontal(Node node) {
		int result = 0;
		for (int i = 0; i < node.state.size(); i++) {
			for (int j = i + 1; j < node.state.size(); j++) {
				if (node.state.get(i).equals(node.state.get(j))) {
					result++;
				}
			}
		}
		return result;
	}

	//trả về tổng xung đột chéo trong một node
	private int checkDiagonal(Node node) {
		int result = 0;
		for (int i = 0; i < node.state.size() - 1; i++) {
			for (int j = i + 1; j < node.state.size(); j++) {
				// hàng - hàng == cột - cột
				if (Math.abs(node.state.get(i) - node.state.get(j)) == Math.abs(i - j)) {
					result++;
				}
			}
		}
		return result;
	}

	private int getFitness(Node node) {
		return (node.n * (node.n - 1) / 2) - (checkHorizontal(node) + checkDiagonal(node));
	}

	public static void main(String[] args) {
		GeneticAlgorithm gen = new GeneticAlgorithm();
		gen.randomPopulation(8);
		gen.reproduction();
	}
}
