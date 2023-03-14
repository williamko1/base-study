package me.qinzc.interview.sgg1;

/**
 * desc :
 * 事务的传播
 * Transactional(propagation = Propagation.REQUIRED)
 * REQUIRED      有事务用外层的，没事务新建。  串行失败
 * SUPPORTS
 * MANDATORY
 * REQUIRES_NEW  开启一个新事务。           各不相干
 * NOT_SUPPORTED
 * NEVER
 * NESTED
 *
 * 事务的隔离性
 * Transactional(isolation = Isolation.DEFAULT
 * DEFAULT          mysql（repeatable_read)   oracle(read_committed_
 * READ_UNCOMMITTED 读未提交    脏   不可重复读   幻
 * READ_COMMITTED   读已提交    不可重复度  幻
 * REPEATABLE_READ  重复读      幻
 * SERIALIZABLE     串行化
 *
 * @author Zane Qin
 * @creatTime : 15:18 2023/3/14
 * @modifier:
 * @modifyTime:
 */
public class A08_SpringTransaction {

}
