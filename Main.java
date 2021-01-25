import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Node> map = new HashMap<>();
        Node node1 = new Node("开始", 1);
        Node node2 = new Node("桌面开料", 2);
        Node node3 = new Node("抛光", 3);
        Node node4 = new Node("组装", 4);
        Node node5 = new Node("油漆", 5);
        Node node6 = new Node("全桌组装", 6);
        Node node7 = new Node("结束", 7);
        Node node8 = new Node("桌腿开料", 8);
        Node node9 = new Node("打磨", 9);
        Node node10 = new Node("抽屉版开料", 10);
        Node node11 = new Node("抽屉组装", 11);
        Node node12 = new Node("抽屉油漆", 12);
        Node node13 = new Node("13", 13);
        Node node14 = new Node("14", 14);
        Node node15 = new Node("15", 15);
        node1.next = Arrays.asList(2, 8, 10);
        node2.next = Arrays.asList(3);
        node3.next = Arrays.asList(4);
        node4.next = Arrays.asList(5);
        node5.next = Arrays.asList(6);
        node6.next = Arrays.asList(7);
        node7.next = new ArrayList<>();
        node8.next = Arrays.asList(9);
        node9.next = Arrays.asList(4);
        node10.next = Arrays.asList(11);
        node11.next = Arrays.asList(12);
        node12.next = Arrays.asList(6);
        node1.time.put(2, 0.5);
        node1.time.put(8, 0.5);
        node1.time.put(10, 1.0);
        node2.time.put(3, 0.5);
        node3.time.put(4, 0.5);
        node4.time.put(5, 0.5);
        node5.time.put(6, 1.0);
        node6.time.put(7, 0.0);
        node8.time.put(9, 1.0);
        node9.time.put(4, 0.5);
        node10.time.put(11, 1.0);
        node11.time.put(12, 2.0);
        node12.time.put(6, 1.0);
        map.put(1, node1);
        map.put(2, node2);
        map.put(3, node3);
        map.put(4, node4);
        map.put(5, node5);
        map.put(6, node6);
        map.put(7, node7);
        map.put(8, node8);
        map.put(9, node9);
        map.put(10, node10);
        map.put(11, node11);
        map.put(12, node12);
        KahnTopological kahnTopological = new KahnTopological();

        //计算拓扑顺序
        List<Integer> list = kahnTopological.topo(map);
        System.out.println(list);
        //计算最小
        calculateMin(map, 1, 7, list);
        for (Entry<Integer, Node> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue().min);
        }
        map.get(7).max = map.get(7).min;
        //计算最大
        calculateMax(map, 1, 7, list);
        for (Entry<Integer, Node> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue().max);
        }
        //输出结果
        Set<Integer> ans=new HashSet();
        for (Entry<Integer, Node> entry : map.entrySet()) {
            if(entry.getValue().min==entry.getValue().max){
                ans.add(entry.getKey());
            }
        }
        for(int i=0;i<list.size();i++){
            if(ans.contains(list.get(i))){
                System.out.println("id:"+list.get(i)+"  ,"+map.get(list.get(i)).name);
            }
        }
        System.out.println("cost: " +map.get(7).max);
    }

    private static void calculateMax(Map<Integer, Node> map, int start, int end, List<Integer> toPo) {
        for (int i = toPo.size() - 1; i >= 0; i--) {
            Node tempTo = map.get(toPo.get(i));
            //看有谁和它相连
            for (Entry<Integer, Node> entry : map.entrySet()) {
                Node friendFrom = entry.getValue();
                if (friendFrom.time.get(toPo.get(i)) != null) {
                    if (friendFrom.max <= (tempTo.max - friendFrom.time.get(toPo.get(i)))){

                    }else{
                        friendFrom.max=(tempTo.max - friendFrom.time.get(toPo.get(i)));
                    }
                }
            }
        }
        return;
    }

    public static void calculateMin(Map<Integer, Node> map, Integer start, Integer end, List<Integer> toPo) {
        for (int i = 0; i < toPo.size(); i++) {
            Node temp = map.get(toPo.get(i));
            List<Integer> friend = temp.next;
            for (int j = 0; j < friend.size(); j++) {
                Node littleBrother = map.get(friend.get(j));
                if (littleBrother.min >= temp.min + temp.time.get(friend.get(j))) {

                } else {
                    littleBrother.min = temp.min + temp.time.get(friend.get(j));
                }
            }
        }
    }
}
