流控的规则对应的是FlowRule：
有几个重要的属性

* resource:规则的资源名
* grade：限流阈值类型，qps 或线程数
* count：限流的阈值
* limitApp：被限制的应用，授权时候为逗号分隔的应用集合，限流时为单个应用
* strategy：基于调用关系的流量控制
* controlBehavior：流控策略

#### limitApp
* default：表示不区分调用者，任何调用者的请求都进行限流统计
* {origin}:表示针对特定的调用者，只有来自这个调用者的请求才会进行流量控制。ContextUtil.entry(origin)
例如：资源 NodeA 配置了一条针对调用者 caller1 的规则，那么当且仅当来自 caller1 对 NodeA 的请求才会触发流量控制。
* other：表示除 {origin} 以外的其余调用方的流量进行流量控制。

#### strategy


#### 规则丢失
无论是通过硬编码的方式来更新规则，还是通过接入 Sentinel Dashboard 后，在页面上操作来更新规则，都无法避免一个问题，那就是服务重新后，规则就丢失了，因为默认情况下规则是保存在内存中的。