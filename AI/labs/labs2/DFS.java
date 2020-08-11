package labs2;

import java.util.List;
import java.util.Stack;

public class DFS {
	Stack<Node> stack = new Stack<>();

	public Node dfsUsingStack(Node initial, int nQueens) {
		// Nếu initial là giải pháp mục tiêu thì trả về initial
		if (initial.state.size() == nQueens) {
			System.out.println("* " + initial.state + " is goal.");
		} else {
			// Đặt initial vào QUEUE
			stack.add(initial);
			// Lặp nếu QUEUE chưa rỗng.
			while (!stack.isEmpty()) {
				// Lấy đỉnh node từ QUEUE.
				Node node = stack.pop();

				List<Node> neighbourList = node.getNeighbours();
				// Duyệt đỉnh node.
				for (int i = 0; i < neighbourList.size(); i++) {
					// Chọn lần lượt đỉnh kề neighbourNode của node.
					Node neighbourNode = neighbourList.get(i);
					// Nếu đỉnh neighbourNode là goal thì trả về kết quả.
					if (neighbourNode.state.size() == nQueens) {
						System.out.println("→ " + neighbourNode.state);
						return null;
					} else {
						stack.add(neighbourNode);
					}
				}
			}
			System.out.println("No road.");
		}
		return null;
	}
}
