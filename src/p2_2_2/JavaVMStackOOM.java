package p2_2_2;

/**
 * VM Args: -Xss2M 减少栈内存容量。
 *
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
    // 运行结果：
    //Exception in thread "main" java.lang.OutOfMemoryError:unable to create new native thread
}
