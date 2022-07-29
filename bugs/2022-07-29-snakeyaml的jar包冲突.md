执行测试代码报错，发现snakeyaml包，看起来有两个依赖，但是版本不同引起的。
![微信图片_20220729155218](https://user-images.githubusercontent.com/97614802/181712255-8b24762c-7d06-4df8-bf1c-1b81e199d902.jpg)


然后在web项目里，show-dependences。找到了snakeyaml的android依赖。然后使用excutions去掉这个依赖就好了。
![微信图片_20220729155310](https://user-images.githubusercontent.com/97614802/181712308-c7c2a6bf-a4e2-4f9d-953c-0ee1c4a275e7.jpg)

![image](https://user-images.githubusercontent.com/97614802/181712457-b3c874d9-8056-4981-90fe-962c4338837f.png)

