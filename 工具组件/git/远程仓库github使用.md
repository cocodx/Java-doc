```java
$ git remote add github(远端别名) xxxxxxxxxxxxxx.git(远端地址)
$ git fetch github master
$ git checkout master
$ git merge --allow-unrelated-histories github/master
$ git push github master

```