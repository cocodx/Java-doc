#### ForkJoinPool

ForkJoinPool是Java中的一个线程池，它可以用于并行执行任务。它使用“工作窃取”算法，这意味着当一个线程没有任务可以执行时，它可以从另一个线程的任务队列中“窃取”任务来执行。

```java
import java.util.concurrent.*;

public class ForkJoinPoolExample {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(nums, 0, nums.length);

        int result = forkJoinPool.invoke(task);

        System.out.println("Sum is: " + result);
    }

    private static class SumTask extends RecursiveTask<Integer> {
        private int[] nums;
        private int start;
        private int end;

        public SumTask(int[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 1) {
                return nums[start];
            }

            int mid = (start + end) / 2;

            SumTask leftTask = new SumTask(nums, start, mid);
            SumTask rightTask = new SumTask(nums, mid, end);

            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}

```

在上面的示例中，我们创建了一个数组并使用ForkJoinPool并行计算数组元素的总和。我们创建了一个SumTask类，它是一个递归任务，用于将数组分成更小的子集，直到每个子集只有一个元素，然后返回结果并将它们相加。我们使用fork()方法来启动新的子任务，并使用join()方法等待它们完成并返回结果。

在main()方法中，我们创建了一个ForkJoinPool对象，并将SumTask传递给invoke()方法，它将返回计算的总和。最后，我们将结果输出到控制台。

#### ForkJoinPool.getCommonPoolParallelism()

ForkJoinPool.getCommonPoolParallelism() 是一个 Java 方法，它返回 ForkJoinPool 的公共池的并行度（parallelism），即可同时执行的任务数。

在 Java 8 中，引入了 ForkJoinPool 框架来支持并行计算。这个框架使用工作窃取算法（work-stealing algorithm）来平衡任务的执行负载。ForkJoinPool 具有两个实例，分别是私有池（private pool）和公共池（common pool）。

公共池是一个静态线程池，可以由多个 ForkJoinTask 共享。公共池的大小通常是根据可用的处理器数量动态调整的，因此在不同的机器上可能会有所不同。ForkJoinPool.getCommonPoolParallelism() 方法返回公共池的并行度，即公共池中可同时执行的任务数。

一般来说，公共池的并行度应该等于机器上可用的处理器数量，因为这样可以充分利用所有的处理器。但是，如果应用程序中有其他线程或任务需要执行，公共池的并行度可能会受到限制。因此，在使用 ForkJoinPool 框架时，需要考虑并行度的设置以及其他任务或线程的影响。

#### ForkJoinWorkerThread

ForkJoinWorkerThread 是 ForkJoinPool 框架中的一个类，用于表示执行任务的工作线程。它是 ForkJoinPool 中实际执行任务的线程，每个工作线程都有一个工作队列，用于存储待执行的任务。

当一个任务需要执行时，ForkJoinPool 会选择一个工作线程来执行该任务。如果当前没有可用的工作线程，ForkJoinPool 会创建一个新的工作线程来执行该任务。每个工作线程都会不断地从自己的工作队列中取出任务并执行，直到工作队列为空或者当前工作线程被中断。

ForkJoinWorkerThread 还可以通过覆盖其 onStart() 和 onTermination(Throwable) 方法来添加特定的行为。例如，在 onStart() 方法中可以执行一些初始化操作，而在 onTermination(Throwable) 方法中可以执行一些清理操作。

总之，ForkJoinWorkerThread 是 ForkJoinPool 框架中执行任务的工作线程，它维护着自己的工作队列，并根据需要执行任务。

