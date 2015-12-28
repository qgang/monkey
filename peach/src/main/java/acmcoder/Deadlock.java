package acmcoder;

/**
 * Created by gang.qin on 2015/9/21.
 * 考点：检测死锁
 * 测试数据输入：
 7
 123	1001 1002	1003
 127
 128	1010
 129
 124	1007	1008
 125	1003	1004
 126	1004	1002

 输出：1；
 */
import java.util.*;

public class Deadlock {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //数据输入
        int n = Integer.parseInt(scan.nextLine());
        String line = null;
        List<ProcessInfo> pos = new ArrayList<ProcessInfo>();
        for (int i = 0; i < n; i++) {
            line = scan.nextLine();
            String[] strs = line.split("\t");
            ProcessInfo po = new ProcessInfo();
            po.id = Integer.parseInt(strs[0]);
            if (!" ".equals(strs[1])) {
                List hasLockedId = new ArrayList();
                String[] lockedIds = strs[1].split(" ");
                for (int j = 0; j < lockedIds.length; j++) {
                    hasLockedId.add(Integer.parseInt(lockedIds[j]));
                }
                po.hasLockedId = hasLockedId;
            }
            if (!" ".equals(strs[2])) {
                po.needLockedId = Integer.parseInt(strs[2]);
            }
            pos.add(po);
        }

        //结果输出
        System.out.println(lookup(pos));
    }

    //查找形成死锁的环的个数
    private static int lookup(List<ProcessInfo> pos) {
        Set<String> cycle = new HashSet<String>();
        for (ProcessInfo po : pos) {
            if (po.needLockedId != null) {
                String cycleIds = getCycleIds(po, pos);
                if (cycleIds != null) {
                    cycle.add(cycleIds);
                }
            }
        }
        return cycle.size();
    }

    //从某个进程开始，若存在死锁，返回形成死锁的进程id列表（按id从小到大排序）
    private static String getCycleIds(ProcessInfo po, List<ProcessInfo> pos) {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(po.id);
        ProcessInfo next = getNext(po, pos);
        while (next != null && po.id != next.id) {
            ids.add(next.id);
            next = getNext(next, pos);
        }

        if (next == null) {
            return null;
        }

        Collections.sort(ids);
        return ids.toString();
    }

    //查找某个进程需要的锁id已被哪个经常所持有，返回进程信息，否则返回null
    private static ProcessInfo getNext(ProcessInfo po, List<ProcessInfo> pos) {
        for (ProcessInfo index : pos) {
            List<Integer> hasLockedId = index.hasLockedId;
            if (hasLockedId != null && hasLockedId.contains(po.needLockedId)) {
                return index;
            }
        }
        return null;
    }

}

class ProcessInfo {
    public int id;//进程Id
    public List<Integer> hasLockedId;//已持有锁Id列表
    public Integer needLockedId;//需要锁Id
}

