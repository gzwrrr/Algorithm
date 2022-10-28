package 位运算;

/**
 * 左移只有无符号，补 0
 * >> 有符号右移，补符号位
 * >>> 无符号右移，补 0
 * @author gzw
 */
public class PrintInt {
    public static void main(String[] args) {
        // 01111111111111111111111111111111，最大 + 1 溢出变成最小
        printInt(Integer.MAX_VALUE);
        // 10000000000000000000000000000000
        printInt(Integer.MIN_VALUE);
        // 有符号右移
        printInt(Integer.MIN_VALUE >> 1);
        // 无符号右移
        printInt(Integer.MIN_VALUE >>> 1);
    }

    public static void printInt(int num) {
        // 打印的值再转成十进制要求补码
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
