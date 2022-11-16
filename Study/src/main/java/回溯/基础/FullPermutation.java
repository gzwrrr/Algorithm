package 回溯.基础;

import java.util.LinkedList;
import java.util.List;

/**
 * 全排列问题
 * 多叉决策树
 * 回溯解法
 * 时间复杂度：O(N!)
 * @author gzw
 */
public class FullPermutation {
    public static void main(String[] args) {
        FullPermutation fullPermutation = new FullPermutation();
        int[] nums = {1, 2, 3};
        System.out.println(fullPermutation.permute(nums));
    }

    List<List<Integer>> res = new LinkedList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        // 当记录的节点数与 nums 长度一样时说明完成一次排列，直接将结果放入 res 中
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            // 做出选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}
