dom优点：
    1、形成了树结构，有助于更好的理解、掌握，且代码容器编写
    2、解析过程中，树结构保存在内存中，方便修改。（tomcat不需要改配置文件，鸡肋）
    缺点：
    1、由于文件时一次性读取，所以对内存耗费比较大（tomcat作为容器，必须追求性能，肯定不能太耗内存）
    2、如果XML文件比较大，容易影响解析性能可能会造成内存溢出。

sax优点：
    1、采用事件驱动模型，对内存耗费比较小。正好适合tomcat
    2、适用于只读取不修改XML文件中的数据时。适合tomcat，不需要修改配置屋内按，只需要读取并处理
    缺点：
    1、编码比较麻烦
    2、很难同时访问xml文件中的多处不同数据。要访问的话，只能自己搞个field存起来，hashmap。