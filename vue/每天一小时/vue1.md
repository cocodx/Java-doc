#### 在一个vue标准应用中，创建模板

如下图，创建组件文件夹

![image](https://user-images.githubusercontent.com/97614802/207568959-e3613cc7-7328-4661-8f68-d811b5cd30a9.png)

##### 在模板定义本地变量

如下的代码，在data(){ return { "在这里定义变量" } }

```vue
<script>
export default {
  name:'Test1Vue',
  data(){
    return {
      count:0,
      msg:"I want to handle foreigner language",
      rawHtml:'<span style="color: red">This should be red.</span>',
      dynamicId:123,
      isButtonDisabled:true,
      objectOfAttrs:{
        id:'container1',
        class:'wrapper'
      }
    }
  }
}
</script>
```

##### 在vue模板中，使用变量

1. 使用双大括号去进行显示变量 {{  }}
2. 使用v-html指令，输出html代码
3. 使用'v-bind:属性名'，去绑定属性，简写':'
4. 使用:disabled，去设置按钮是否可点击
5. 也可以使用'v-bind:属性名',去绑定对象

如下vue的temaplte标签中，html代码，对应前面的指令使用
```html
<template>
  <div>
    <button @click="count++">{{ count }}</button>
    <span>Message: {{ msg }}</span>
    <p>Using text interpolation: <span v-html="rawHtml"></span></p>
    <div v-bind:id="dynamicId">123</div>
    <button :disabled="isButtonDisabled">Button</button>
    <div v-bind="objectOfAttrs">绑定对象
      <input v-bind:name="objectOfAttrs.id">
    </div>
  </div>
</template>
```

