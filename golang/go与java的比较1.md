##### 观察1
java开发时以.java结尾的。

go开发时以.go结尾的。

##### 观察2
java在
![image](https://user-images.githubusercontent.com/97614802/184296004-a86aa7ea-ddd7-46ef-bb94-e697c1bdd092.png)

```java
public class Test {

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
```

go是上层文件夹叫啥，package就是啥

![image](https://user-images.githubusercontent.com/97614802/184296172-fd9c5b23-ce67-47de-a2e4-cbb6fee866bd.png)

```go
package main

import "fmt"

func main() {

	fmt.Println("Hello, World!")

}

```

##### 观察3
java定义变量是，把类型关键字放到开头，而go是用var去定义，再在变量名后头标识类型，或者直接不写类型，自动识别。

java可以在方法里面，定义局部变量，可以在类里定义【go也可以】。不用也不会报错。但是go会报错

```java
public class Test {

  Long ac = 100L;
  Integer bc = 100;

  public static void main(String[] args) {
      Long ac = 100L;
      Integer bc = 100;
      Double db = 100.23D;
      String ab = "123456";
  }
}

```

go
```go
package main

import "fmt"

var ab, bc = "123456", "7890"

func main() {
	// %d 表示整型数字，%s 表示字符串
	var stockcode = 12345
	var enddata = "2022-08-12"
	var url = "Code=%d&endDate=%s"
	var target_url = fmt.Sprintf(url, stockcode, enddata)
	fmt.Println(target_url)

	var a string = "caijier"
	var b, c int = 1, 2
	fmt.Println(a, b, c)
	fmt.Println(ab + bc)
}
```

