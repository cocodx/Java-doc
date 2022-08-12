##### 观察1
java与go都可以定义常量。java是用final。go是用const，哎呀go老弟啥时候跟js一样了。
go还有iota的骚玩法，真牛逼，下次再研究

```java
public class Test {
    
  private final Integer aaaa=1000;
  private final String name;

  public static void main(String[] args) {
      final Integer abc = 1000;
  }

  public Test(String name) {
      this.name = name;
  }
}
```


```go
const hahaha = "trump"
```

##### go语言运算符跟java老哥没啥区别啊

##### go的if没中括号

> if else if

```go
if b < 100 {
		fmt.Println("b小于100")
	}else if b == 100 {
		fmt.Println("b等于100")
	}
	fmt.Println("b大于100")
```

> if嵌套

```go
if b < 100 {
		if c < 100 {
			fmt.Println("大家都小于100，哈哈，一起摆烂")
		}
	}
```

##### switch

go老弟的switch跟java老哥的差不多用法
```go
	var grade = "C"
	switch {
	case grade == "A":
		fmt.Println("牛逼，铁子")
	case grade == "B":
		fmt.Println("还行吧，老铁")
	case grade == "C":
		fmt.Println("小垃圾儿")
	case grade == "D":
		fmt.Println("菜狗")
	}
```
