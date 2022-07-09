1. 保证可见性
1. 不保证原子性
1. 禁止指令重排

多线程环境中可以使用volatile，避免指令重排

相当于内存屏障，指令前后cpu不能重排，刷出cpu缓存

cpu指令 storestore+storeload\loadload


