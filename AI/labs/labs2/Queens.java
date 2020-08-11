package labs2;

public class Queens {

	private int n;
	private Node goal;

	public Queens(int n) {
		this.n = n;
	}

	public void dfs() {

		DFS dfs = new DFS();
		this.goal = dfs.dfsUsingStack(new Node(this.n), this.n);
	}

	public static void main(String[] args) {
		Queens queens = new Queens(4);
		queens.dfs();
	}
}
