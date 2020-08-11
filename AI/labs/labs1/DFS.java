package labs1;

import java.util.List;
import java.util.Stack;

public class DFS {
	Stack<Node> stack = new Stack<>();

	public labs2.Node dfsUsingStack(Node initial, int goal) {
		// Nếu initial là giải pháp mục tiêu thì trả về initial
		if (initial.getState() == goal) {
			System.out.println("* " + initial.getState() + " is goal.");
		} else {
			// Đặt initial vào QUEUE
			initial.setVisited(true);
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
					if (neighbourNode.getState() == goal) {
						neighbourNode.setVisited(true);
						neighbourNode.setParent(node);
						String result = "";
						while (neighbourNode != initial) {
							result = neighbourNode.getState() + " → " + result;
							neighbourNode = neighbourNode.getParent();
						}
						System.out.println("• " + initial.getState() + " → " + replaceLast(result, "→", ""));
						return null;
					} else { // Ngược lại.
						// Nếu nếu neighbourNode chưa được xét thì thêm neighbourNode vào queue.
						if (!neighbourNode.isVisited()) {
							neighbourNode.setVisited(true);
							neighbourNode.setParent(node);
						}
						stack.add(neighbourNode);
					}
				}
			}
			System.out.println("No road.");
		}
		return null;
	}

	private static String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
	}
}
