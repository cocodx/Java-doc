#### golang读取控制输入字符

getting inputted data in the teminal by user

```java
reader :=bufio.NewReader(os.Stdin)

//this row code means it will read you inputed stuff until you pressed the enter key
userInput, _ = reader.ReadString('\n')

//this used to in window system .the string will take \r .when you pressed enter button
userInput = strings.TrimSuffix(userInput,"\r\n")

//this used to in linux system .the string only take \n .when you pressed enter button
userInput = strings.TrimSuffix(userInput,"\n")


```
