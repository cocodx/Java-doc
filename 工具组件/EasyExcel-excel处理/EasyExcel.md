maven依赖引入  
```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>easyexcel</artifactId>
    <version>2.1.1</version>
</dependency>
```
在实体类中，把属性标上对应的列表头对应的名称  
```
@Data
public class DemoData {

    @ExcelProperty("学生编号")
    private int sno;

    @ExcelProperty("学生姓名")
    private String name;
}
```
准备写入excel数据的方法 
```
private static List<DemoData> data(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setName("张三"+i);
            list.add(demoData);
        }
        return list;
    }
``` 
####写入excel
#####写入方法1
```
@Test
    public void writeTest1(){
        String fileName = "F:\\11.xlsx";
        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(data());
    }
```
#####写入方法2
```
@Test
    public void writeTest2(){
        //要手动关闭流
        String fileName = "F:\\22.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName,DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("学生列表").build();
        excelWriter.write(data(),writeSheet);
        //关闭流
        excelWriter.finish();
    }
```
####读取excel
#####1、列固定，行不固定  
#####2、修改类实体
```
@Data
public class DemoData {

    //index是索引号，进行绑定
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;

    @ExcelProperty(value = "学生姓名",index = 0)
    private String name;
}
```
#####3、创建Excel监听器
```
//创建读取excel监听器
public class ExcelListener extends AnalysisEventListener<DemoData> {

    //读取每一行的数据
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println(demoData);
    }

    //数据读取完做的事情
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
```
#####4、开始读取
```
@Test
    public void readTest(){
        String fileName = "F:\\11.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }
```
