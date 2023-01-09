#### 正常写法1

```go
sum := 0
for bi := 0; bi <= 10; bi++ {
  sum += bi
}
fmt.Println(sum)
```

#### 正常写法2

```go
sum := 1
for sum <= 10 {
  sum += sum
}
fmt.Println(sum)
```

#### 无限循环
```go
sum := 1
for {
  sum++
}
fmt.Println(sum)
```
