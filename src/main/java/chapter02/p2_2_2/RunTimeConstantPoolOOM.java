package chapter02.p2_2_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过intern()方法，加字符串添加到常量池中
 *
 * VM args：-XX:PermSize=10M -XX:MaxPermSize=10M 限制方法区大小
 *
 * 在JDK1.7及以前，HotSpot虚拟机将java类信息、常量池、静态变量、即时编译器编译后的代码等数据，
 * 存储在Perm（永久带）里（对于其他虚拟机如BEA JRockit、IBM J9等是不存在永久带概念的），
 * 类的元数据和静态变量在类加载的时候被分配到Perm里，当常量池回收或者类被卸载的时候，垃圾收集器会回收这一部分内存，
 * 但效果不太理想。
 *
 * JDK1.8时，HotSpot虚拟机对JVM模型进行了改造，将类元数据放到了本地内存中，将常量池和静态变量放到了Java堆里，
 * HotSpot VM将会为类的元数据明确的分配与释放本地内存，在这种架构下，类元数据就突破了-XX:MaxPermSize的限制，
 * 所以此配置已经失效，现在可以使用更多的本地内存。这样一定程度上解决了原来在运行时生成大量的类，
 * 从而经常Full GC的问题——如运行时使用反射、代理等。
 *
 */
public class RunTimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用List保持常量池的引用，避免Full GC回收常量池
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
    // 运行结果
    // Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
}
