package me.qinzc.interview.sgg1;

/**
 * desc : redis持久化
 * 1. RDB(Redis DataBase) 快照文件
 * fork一个子进程进行持久化，会先将数据写入临时文件，待持久化结束了，再用这个临时文件替换上次的文件。主进程不进行任何io操作
 * 确保了性能，适合大规模数据恢复，对于数据的完整性不敏感，那么此时RDB比AOF高效，RDB缺点最后一次持久化之后的数据可能丢失
 * 2. AOF(append of file)
 * 以日志的方式记录每一个操作，只允许追加，不允许改写文件。恢复就是读取指令，从头到尾执行一次
 * 备份机制更文件，丢失数据更低，可读的日志文件
 * 比RDB占用磁盘，恢复备份速度慢，每次读写都同步有压力，存在个别bug，恢复不能
 * @author : Zane Qin
 * createTime : 5:04 2023/3/15
 */
public class A14_RedisPersistence {
}
