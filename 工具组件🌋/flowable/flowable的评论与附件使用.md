#### 评论涉及到的表

act_ge_bytearray act_hi_attachment act_hi_comment

act_ge_bytearray 保存文件

act_hi_attachment 保存附件记录

act_hi_comment 评论历史表

act_hi_attachment 表中的content_id_，应该是comment表里面的主键id，我是这么用的。这样评论表就和附件表关联起来了。



