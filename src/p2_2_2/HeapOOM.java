package p2_2_2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms10m 最小内存
 *          -Xmx10m 最大内存
 *          -XX:+PrintGCDetails 打印GC细节
 *          -XX:+HeapDumpOnOutOfMemoryError 让虚拟机在出现内存溢出时Dump出当前的内存堆转储快照以便事后进行分析
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
