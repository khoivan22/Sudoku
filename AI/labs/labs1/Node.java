package labs1;

import java.util.ArrayList;
import java.util.List;

/*
    - Mỗi một Node đại diện cho một state, biến visited để đánh dấu Node này đã từng được duyệt hay chưa,
    danh sách neighbours là danh sách kề của Node, tức là các Node con của Node đang xét, Node parent
    là Node cha của node đang xét.
*/
public class Node {
    private int state;
    private boolean visited;
    private List<Node> neighbours;
    private Node parent;

    public Node(int state) {
        this.state = state;
        this.neighbours = new ArrayList<>();
        this.parent = null;
    }


    /*
    - Hàm này thực hiện nhiệm vụ thêm Node neighbour vào danh sách kề của Node đang xét.
    - Chú ý, chúng takhông new một Node neighbour mới, mà chỉ add địa chỉ của neighbour đang tồn tại vào danh sách kề mà thôi.
    */
    public void addNeighbours(Node neighbourNode) {
        this.neighbours.add(neighbourNode);
    }

    /*
    - Hàm này trả về danh sách toàn bộ các Node con của Node đang xét.
    * */
    public List getNeighbours() {
        return neighbours;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
