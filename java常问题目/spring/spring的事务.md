spring的事务是依托于mysql的事务来。就像应用层都依托于基础层。

控制业务方法是不是有事务的，是什么样的事务的。共有 7 个传播行为。（标红常用需掌握）
（1）PROPAGATION_REQUIRED：指定的方法必须在事务内执行。若当前存在事务，就加入当前事务中；若当前没有事务，则创建一个新事务。这种创博行为时最常见的选择，也是spring默认的事务传播行为。
（2）PROPAGATION_REQUIRED_NEW：总是新建一个事务，若当前存在事务，就将当前事务挂起，知道新事物执行完毕。
（3）PROPAGATION_SUPPORTS：指定方法支持当前事务，但若当前没有事务，也可以以非事务的方式执行。
（4）PROPAGATION_MANDATORY
（5）PROPAGATION_NESTED
（6）PROPAGATION_NEVER
（7）PROPAGATION_NOT_SUPPORTED

可以根据改造开户流程：说下用了什么事务


@Transactional的属性

isolation：用于设置隔离级别，该属性类型为isolation枚举，默认值为 Isolation.DEFUALT。
readOnly：用于设置方法对数据库的操作是否只读的，该属性为boolean，默认值为false。
timeout：用于设置本操作与数据库连接超时时限。单位为妙，类型为int，默认值为-1，即没有时限。
rollbackFor：指定需要回滚的异常类，类型为Class[]，默认值为空数组。当然，若只有一个异常类，可以不使用数组。
rollbackForClassName：指定需要回滚的异常类类名。类型为String[]，默认值为空数组。当然，若只有一个异常类，可以不使用数组。
noRollbackFor：指定不需要回滚的异常类，类型为Class[]，默认值为空数组。当然，若只有一个异常类，可以不使用数组。（与rollbackFor相反）
noRollbackForClassName：指定不需要回滚的异常类类名，类型为Class[]，默认值为空数组。当然，若只有一个异常类，可以不使用数组。（与rollbackForClassName相反）