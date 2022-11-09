#### poi处理从第几行开始导入

万物皆是对象，先把xls，或者xlsx转成workbook，workbook再去获取sheet，操作sheet的api去导入

```java
//从第0行到第”whichline“行 清空
try {
    int whichLine = 1;
    whichLine--;
    FileInputStream is = new FileInputStream("C:\\Users\\cocodx\\Desktop\\1.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(is);
    XSSFSheet sheetAt = workbook.getSheetAt(0);
    while (whichLine > -1) {
        XSSFRow row = sheetAt.getRow(whichLine);
        sheetAt.removeRow(row);
        whichLine--;
    }
    FileOutputStream os = new FileOutputStream("C:\\Users\\cocodx\\Desktop\\5.xlsx");
    workbook.write(os);
    is.close();
    os.close();
} catch (Exception e) {
    e.printStackTrace();
}
```
