package xyz.intent.iface.util;

/**
 * xyz.zzyitj.nbt.util
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/5/31 11:08 上午
 * @since 1.0
 */
public class ByteUtils {
    public static final long BYTE_B = 1024L;
    public static final long BYTE_KB = BYTE_B * BYTE_B;
    public static final long BYTE_MB = BYTE_B * BYTE_KB;
    public static final long BYTE_GB = BYTE_B * BYTE_MB;
    public static final long BYTE_TB = BYTE_B * BYTE_GB;
    public static final long BYTE_PB = BYTE_B * BYTE_TB;

    /**
     * 大端存储：Big-endian
     * 10进制的short转16进制的字节数组
     * 数据的高字节保存在内存的低地址中
     * 数据的低字节保存在内存的高地址中
     *
     * @param val short
     * @return byte[]
     */
    public static byte[] shortToBytesBigEndian(short val) {
        byte[] data = new byte[2];
        data[0] = (byte) (val >> 8 & 0xff);
        data[1] = (byte) (val & 0xff);
        return data;
    }

    /**
     * 小端存储：Little-endian
     * 10进制的int转16进制的字节数组
     * 将int转为低字节在前，高字节在后的byte数组
     * 数据的高字节保存在内存的高地址中
     * 数据的低字节保存在内存的低地址中
     *
     * @param val int
     * @return 字节数组
     */
    public static byte[] intToBytesLittleEndian(int val) {
        byte[] data = new byte[4];
        data[0] = (byte) (val & 0xff);
        data[1] = (byte) ((val >> 8) & 0xff);
        data[2] = (byte) ((val >> 16) & 0xff);
        data[3] = (byte) ((val >> 24) & 0xff);
        return data;
    }

    /**
     * 大端存储：Big-endian
     * 10进制的int转16进制的字节数组
     * 数据的高字节保存在内存的低地址中
     * 数据的低字节保存在内存的高地址中
     *
     * @param val int
     * @return byte[]
     */
    public static byte[] intToBytesBigEndian(int val) {
        byte[] data = new byte[4];
        data[3] = (byte) (val & 0xff);
        data[2] = (byte) (val >> 8 & 0xff);
        data[1] = (byte) (val >> 16 & 0xff);
        data[0] = (byte) (val >> 24 & 0xff);
        return data;
    }

    /**
     * 把16进制的data[start,end]转换为十进制int
     *
     * @param data  字节数组
     * @param start 开始的位置
     * @param end   结束的位置
     * @return 10进制
     */
    public static int bytesToInt(byte[] data, int start, int end) {
        if (data.length < (end + 1)) {
            return 0;
        }
        int num = 0;
        for (int i = end; i >= start; i--) {
            // 先把当前的data[i]求出，再求和
            int temp = (data[i] & 0xff) << (8 * (end - i));
            num += temp;
        }
        return num;
    }
}
