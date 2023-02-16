package LeetCodeRandom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Code_1604 {
    //题目链接：https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/description/
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            map.putIfAbsent(name, new ArrayList<Integer>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            map.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String name : keySet) {
            List<Integer> list = map.get(name);
            Collections.sort(list);
            for (int i = 2; i < list.size(); i++) {
                int time1 = list.get(i);
                int time2 = list.get(i - 2);
                int difference = time1 - time2;
                if (difference <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
