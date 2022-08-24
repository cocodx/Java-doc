#### ngrok
使用githhub账号登录ngrok。

然后下载ngrok压缩包解压，解压到一个空白目录

进入到ngrok解压的目录，cmd，输入以下命令
```shell
ngrok config add-authtoken {your token}
```

然后，输入要代理的本机端口

```shell
ngrok http 8802
```

![image](https://user-images.githubusercontent.com/97614802/186348717-85f930bb-b3ce-4f2b-9a6f-312fdd721c4c.png)
