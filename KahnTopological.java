import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class KahnTopological
{
    public List<Integer> topo(Map<Integer, Node> map){
        //入度
        int[] enterSize=new int[map.size()+1];
        List<Integer> result=new ArrayList<>(map.size());
        for(Entry<Integer,Node> entry: map.entrySet()){
            Node node=entry.getValue();
            int id=entry.getKey();
            List<Integer> friend=node.next;
            for(int i=0;i<friend.size();i++){
                //计算入度
                enterSize[friend.get(i)]++;
            }
        }
        //找到第一个入度为0的节点,即起点
        int start=1;
        for(int i=1;i<=map.size();i++){
            if(enterSize[i]==0){
                start=i;
                break;
            }
        }
        Queue<Integer> queue=new LinkedList<Integer>();
        queue.offer(start);
        while(!queue.isEmpty()){
            Integer temp=queue.poll();
            result.add(temp);
            List<Integer> friend=map.get(temp).next;
            for(int i=0;i<friend.size();i++){
                enterSize[friend.get(i)]--;
                if(enterSize[friend.get(i)]==0){
                    queue.offer(friend.get(i));
                }
            }
        }
        return result;
    }
}

