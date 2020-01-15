package chapter03.p3_3_1;

public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        // 假设在这行发生GC，那么objA和objB是否能被回收？
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 从运行结果中可以清楚地看到GC日志中包含“320K->193K(250240K)”，
     * 意味着虚拟机并没有因为这两个对象互相引用就不回收它们，这也说明
     * 虚拟机不是通过引用计数算法来判断对象是否存活的。
     */
}
