#### session cookie
session是HttpSession，用户通过浏览器发送http请求，发送的数据存到session里面，是位于服务端保存。

cookie是位于客户端，在浏览器保存的
![image](https://user-images.githubusercontent.com/97614802/182563890-6e45215b-ac8e-4a01-8e2e-cce6d766b70c.png)
F12，选择Application，可以看到，不同的域名，对应着一个cookie，每个单独的网站都有一个cookie，用来存放一些用户的相关数据。

在HttpServletResponse中添加cookie，设置最大有效时间(单位为秒)

```java
Cookie cookie = new Cookie("user",userName+"-"+password);
cookie.setMaxAge(1*60*15);
resp.addCookie(cookie);
```

![image](https://user-images.githubusercontent.com/97614802/182573622-77040c03-dad2-4896-bd51-8f7673b72d6c.png)

