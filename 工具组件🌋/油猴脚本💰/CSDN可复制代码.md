

```javascript
let codes  = document.querySelectorAll("code");
codes.forEach(c => {
    //设置代码块可以编辑，从而实现复制
    c.contentEditable = "true";
})

```