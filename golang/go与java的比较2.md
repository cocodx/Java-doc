##### 定义类
java 直接上关键字class完事，go是用结构体。。。。Java完胜go
小lajier，gogo 变量名放前头，类型放后头，java小哥我不太适应

```java
package org.github.cocodx;

public class Student {

    private Long id;
    private String name;
    private Boolean male;
    private Float score;

    public Student() {
    }

    public Student(Long id, String name, Boolean male, Float score) {
        this.id = id;
        this.name = name;
        this.male = male;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}

```

go

```go
package demo01

import "fmt"

type Student struct {
	//第三列是标记信息
	id    uint    `json:id`
	name  string  `json:name`
	male  bool    `json:male`
	score float64 `json:score`
}

func NewStudent(id uint, name string, male bool, score float64) *Student {
	return &Student{id, name, male, score}
}

func (s Student) GetName() string {
	return s.name
}

func (s Student) GetId() uint {
	return s.id
}

func (s Student) GetMale() bool {
	return s.male
}

func (s Student) GetScore() float64 {
	return s.score
}

func (s *Student) SetName(name string) {
	s.name = name
}
func (s *Student) SetId(id uint) {
	s.id = id
}

// 显示指针
func (s *Student) SetMale(male bool) {
	s.male = male
}
func (s *Student) SetScore(score float64) {
	s.score = score
}

// go版本 toString实现
func (s Student) String() string {
	return fmt.Sprintf("{id: %d, name: %s, male: %t, score: %f }",
		s.id, s.name, s.male, s.score)
}
```
