#### GoLang的编译

1. 有了go源码，可以通过编译器编译成机器的二进制码文件
2. 再该源码文件目录下，通过go build对hello.go文件进行编译，可以指定生成的可执行文件名（windows是exe，linux是可执行文件），在windows下，必须是exe后缀
3. 如果没有错误，没有任何提示
4. 如果有错误，编译时，会在错误的那一行报错

```go
#打包
go build -o myHello.exe
```
