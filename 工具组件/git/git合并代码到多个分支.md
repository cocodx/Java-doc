#### 做到的需求，要在多个分支合并
🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈🙈
肯定是已经有提交的记录了，那么找到这个commitId，比如我举个我这的例子
![image](https://user-images.githubusercontent.com/97614802/175529033-e843039c-f32e-442b-9ee3-ce1612e031a5.png)

切换到这个分支去看git log
```java
git log feature-contract-import-support1000
```
![image](https://user-images.githubusercontent.com/97614802/175529198-433489d2-2b52-4382-ac09-9f4f4b8381b2.png)
复制粘贴前8位就好了 508e4a2f72

然后找到你要提交的远程分支，prod，master，test
比如test，拉到本地

1，先查看远程分支
```java
git branch -a
```
![image](https://user-images.githubusercontent.com/97614802/175529436-edaba9f1-1915-4ce8-8d94-4039ce4716af.png)

2，拉到本地分支
```java
git checkout -b 本地新建的分支名 origin/线上分支名
```
git checkout -b test remotes/origin/test

那么现在在这个分支了，就cherry-pick摘樱桃
```java
git cherry-pick 508e4a2f72
```

遇到冲突不要慌，结合idea，在idea看代码
解决冲突，再提交到远程分支

