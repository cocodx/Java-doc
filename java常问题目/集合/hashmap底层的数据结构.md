HashMap<String,String> map = new HashMap<>();
map.put("张三","测试数据");
map.put("李四","测试数据");

底层核心的数据结构是数组。
对张三计算出来的一个hash值，根据这个hash值对数组进行取模，就会定位到数组里的一个元素里去。

假设可以放16个元素，取模，index
array[4] = <"张三","测试数据">

map.get("张三") -> hash值 -> 对数组长度进行取模 -> return array[4]