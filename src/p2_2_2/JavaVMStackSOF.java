package p2_2_2;

/**
 * VM Args: -Xss128k 减少栈内存容量。
 *
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
    // 运行结果：
    // stack length:991
    // Exception in thread "main" java.lang.StackOverflowError
}
