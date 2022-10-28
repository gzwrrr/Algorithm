package 对数器;

/**
 * p17
 * 自己改造随机
 * 例如：17 ~ 56 等概率返回，39 = 56 - 17 + 1
 *   f(): 0/1        6 位最大 64         超出 39 就重做    最后得到 0 ~ 39 再加上 17 即可
 * 获得 01 发生器 -> 看看需要几个二进制位 -> 超出范围就重做 -> 最后加回去
 * @author gzw
 */
public class randomTest {

    public static void main(String[] args) {
        int testTimes = 10000000;
        int[] counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int ans = g();
            counts[ans]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "出现了：" + counts[i]);
        }
    }

    public static int g() {
        return f06() + 1;
    }

    public static int f06() {
        int ans = 0;
        do {
            ans = f07();
        } while (ans == 7);
        return ans;
    }

    // 使用 0 1 等概率发生器随机出等概率的 [0, 7]
    public static int f07() {
        return (f01() << 2) + (f01() << 1) + f01();
    }

    // 只能用 randomf 获得等概率的 0 和 1
    public static int f01() {
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    // 下面的函数不能改变
    // [1, 5] 等概率返回
    public static int f() {
        return (int)(Math.random() * 5) + 1;
    }
}
