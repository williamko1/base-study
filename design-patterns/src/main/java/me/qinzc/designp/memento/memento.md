备忘录(memento) 模式
将中间数据暂存之后再恢复

场景：
1. 计算出来一份中间数据的类
2. 这个类，之后需要基于这份中间数据执行另外2次操作，每次操作都要基于这份中间数据区执行
3. 第一次基于中间数据操作
4. 第二次基于未修改的中间数据操作