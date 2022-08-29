1. '*':xin号表示匹配html中所有元素（注意：*号的性能差，因为它要匹配所有元素，所以在开发时，不建议使用）

1. 标签匹配器：用来匹配对应的标签 
1. 类选择器：用来选择class命名的标签
1. ID选择器：用来选择用ID命令的标签
1. 派生选择器：根据上下文来确定选择的标签
1. 伪类选择器：使用频繁


```html
/**
标签选择器
    */
span{
    display: block;
    margin-right: 20px;
    border: 1px solid gray;
}

/**
id选择器
    */
#content{
    color: pink;
}

/**
派生选择器
    */
.box2 li{
    color: orange;
}
```

#### 选择器分组及样式继承
让多个选择器多个元素具有相同的样式，一般用于设置公共样式

##### 样式权重
!important(10000) > 内链样式(1000) > ID选择器(100) > 类、伪类选择器(10) > 标签选择器(1) 

##### css字体
1. font-size：字号
1. font-family：字体
1. font-style：文字样式normarl italic oblique
1. font-weight：文字加粗normal bold bolder ligther 100-900
1. line-height：行高px 数字 em
1. color：文字的颜色
1. text-decoration：文字修饰normal underline overline line-through
1. text-align：文本对齐方式 left right center
1. text-transform：字母大小写：capitalize uppercase lowercase none
1. text-indent：文本缩进px em % pt

##### 背景色
