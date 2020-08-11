package sudoku;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Genetic {
	ArrayList<Node> population;// quan the
	ArrayList<Node> population0;// quan the 0
	ArrayList<Node> fathers;// ca the cha
	ArrayList<Node> mothers;// ca the me

	//	ArrayList<JLabel> listLB;
	int map[][];

	int fitness = 60;// muc chon loc
	final int amountPersons = 500;// so ca the trong quan the
	Random rd;

	public Genetic(int[][] arr) {
		map = arr;
//		listLB = new ArrayList<>();
//		for (int i = 0; i < 81; i++) {
//			listLB.add(new JLabel());
//		}

		rd = new Random();
		this.population = new ArrayList<>();
		this.population0 = new ArrayList<>();
		this.fathers = new ArrayList<>();
		this.mothers = new ArrayList<>();
		this.createPopulation();
	}

	// tao quan the
	public void createPopulation() {
		for (int i = 0; i < amountPersons; i++) {
			Node NodeTest = new Node(map);
			if (NodeTest.isGoal()) {
				printNodeTest(NodeTest);
				break;
			}
			population.add(NodeTest);
		}
	}

	// chon ca the cha tu quan the( chon ngau nhien)
	public void selectFather() {
		for (int i = 0; i < amountPersons / 2; i++) {
			fathers.add(population.get(i));
		}
	}

	// chon ca the me tu quan the(chon ngau nhien)
	public void selectModer() {
		for (int i = amountPersons / 2; i < amountPersons; i++) {
			mothers.add(population.get(i));
		}
	}

	// chon loc ca the tu quan the
	public boolean selective(ArrayList<JLabel> listLB) {
		// chon lai trong quan the neu khong dat yeu cau thi xoa di
		for (int i = 0; i < population.size(); i++) {

			if (population.get(i).heuristic() > fitness)
				population.remove(population.get(i));
		}
		// Nếu quần thể vượt quá person thì xóa đi
		while (amountPersons < population.size()) {
			population.remove(0);
		}
		// neu quan the it hon amountPersons thi chon ngau nhien trong quan the, dot bie
		// roi them vao quan the
		int count = 0;

		while (0 < amountPersons - population.size()) {
			Node n = new Node(map);
			// neu dot bien tat ca ca the thi lai

			if (count > population.size() - 1) {
				int x = rd.nextInt(population.size());
				int y = rd.nextInt(population.size());

				n = mutation(crossOver3(population.get(x), population.get(y)));

				if (n.heuristic() > fitness) {
					n = mutation(crossOver1(population.get(x), population.get(y)));
				}
				if (n.heuristic() > fitness) {
					n = mutation(crossOver2(population.get(x), population.get(y)));
				}
				if (n.heuristic() > fitness) {
					n = mutation(crossOver(population.get(x), population.get(y)));
				}
				if (n.heuristic() > fitness) {
					n = mutation(crossOver4(population.get(x), population.get(y)));
				}
			}
			if (n.heuristic() == 0) {
				System.out.println("is goal: ");
				printNodeTest(n);
				setNodeLabel(n.state, listLB);
				return true;
			}
			if (n.heuristic() < fitness) {
				population.add(n);
				System.out.println("Cá thể thêm vào: " + population.size());
			}
			count++;
		}
		return false;
	}

	// dot bien ca the
	public Node mutation(Node NodeTest) {
		int count = 0;
		while (count < 50) {
			String[] s = NodeTest.find1().split("");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			if (NodeTest.checkIndex(x, y) == 0)
				break;
			count++;
			NodeTest.state[x][y] = NodeTest.rand(x, y);
		}
		return NodeTest;
	}

	// lay 3 hang cua cha 3 hang cua me va 3 hang cua cha

	public Node crossOver(Node father, Node mother) {
		Node child = new Node(map);
		for (int i = 0; i < 9; i++) {

			for (int j = i; j < i + 3; j++) {

				for (int j2 = 0; j2 < 9; j2++) {

					child.state[j][j2] = father.state[j][j2];
				}
			}
			i = i + 3;
			for (int j = i; j < i++; j++) {
				for (int j2 = i; j2 < i + 1; j2++) {

					child.state[j][j2] = mother.state[j][j2];
				}
			}
		}
		return child;
	}

	// lay 3 cot cua cha 3 cot cua me va 3 cot cua cha

	public Node crossOver1(Node father, Node mother) {
		Node child = new Node(map);
		for (int i = 0; i < 9; i++) {
			for (int j = i; j < i + 3; j++) {
				for (int j2 = 0; j2 < 9; j2++) {
					child.state[j2][j] = father.state[j2][j];
				}
			}
			i = i + 3;
			for (int j = i; j < i++; j++) {
				for (int j2 = i; j2 < i + 1; j2++) {
					child.state[j2][j] = mother.state[j2][j];
				}
			}
		}
		return child;
	}

	// lay 1 hang cua cha 1 hang cua me
	// ok

	public Node crossOver2(Node father, Node mother) {
		Node child = new Node(map);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				child.state[j][i] = father.state[j][i];
				j = j + 1;
				if (j < 9)
					child.state[j][i] = mother.state[j][i];
			}
		}
		return child;
	}

	// lay 1 cot cua cha va 1 cot cua me
	// ok
	public Node crossOver3(Node father, Node mother) {

		Node child = new Node(map);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				child.state[i][j] = father.state[i][j];
				j = j + 1;
				if (j < 9)
					child.state[i][j] = mother.state[i][j];
			}

		}

		return child;
	}

	// ngau nhien

	public Node crossOver4(Node father, Node mother) {
		Node child = new Node(map);
		int x = rd.nextInt(6);
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				if (x < 3) {
					child.state[i][j] = father.state[i][j];
					x++;
				}
				if (x < 6) {
					child.state[i][j] = mother.state[i][j];
					x++;
				}
				if (x == 6) {
					x = rd.nextInt(6);
				}
			}

		}

		return child;
	}

	public void printNodeTest(Node NodeTest) {
		for (int i = 0; i < NodeTest.state.length; i++) {
			System.out.println();
			for (int j = 0; j < NodeTest.state.length; j++) {
				System.out.print(NodeTest.state[i][j] + "-");
			}
		}
	}

	public void setNodeLabel(int[][] map, ArrayList<JLabel> listLB) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				arr.add(map[i][j]);
			}
		}
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) != 0)
				listLB.get(i).setText(arr.get(i) + "");
		}
	}

	public boolean run(ArrayList<JLabel> listLB) {

		for (int i = 0; i < 60; i++) {
			// kiem tra goal
			for (Node n : population) {
				if (n.heuristic() == 0) {
					System.out.println("is goal: ");
					setNodeLabel(n.state, listLB);
					printNodeTest(n);
					return true;
				}
			}
			// chon quan the cha me
			selectFather();
			selectModer();

			// lai quan the cha voi quan the me
			for (int j = 0; j < fathers.size(); j++) {

				Node child = crossOver3(fathers.get(j), mothers.get(j));
				// new con sinh ra khong dat yeu cau thi dot bien
				if (child.heuristic() > fitness)
					child = mutation(child);
				if (child.heuristic() == 0) {
					System.out.println("is goal: ");
					setNodeLabel(child.state, listLB);
					printNodeTest(child);
					return true;
				}
				setNodeLabel(child.state, listLB);
				// them vao quan the
				if (child.heuristic() < fitness) {
					population.add(child);
				}
			}
			// xoa quan the cha me de chon quan the moi
			fathers.clear();
			mothers.clear();
			System.out.println("số cá thể sau khi lai: " + population.size());

//			// chon loc
			if (selective(listLB))
				return true;

			System.out.println("số cá thể sau chọn lọc: " + population.size());
			System.out.println("số thế hệ: " + i);
			System.out.println();
			// giam finess sau moi the he
			if (fitness < 3) {
				population.removeAll(population);
				createPopulation();
				fitness = 60;
				i = 0;
			}
			fitness--;
		}
		return false;
//		sort(population);
//		population.get(0).printNodeTest();
//		System.out.println();
//		System.out.println("Heuristic: " + population.get(0).heuristic());
	}


	/*public static void main(String[] args) {
		long startTime = System.nanoTime();

		Genetic g = new Genetic();
		g.run();

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);

		System.out.println();
		System.out.println(" run time :" + (double) duration / 1_000_000_000L + "s");
	}*/
}
