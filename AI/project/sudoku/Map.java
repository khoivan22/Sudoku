package sudoku;

import java.util.ArrayList;

public class Map {
	ArrayList<int[][]> easy;
	ArrayList<int[][]> normal;
	ArrayList<int[][]> hard;

	public Map() {
		easy = new ArrayList<>();
		normal = new ArrayList<>();
		hard = new ArrayList<>();
		addMapE();
		addMapN();
		addMapH();
	}

	private void addMapE() {
		int map2[][] = {
				{1, 0, 2, 0, 0, 0, 3, 0, 0},
				{0, 0, 3, 0, 4, 0, 0, 0, 5},
				{0, 0, 0, 0, 6, 0, 0, 7, 0},

				{6, 0, 0, 1, 0, 0, 5, 0, 0},
				{0, 5, 0, 0, 2, 0, 0, 8, 0},
				{0, 0, 7, 0, 0, 9, 0, 0, 3},

				{0, 8, 0, 0, 5, 0, 0, 0, 0},
				{2, 0, 0, 0, 7, 0, 6, 0, 0},
				{0, 0, 4, 0, 0, 0, 9, 0, 8}
		};
		int map1[][] = {
				{5, 3, 0, 0, 7, 0, 0, 0, 0},
				{6, 0, 0, 1, 9, 5, 0, 0, 0},
				{0, 9, 8, 0, 0, 0, 0, 6, 0},

				{8, 0, 0, 0, 6, 0, 0, 0, 3},
				{4, 0, 0, 8, 0, 3, 0, 0, 1},
				{7, 0, 0, 0, 2, 0, 0, 0, 6},

				{0, 6, 0, 0, 0, 0, 2, 8, 0},
				{0, 0, 0, 4, 1, 9, 0, 0, 5},
				{0, 0, 0, 0, 8, 0, 0, 7, 9}
		};
		easy.add(map1);
		easy.add(map2);
	}

	private void addMapN() {
	}

	private void addMapH() {
	}
}
