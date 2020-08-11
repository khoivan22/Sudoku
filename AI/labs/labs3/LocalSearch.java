package labs3;

import java.util.*;

public class LocalSearch {

	//trả về tổng xung đột ngang trong một node
	public int checkHorizontal(Node node) {
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
	public int checkDiagonal(Node node) {
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

	//hàm đánh giá mức độ hợp lý của một node,
	//trả về tổng xung đột ngang và chéo
	public int heuristic(Node node) {
		return checkHorizontal(node) + checkDiagonal(node);
	}

	// thay đổi vị trí quân hậu của node tại cột y (đang ở bất kỳ dòng nào) thành dòng x,
	// trả về heuristic của node mới
	public int tryMovingOneQueen(Node node, int x, int y) {
		node.state.set(y, x);
		return heuristic(node);
	}

	// Tạo tất cả các neighbour node của node đang xét, đánh giá các neighbour node này(tạo hậu duệ),
	// nếu có nhiều node có cùng một kết quả đánh giá, chỉ cần lưu lại một node trong chúng.
	// Trả về một SortedMap trong đó key là kết quả đánh giá, value là node mới.
	public SortedMap<Integer, Node> generateNeighbours(Node node) {
		SortedMap<Integer, Node> heuristicNeighbours = new TreeMap<>();
		for (int i = 0; i < node.n; i++) {
			for (int j = 0; j < node.n; j++) {
				Node newNode = new Node(node.n, new ArrayList<>(node.state));
				int row = (node.state.get(i) + j) % node.n;
				int heuristic = tryMovingOneQueen(newNode, i, row);
				System.out.println(heuristic);
				heuristicNeighbours.put(heuristic, newNode);
			}
		}
		return heuristicNeighbours;
	}

	// hàm chạy thuật toán leo đồi
	public void run(int nQueens) {
		Node initial = new Node(nQueens); //hoặc 4,5,6,7
		System.out.print("• Initial state is: " + initial.state);

		if (heuristic(initial) == 0) //goal //global optimize
		{
			//in vị trí của các quân hậu
			System.out.print(" → Goal state!");
			return;
		}

		Node node = initial;
		SortedMap<Integer, Node> neighbours = generateNeighbours(node);

		// lấy neighbours có đánh giá tốt nhất
		Integer bestHeuristic = neighbours.firstKey();

		// trong khi độ tốt chưa tối ưu thì tiếp tục đánh giá để cải thiện độ tốt,
		// cho đến khi không thể cải thiện độ tốt của state
		while (bestHeuristic < heuristic(node)) {
			node = neighbours.get(bestHeuristic);
			neighbours = generateNeighbours(node);
			bestHeuristic = neighbours.firstKey();
		}

		if (heuristic(node) == 0) {
			System.out.println(" → Goal state is: " + node.state);
		} else
			System.out.println(" → Cannot find goal state! Best state is:" + node.state);
	}

	public static void main(String[] args) {
		LocalSearch ls = new LocalSearch();
		ls.run(4);
	}
}