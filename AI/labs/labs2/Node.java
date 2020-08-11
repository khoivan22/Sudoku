package labs2;

import java.util.ArrayList;
import java.util.List;

public class Node {
	int n;//n là số quân hậu cần được đặt.
	List<Integer> state;//state là vị trí các quân hậu hiện tại trên bàn cờ.
	List<Node> neighbours;//neighbours là danh sách các Node con của node đang xét

	public Node(int n) {
		this.n = n;
		this.state = new ArrayList();
		this.neighbours = new ArrayList();
	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = state;
		this.neighbours = new ArrayList();
	}

	public void addNeighbours(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	//kiểm tra một state có hợp lệ hay không
	public boolean isValid(List<Integer> state) {
		boolean result = false;

		int nodeLast = state.get(state.size() - 1);
		if (state.size() == 1) {
			result = false;
		} else {
			for (int i = 0; i < state.size() - 1; i++) {
				if (nodeLast == state.get(i)) {
					result = true;
				} else {
					// kiểm tra xung đột chéo
					if (Math.abs(nodeLast - state.get(i)) == Math.abs((state.size() - 1) - i)) {
						result = true;
					}
				}
			}
		}
		return result;
	}


	//	để kiểm tra có thể đặt con hậu mới vào vị trí x của cột mới hay không
	private List<Integer> place(int x) {
		List<Integer> copyState = new ArrayList<>(this.state); // clone list 'state'
		copyState.set(copyState.size() - 1, x);
		if (isValid(copyState)) {
			return null;
		}
		return copyState;
	}

	public List<Node> getNeighbours() {

		List<Integer> neighboursNode;
		if (this.state.size() == this.n) {
			return null;
		}
		for (int i = 0; i < this.state.size(); i++) {
			neighboursNode = place(i);
			if (neighboursNode != null) {
				Node nodeChild = new Node(neighboursNode.get(i));
				this.addNeighbours(nodeChild);
			}
		}
		return this.neighbours;
	}
}
