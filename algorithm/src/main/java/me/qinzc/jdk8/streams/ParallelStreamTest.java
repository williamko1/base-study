package me.qinzc.jdk8.streams;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * desc : 并行流测试
 *
 * 尽管如此，请记住，并行化并不是没有代价的。并行化过程本身需要对流做递归划分，把每
 * 个子流的归纳操作分配到不同的线程，然后把这些操作的结果合并成一个值。但在多个内核之间
 * 移动数据的代价也可能比你想的要大，所以很重要的一点是要保证在内核中并行执行工作的时间
 * 比在内核之间传输数据的时间长。总而言之，很多情况下不可能或不方便并行化。然而，在使用
 * 并行Stream加速代码之前，你必须确保用得对；如果结果错了，算得快就毫无意义了。让我们
 * 来看一个常见的陷阱。
 * <p>
 * 1. 留意装箱。自动装箱和拆箱操作会大大降低性能。Java 8中有原始类型流（IntStream、
 * LongStream、DoubleStream）来避免这种操作，但凡有可能都应该用这些流。
 * 2. 有些操作本身在并行流上的性能就比顺序流差。特别是limit和findFirst等依赖于元
 * 素顺序的操作，它们在并行流上执行的代价非常大。例如，findAny会比findFirst性
 * 能好，因为它不一定要按顺序来执行。你总是可以调用unordered方法来把有序流变成
 * 无序流。那么，如果你需要流中的n个元素而不是专门要前n个的话，对无序并行流调用
 * limit可能会比单个有序流（比如数据源是一个List）更高效。
 * 3. 对于较小的数据量，选择并行流几乎从来都不是一个好的决定。并行处理少数几个元素
 * 的好处还抵不上并行化造成的额外开销。
 * 4. 要考虑流背后的数据结构是否易于分解。例如，ArrayList的拆分效率比LinkedList
 * 高得多，因为前者用不着遍历就可以平均拆分，而后者则必须遍历。另外，用range工厂
 * 方法创建的原始类型流也可以快速分解。最后，你将在7.3节中学到，你可以自己实现
 * Spliterator来完全掌控分解过程。
 * <p>
 * 源        可分解性
 * ArrayList 极佳
 * LinkedList 差
 * IntStream.range 极佳
 * Stream.iterate 差
 * HashSet 好
 * TreeSet 好
 * @author Zane Qin
 * creatTime : 09:19 2022/8/16
 * modifier:
 * modifyTime:
 */
public class ParallelStreamTest {


    @Test
    public void test1() {
        // 10
        System.out.println("Parallel range sum done in:" + measureSumPerf(ParallelStreamTest::parallelRangedSum, 10_000_000_0) + " msecs");
        // 26
        System.out.println("Iterative sum done in:" + measureSumPerf(ParallelStreamTest::iterativeSum, 10_000_000_0) + " msecs");
        // 45
        System.out.println("ranged sum done in: " + measureSumPerf(ParallelStreamTest::rangedSum, 10_000_000_0) + " msecs" );
        // 951
        System.out.println("Sequential sum done in:" + measureSumPerf(ParallelStreamTest::sequentialSum, 10_000_000_0) + " msecs");
        // 1304
        System.out.println("Parallel iterate sum done in:" + measureSumPerf(ParallelStreamTest::parallelIterateSum, 10_000_000_0) + " msecs");
        // 15414
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreamTest::parallelSum, 10_000_000_0) + " msecs" );

    }


    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    /**
     * best 10ms
     * 使用LongStream,避免自动拆装箱，
     * 使用rangeClosed
     * 使用parallel 多线程
     */
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 26ms
     * 原始for循环遍历long
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 45ms
     * LongStream
     * rangeClosed
     */
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    /**
     * 951ms - 性能差
     * Stream.iterate.limit
     *
     * 未使用 LongStream
     * 未使用 rangeClose
     * 未使用 多线程
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 1304ms - 性能差
     * 使用LongStream 避免自动拆装箱
     * 使用limit
     * 使用多线程
     *
     * 未使用rangeClose
     */
    public static long parallelIterateSum(long n) {
        return LongStream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 15414ms - 性能极差
     * 有自动拆装箱
     * 使用limit
     * 使用多线程
     *
     *
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

}
