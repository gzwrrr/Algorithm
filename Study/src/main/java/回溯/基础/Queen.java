package 回溯.基础;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen {
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> solveNQueens(int n) {
        // 初始化棋盘（实际上是盘中的一行）
        List<Integer> board = new ArrayList<>(n);
        backtrace(board, 0);
        return res;
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     */
    private void backtrace(List<Integer> board, int row) {
    }


}
