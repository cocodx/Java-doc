#### 粗心导致删除全表更新

##### 现象说明

在baseMapper执行delete()方法，出现mybatis-plus的全表更新update报错 **Full table operation is prohibited**

##### 最终结果

根据网上的结果，执行delete()方法，是一个LambdaQueryWrapper，delete，实际上对映射的类字段del_flag更新为1，
delete，原本我定义了removeWrapper对象进行删除，查询时候用queryWrapper对象进行查询，但是删除的时候，填的对象是queryWrapper。导致了，后来改回来又正常了。
可能是mybatis-plus对全表统计的对象是new出来的LambdaQueryWrapper。

