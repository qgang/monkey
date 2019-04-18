package base;

import Util.FileUtil;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.util.*;

public class StringHashcode {

    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }

        return hash;
    }

    /**
     * 计算 hash code 冲突率，顺便分析一下 hash code 最大值和最小值，并输出
     * @param multiplier
     * @param hashs
     */
    public static void calculateConflictRate(Integer multiplier, List<Integer> hashs) {
        Comparator<Integer> cp = (x, y) -> x > y ? 1 : (x < y ? -1 : 0);
        int maxHash = hashs.stream().max(cp).get();
        int minHash = hashs.stream().min(cp).get();

        // 计算冲突数及冲突率
        int uniqueHashNum = (int) hashs.stream().distinct().count();
        int conflictNum = hashs.size() - uniqueHashNum;
        double conflictRate = (conflictNum * 1.0) / hashs.size();

        System.out.println(String.format("multiplier=%4d, minHash=%11d, maxHash=%10d, conflictNum=%6d, conflictRate=%.4f%%",
                multiplier, minHash, maxHash, conflictNum, conflictRate * 100));
    }

    /**
     * 将整个哈希空间等分成64份，统计每个空间内的哈希值数量
     * @param hashs
     */
    public static Map<Integer, Integer> partition(List<Integer> hashs) {
        // step = 2^32 / 64 = 2^26
        final int step = 67108864;
        List<Integer> nums = new ArrayList<>();
        Map<Integer, Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i += step) {
            final long min = i;
            final long max = min + step;
            int num = (int) hashs.parallelStream()
                    .filter(x -> x >= min && x < max).count();

            statistics.put(start++, num);
            nums.add(num);
        }

        // 为了防止计算出错，这里验证一下
        int hashNum = nums.stream().reduce((x, y) -> x + y).get();
        assert hashNum == hashs.size();

        return statistics;
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = FileUtil.openFileReader("D:\\web2");
            List<Integer> hashcodes2 = new ArrayList<>(235886);
            List<Integer> hashcodes3 = new ArrayList<>(235886);
            List<Integer> hashcodes5 = new ArrayList<>(235886);
            List<Integer> hashcodes7 = new ArrayList<>(235886);
            List<Integer> hashcodes17 = new ArrayList<>(235886);
            List<Integer> hashcodes31 = new ArrayList<>(235886);
            List<Integer> hashcodes101 = new ArrayList<>(235886);
            List<Integer> hashcodes127 = new ArrayList<>(235886);
            for (String word = br.readLine(); word != null; word=br.readLine()) {
                hashcodes2.add(hashCode(word, 2));
                hashcodes3.add(hashCode(word, 3));
                hashcodes5.add(hashCode(word, 5));
                hashcodes7.add(hashCode(word, 7));
                hashcodes17.add(hashCode(word, 17));
                hashcodes31.add(hashCode(word, 31));
                hashcodes101.add(hashCode(word, 101));
                hashcodes127.add(hashCode(word, 127));
            }
            calculateConflictRate(2, hashcodes2);
            calculateConflictRate(3, hashcodes3);
            calculateConflictRate(5, hashcodes5);
            calculateConflictRate(7, hashcodes7);
            calculateConflictRate(17, hashcodes17);
            calculateConflictRate(31, hashcodes31);
            calculateConflictRate(101, hashcodes101);
            calculateConflictRate(127, hashcodes127);
            Map<Integer, Integer> map31 = partition(hashcodes31);
            Map<Integer, Integer> map101 = partition(hashcodes101);
            Map<Integer, Integer> map127 = partition(hashcodes127);
            System.out.println(JSON.toJSONString(map31.values()));
            System.out.println(JSON.toJSONString(map101.values()));
            System.out.println(JSON.toJSONString(map127.values()));
            return;
        } catch (Exception e) {

        } finally {
            FileUtil.close(br);
        }
    }
}
