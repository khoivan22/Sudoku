package Sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Genetic {
	ArrayList<Node> population;// quan the
	ArrayList<Node> population0;// quan the 0
	ArrayList<Node> fathers;// ca the cha
	ArrayList<Node> mothers;// ca the me
	ArrayList<Node> childs;// ca the con sinh ra tu cha va me

	int finess = 60;// muc chon loc
	final int amountPersons =1500;// so ca the trong quan the

	Random rd;
	Node nn = new Node();

	public Genetic() {
		rd = new Random();
		this.population = new ArrayList<Node>();
		this.population0 = new ArrayList<Node>();
		this.fathers = new ArrayList<Node>();
		this.mothers = new ArrayList<Node>();
//		this.parents=new ArrayList<Node>();
//		this.mothers=new ArrayList<Node>();
//		this.childs=new ArrayList<Node>();
		this.createPopulation();

	}

	// tao quan the
	public void createPopulation() {
		for (int i = 0; i < amountPersons; i++) {
			Node node = new Node();
			if (node.isGoal()) {
				printNode(node);
				break;
			}
			population.add(node);
		}
	}

	public void createPopulation0() {
		for (int i = 0; i < amountPersons; i++) {
			Node node = new Node();
			if (node.isGoal()) {
				printNode(node);
				break;
			}
			population0.add(node);
		}
	}

	public void createNew() {
		this.createPopulation0();
		@SuppressWarnings("unchecked")
		ArrayList<Node> news = (ArrayList<Node>) population.clone();
//		population.clear();

		
		for (int i = news.size() / 4; i < population.size(); i++) {
			if(population.size()>population0.size()) break;
			population.set(i,population0.get(i));
		}
	}

//chon ca the cha tu quan the( chon ngau nhien)
	public void selectFather() {
		for (int i = 0; i < amountPersons / 2; i++) {
			fathers.add(population.get(i));
		}
	}

//chon ca the me tu quan the(chon ngau nhien)
	public void selectModer() {
		for (int i = amountPersons / 2; i < amountPersons; i++) {
			mothers.add(population.get(i));
		}
	}

// chon loc ca the tu quan the
	public boolean selective() {
		// chon lai trong quan the neu khong dat yeu cau thi xoa di
		for (int o = 0; o < population.size(); o++) {

			if (population.get(o).heuristic() > finess)
				population.remove(population.get(o));
		}
		// neu quan the vuot qua amountPersons thi lay amountPersonsca the con lai xoa
		// di
		while (amountPersons < population.size()) {
			population.remove(0);
		}
		// neu quan the it hon amountPersons thi chon ngau nhien trong quan the, dot bie
		// roi them vao quan the
		int count = 0;

		while (0 < amountPersons - population.size()) {
			Node n = null;
			// neu dot bien tat ca ca the thi lai

			if (count > population.size() - 1) {
				int x = rd.nextInt(population.size());
				int y = rd.nextInt(population.size());
				n = mutation(crossOver3(population.get(x), population.get(y)));
				if (n.heuristic() > finess) {
					n = mutation(crossOver1(population.get(x), population.get(y)));
				}
//				if (n.heuristic() > finess) {
//					n = mutation(crossOver(population.get(x), population.get(y)));
//				}
//				if (n.heuristic() > finess) {
//					n = mutation(crossOver2(population.get(x), population.get(y)));
//				}
//				if (n.heuristic() > finess) {
//					n = mutation(crossOver4(population.get(x), population.get(y)));
//				}

			} else
				n = mutation(population.get(count));
			if (n.heuristic() == 0) {
				System.out.println("is goal: ");
				printNode(n);
				return true;
			}
			if (n.heuristic() < finess) {

				population.add(n);
				System.out.println("cá thể thêm vào: "+population.size());

			}
			count++;
		}
		return false;
	}

// dot bien ca the
	public Node mutation(Node node) {
		Node n = node;
		int count = 0;
		while (count < 50) {
			String[] s = node.find1().split("");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			if (n.checkIndex(x, y) == 0)
				break;
				count++;
				n.state[x][y] = node.rand(x, y);
		}
		return n;
	}

	// lay 3 hang cua cha 3 hang cua me va 3 hang cua cha
	public Node crossOver(Node father, Node mother) {
		Node child = new Node();
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
		Node child = new Node();
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
		Node child = new Node();
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

		Node child = new Node();
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
		Node child = new Node();
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

	public void run() {

		// lai 98 the he
		for (int i = 0; i < 60; i++) {
			// kiem tra goal
			for (Node node : population) {
				if (node.heuristic() == 0) {
					System.out.println("is goal: ");
					printNode(node);
					return;
				}
			}
			// chon quan the cha me
			selectFather();
			selectModer();

			// lai quan the cha voi quan the me
			for (int j = 0; j < fathers.size(); j++) {

				Node child = crossOver3(fathers.get(j), mothers.get(j));
				// new con sinh ra khong dat yeu cau thi dot bien
				if (child.heuristic() > finess)
					child = mutation(child);
				if (child.heuristic() == 0) {
					System.out.println("is goal: ");
					printNode(child);
					return;
				}
				// them vao quan the
				population.add(child);
			}
			// xoa quan the cha me de chon quan the moi
			fathers.clear();
			mothers.clear();
			System.out.println("số cá thể sau khi lai: "+population.size());
			System.out.println("số thế hệ: "+i);
//			if (finess == 2) {
//				createNew();
//				finess = 0;
//				i = 0;
//			}
			// chon loc
			if (selective() == true)
				return;

			System.out.println("số cá thể sau chọn lọc: "+population.size() );
			System.out.println();
			// giam finess sau moi the he
			if (finess > 2)
				finess--;
		}
		sort(population);
		population.get(0).printNode();
		System.out.println();
		System.out.println("heuristic: " + population.get(0).heuristic());
	}

	public void sort(ArrayList<Node> arr) {
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i + 1; j < arr.size(); j++) {
				if (arr.get(i).heuristic() > arr.get(j).heuristic()) {
					Node temp = arr.get(i);
					arr.set(i, arr.get(j));
					arr.set(j, temp);
				}
			}
		}
	}

	public int tb() {
		int x = 0;
		for (Node node : population) {
			x += node.heuristic();
		}
		return (int) x / population.size();
	}

	public void printNode(Node node) {
		for (int i = 0; i < node.state.length; i++) {
			System.out.println();
			for (int j = 0; j < node.state.length; j++) {
				System.out.print(node.state[i][j] + "-");
			}

		}
	}

	public static void main(String[] args) {
		Genetic g = new Genetic();
		long startTime = System.currentTimeMillis();
		g.run();
		long endTime = System.currentTimeMillis();

		long duration = ((endTime - startTime)/1000);
		System.out.println();
		System.out.println(duration+"s");
//		Node n = new Node();
//		Node n1 = new Node();
//		n.printNode();
//		System.out.println();
//		n1.printNode();
//		System.out.println();
//		g.crossOver2(n, n1).printNode();
//		System.out.println(n.heuristic());
//		System.out.println(g.mutation(n).heuristic());
	}
}
