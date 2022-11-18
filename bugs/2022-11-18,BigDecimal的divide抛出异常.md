#### BigDecimal使用divide运算，不指定模式，会导致抛出异常

Non-terminating decimal expansion； no exact representable decimal result

```java
public BigDecimal divide(BigDecimal divisor, RoundingMode roundingMode) {
    return this.divide(divisor, scale, roundingMode.oldMode);
}
```

RoundingMode 对象提供了以下八种舍入模式：
|常量名   | 说明|
|-|-|
|CEILING |   向正无限大方向舍入。|
DOWN    向零方向舍入。
FLOOR    向负无限大方向舍入。
HALF_DOWN    向最接近数字方向舍入，如果与两个相邻数字的距离相等，则向下舍入。
HALF_EVEN    向最接近数字方向舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
HALF_UP    向最接近数字方向舍入，如果与两个相邻数字的距离相等，则向上舍入。
UNNECESSARY    断言具有精确结果。
