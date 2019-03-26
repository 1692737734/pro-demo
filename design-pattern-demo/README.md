# java设计模式

## 为什么要用设计模式
有时候可能你会觉得，明明是很简单的事情，为什么要搞的那么复杂，设计模式给人的第一感觉
就是代码比原先变得复杂

但是随着软件开发的深入，你会觉得这个复杂恰恰就是设计模式的精髓，所谓的简单往往是一把锁开
一个钥匙，而设计模式的目的是要把这个钥匙变成万能钥匙

当然，有时候不能为了用设计模式而用设计模式，应该根据具体的需求来确定我们是否需要使用设计模式

## 设计原则

1 面向接口编程，而不是面向实现。

2 职责单一原则。每个类都应该只有一个单一的功能，并且该功能应该由这个类完全封装起来。

3 对修改关闭，对扩展开放。对修改关闭是说，我们辛辛苦苦加班写出来的代码，该实现的功能和该修复的 bug 都完成了，别人可不能说改就改；对扩展开放就比较好理解了，也就是说在我们写好的代码基础上，很容易实现扩展。

## 设计模式分类

设计模式按照具体功能划分可以分为：创建型模式、结构型模式、行为型模式

### 创建型模式

什么是创建型模式，简而言之，所谓创建型模式就是用来创建对象的，其实对于创建对象，
我们最熟悉的无非就是new一个对象，然后使用set相关的属性，但是，其实这种方式对于
客户端来说并不友好，我们需要一种更友好的创建对象的方式


#### 简单工厂模式
直接上代码
```
/**
 * 简单工厂模式
 * 课程工厂
 */
public class CurriculumFactory {
    public static Curriculumn makeCurriculum(String name){
        if(name.equals("public")){
            Curriculumn curriculumn = new PublicCurriculum();
            curriculumn.setDesc("公开课");
            return curriculumn;
        }
        if(name.equals("inside")){
            Curriculumn curriculumn = new InsideCurriculum();
            curriculumn.setDesc("内部课");
            return curriculumn;
        }
        return  null;
    }
}
```


这就是简单工厂，一个工厂类，一个静态方法，根据我们传入的参数的不同，可以产生同一个
父类的不同子类


