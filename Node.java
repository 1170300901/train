import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    String name;
    int id;
    double min = 0;
    double max = Integer.MAX_VALUE;
    List<Integer> next;
    Map<Integer, Double> time=new HashMap<>();
    public Node(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Node() {
    }

}
