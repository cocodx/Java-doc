#### 报错

Null key returned for cache operation (maybe you are using named params on classes without debug info?) Builder[public java.lang.String com.gwm.service.impl.TestService.selectById(java.io.Serializable)] caches=[hour] | key='#id' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'

对应代码

![image](https://user-images.githubusercontent.com/97614802/234160471-bb1a22dc-9d7a-4644-a994-89e1d6252b1f.png)

vo.getCompanyId()导致



