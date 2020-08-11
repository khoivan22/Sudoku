package labs1;

import java.util.*;

public class BFS {

	// Queue -> FIFO
	Queue<Node> queue = new LinkedList<>();

	public labs2.Node bfsUsingQueue(Node initial, int goal) {

		// Nếu initial là giải pháp mục tiêu thì trả về initial
		if (initial.getState() == goal) {
			System.out.println("* " + initial.getState() + " is goal.");
		} else {
			// Đặt initial vào QUEUE
			initial.setVisited(true);
			queue.add(initial);
			// Lặp nếu QUEUE chưa rỗng.
			while (!queue.isEmpty()) {
				// Lấy đỉnh node từ QUEUE.
				Node node = queue.poll();

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
							queue.add(neighbourNode);
						}
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


/*
•   add() - Chèn phần tử đã chỉ định vào hàng đợi. Nếu tác vụ thành công, add() trả về true, nếu không nó sẽ ném ra một ngoại lệ.
•   offer() - Chèn phần tử đã chỉ định vào hàng đợi. Nếu tác vụ thành công, offer() trả về true, nếu không nó sẽ trả về false.
•   element() - Trả về phần đầu của hàng đợi. Ném một ngoại lệ nếu hàng đợi trống.
•   peek() - Trả về đầu của hàng đợi. Trả về null nếu hàng đợi trống.
•   remove() - Trả về và xóa phần đầu của hàng đợi. Ném một ngoại lệ nếu hàng đợi trống.
•   poll() - Trả về và loại bỏ phần đầu của hàng đợi. Trả về null nếu hàng đợi trống.
*/
