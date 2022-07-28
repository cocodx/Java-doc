前置条件：对于已经登录的用户

授权方式
1. 在shiro.ini配置文件中配置，用户的权限信息
1. 自定义realm中，用户是如何关联权限信息的，实现doGetAuthorizationInfo

```xml
[users]
root=123456,roles1,roles2
# 账号是root，密码是123456，具有的角色是role1，role2

[roles]
roles1=user:create,user:update
roles2=user:query,user:create
roles3=user:delete,user:query
#角色具有的权限
```