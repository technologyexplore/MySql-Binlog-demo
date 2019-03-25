# MySql-Binlog-demo
MySql主从同步-技术分享-20180907

分享总结：
1.  Mysql数据库查看执行计划EXPLAIN的使用；

2. Mysql的索引类型（Btree，Hash）：善于使用组合索引（where条件和索引顺序需一致），不走索引查询的操作 like ‘%xxxx%’；

3. Left join的关联表字段需要索引

4、Mysql执行顺序FROM->ON->JOIN->WHERE->GROUP BY->HAVING->SELECT->DISTINCT->ORDER BY→LIMIT

5、Mysql主从的配置，原理（Binlog）以及项目通过AOP实现动态读取数据源



需要做更深入的探讨学习：

1、Mysql主从同步的延迟问题，以及解决方案

2、项目实现动态读取数据源的另一个方案：Mybatis拦截器

3、目前项目中有些表索引较多，建议合理使用。减小数据库维护成本

4、数据库锁机制，以及如何避免死锁