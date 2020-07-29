package Sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	int[][] state;
	ArrayList<String> indexMap;
//	int map[][] = { 
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
//			{ 0, 0, 0,  0, 0, 0,  0, 0, 0 }
//			 };
	int map[][] = { 
			{ 5, 3, 0,  0, 7, 0,  0, 0, 0 },
			{ 6, 0, 0,  1, 9, 5,  0, 0, 0 },
			{ 0, 9, 8,  0, 0, 0,  0, 6, 0 },
			
			{ 8, 0, 0,  0, 6, 0,  0, 0, 3 },
			{ 4, 0, 0,  8, 0, 3,  0, 0, 1 },
			{ 7, 0, 0,  0, 2, 0,  0, 0, 6 },
			
			{ 0, 6, 0,  0, 0, 0,  2, 8, 0 },
			{ 0, 0, 0,  4, 1, 9,  0, 0, 5 },
			{ 0, 0, 0,  0, 8, 0,  0, 7, 9 }
	};
//	int map[][] = { 
//			{ 0, 1, 0,  0, 0, 2,  3, 4, 0 },
//			{ 0, 0, 4,  0, 0, 5,  6, 1, 0 },
//			{ 0, 7, 0,  0, 3, 0,  0, 0, 8 },
//			
//			{ 0, 0, 0,  8, 0, 3,  1, 0, 2 },
//			{ 0, 0, 9,  0, 0, 0,  4, 0, 0 },
//			{ 5, 0, 3,  6, 0, 4,  0, 0, 0 },
//			
//			{ 8, 0, 0,  0, 7, 0,  0, 9, 0 },
//			{ 0, 4, 2,  1, 0, 0,  7, 0, 0 },
//			{ 0, 9, 6,  2, 0, 0,  0, 3, 0 }
//			 };
//	int map0[][] = { 
//			{ 0, 0, 1,  0, 2, 3,  0, 4, 0 },
//			{ 0, 0, 0,  0, 5, 1,  0, 6, 0 },
//			{ 7, 0, 5,  0, 0, 8,  0, 0, 0 },
//			
//			{ 0, 0, 4,  0, 0, 9,  0, 5, 0},
//			{ 0, 1, 0,  0, 0, 0,  0, 3, 0 },
//			{ 0, 3, 0,  2, 0, 0,  9, 0, 0 },
//			
//			{ 0, 0, 0,  8, 0, 0,  2, 0, 3 },
//			{ 0, 8, 0,  1, 3, 0,  0, 0, 0 },
//			{ 0, 5, 0,  9, 6, 0,  7, 0, 0 }
//			 };
//	int map[][] = { 
//			{ 1, 0, 2,  0, 0, 0,  3, 0, 0 },
//			{ 0, 0, 3,  0, 4, 0,  0, 0, 5 },
//			{ 0, 0, 0,  0, 6, 0,  0, 7, 0 },
//			
//			{ 6, 0, 0,  1, 0, 0,  5, 0, 0 },
//			{ 0, 5, 0,  0, 2, 0,  0, 8, 0 },
//			{ 0, 0, 7,  0, 0, 9,  0, 0, 3 },
//			
//			{ 0, 8, 0,  0, 5, 0,  0, 0, 0 },
//			{ 2, 0, 0,  0, 7, 0,  6, 0, 0 },
//			{ 0, 0, 4,  0, 0, 0,  9, 0, 8 }
//			 };
//	int map[][] = { 
//			{ 0, 0, 1,  0, 0, 0,  2, 0, 0 },
//			{ 0, 3, 0,  0, 0, 4,  0, 0, 5 },
//			{ 6, 0, 0,  0, 7, 0,  0, 3, 0 },
//			
//			{ 0, 0, 4,  0, 0, 5,  0, 0, 0 },
//			{ 0, 7, 0,  0, 2, 0,  0, 8, 0 },
//			{ 0, 0, 0,  9, 0, 0,  6, 0, 0 },
//			
//			{ 0, 2, 0,  0, 8, 0,  0, 0, 1 },
//			{ 5, 0, 0,  6, 0, 0,  0, 7, 0 },
//			{ 0, 0, 9,  0, 0, 0,  3, 0, 0 }
//			 };
	public Node() {
		super();
		
		indexMap=new ArrayList<String>();
		findIndexM();
		Random rd = new Random();
		this.state = new int[9][9];
		for (int i = 0; i < state.length; i++) {
//			System.out.println();
			for (int k = 0; k < state.length; k++) {
//				this.state[i][k]=k;
				if (map[i][k] == 0) {
				int element = rd.nextInt(9);
				this.state[i][k] = element+1;
				} else {
					this.state[i][k] = map[i][k];
				}
//				System.out.print(k+"--");
			}
		}
//		for (int i = 0; i < state.length; i++) {
//			System.out.println();
//			for (int j = 0; j < state.length; j++) {
//				System.out.print(this.state[i][j]);
//			}
//		}
	}

	public boolean isGoal() {
		if (heuristic() == 0)
			return true;
		else
			return false;
	}

	public int checkHorizontal() {
		int re = 0;
		for (int i = 0; i < this.state.length; i++) {
			for (int j = 0; j < this.state.length; j++) {
//				System.out.println(i+""+j);
				for (int j2 = j + 1; j2 < state.length; j2++) {
					if (this.state[i][j] == this.state[i][j2])
						re++;
				}
			}
		}
//		System.out.println(re);
		return re;

	}

	public int checkDoc() {
		int re = 0;
		for (int i = 0; i < this.state.length; i++) {
			for (int j = 0; j < this.state.length; j++) {
				for (int j2 = j + 1; j2 < state.length; j2++) {
					if (this.state[j][i] == this.state[j2][i])
						re++;
				}
			}
		}
//		System.out.println("doc:"+re);
		return re;
	}

	public int check3x3() {
		int count = 0;
		int dex = 0;
		List<Integer> a = new ArrayList<Integer>();
		// lay tung o 3x3 de kiem tra
		for (int l = 0; l < 3; l++) {
			int index1 = 0;
			for (int k = 0; k < 3; k++) {
//				System.out.println();
//				System.out.println();
				for (int i = dex; i < dex + 3; i++) {
//					System.out.println();
					for (int j = index1; j < index1 + 3; j++) {
//						System.out.print(i+""+j+"--");
						a.add(this.state[i][j]);
					}

				}
				for (int i = 0; i < state.length; i++) {
					for (int j = i + 1; j < state.length; j++) {
						if (a.get(i) == a.get(j))
							count++;
					}
				}
//				System.out.println(count);
				a.removeAll(a);
				index1 += 3;
			}
			dex += 3;
		}
		return count;
	}

	public int heuristic() {
		return this.check3x3() + this.checkDoc() + this.checkHorizontal();
	}
	
	
	public int checkIndex(int x, int y) {
		int result=0;		
			for (int j = 0; j < 9; j++) {
				if(y!=j&&state[x][y]==state[x][j]) {
					result++;
				}
				if(x!=j&&state[x][y]==state[j][y]) {
					result++;
				}
		}
			
			int dx=(x<3)?0:(x<6)?3:6;
			int dy=(y<3)?0:(y<6)?3:6;
			
//			if(y<3) dy=0; else if(y<6) dy=3; else if(y<9) dy=6;
//			//
//			if(x<3) dx=0; else if(x<6) dx=3; else if(x<9) dx=6;
			//check 3x3
//			System.out.println(dx+"-"+dy);
			for (int i = dx; i < dx+3; i++) {
				for (int j = dy; j < dy+3; j++) {
					if(state[x][y]==state[i][j])
						result++;
				}
			}
			// vi check 3x3 chua tru chinh no ne phia -1
		return result-1;
	}
	public String find() {
		int x=0;
		int y=0;
		for (int i = 0; i < state.length; i++) {
			
//			System.out.println();
			for (int j = 0; j < state.length; j++) {
					if(checkIndex(x, y)<checkIndex(i, j)) {
						x=i;
						y=j;
				}
			}
		}
		return x+""+y;
	}
	public String find1() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < state.length; i++) {

//			System.out.println();
			for (int j = 0; j < state.length; j++) {
				int check = 0;

				if (checkIndex(x, y) < checkIndex(i, j)) {
					for (String str : indexMap) {
						String[] s = str.split("");
						if (i == Integer.parseInt(s[0]) && j == Integer.parseInt(s[1])) {
							check++;
						}
					}
					if (check == 0) {
						x = i;
						y = j;
					}
				}
			}
		}
		return x + "" + y;
	}
	public void findIndexM() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] != 0)
					indexMap.add(i + "" + j);
			}
		}
	}
	public void printNode() {
		for (int i = 0; i < state.length; i++) {
			System.out.println();
			for (int j = 0; j < state.length; j++) {
				System.out.print(state[i][j] + "-");
			}

		}
	}
	public static void main(String[] args) {
//		System.out.println(new Node().heuristic());
		
		Node n= new Node();
		n.printNode();
		System.out.println();
		System.out.println("======="+n.find()+"======");
//		n.checkIndex(0, 6);
	}

}
