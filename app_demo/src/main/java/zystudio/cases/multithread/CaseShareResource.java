package zystudio.cases.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import zystudio.mylib.utils.LogUtil;

public class CaseShareResource {
    private static CaseShareResource sCase;

    public static CaseShareResource obtain() {
        if (sCase == null) {
            sCase = new CaseShareResource();
        }
        return sCase;
    }

    public void work() {

    }

    public static class EvenChecker implements Runnable {

        private IntGenerator generator;
        private final int id;

        public EvenChecker(IntGenerator g, int ident) {
            generator = g;
            id = ident;
        }

        @Override
        public void run() {
            while (!generator.isCanceled()) {
                int val = generator.next();
                if (val % 2 != 0) {
                    LogUtil.log(val + " not even!");
                    generator.cancel();
                }
            }
        }
        public static void test(IntGenerator gp){
            test(gp,10);
        }
        public static void test(IntGenerator gp,int count){
            ExecutorService exec=Executors.newCachedThreadPool();
            for(int i=0;i<count;i++){
                exec.execute(new EvenChecker(gp, i));
                exec.shutdown();
            }
        }
    }

    public static abstract class IntGenerator {
        private volatile boolean canceled = false;

        public abstract int next();

        public void cancel() {
            canceled = true;
        }

        public boolean isCanceled() {
            return canceled;
        }
    }
}
