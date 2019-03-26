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


#### 工厂模式
假如简单工厂模式已经符合我们的要求了，我们也就不用捣腾了，之所以需要工厂模式，是我们
可能需要多个不同的工厂，比如课程存在公开课以及内部课，在公开课存在模式A和模式B,内部课
也存在模式A与模式B,这个时候就需要工厂模式来解决类的创建了

上代码
```
/**
 * 课程工厂接口
 */
public interface CurriculumFactory {
    Curriculumn makeCurriculumn(String name);
}

/**
 * 公开课工厂
 */
public class PublicCurriculumFactory implements CurriculumFactory {
    public Curriculumn makeCurriculumn(String name) {
        if (name.equals("A")) {
            Curriculumn curriculumn =  new PublicCurriculumA();
            curriculumn.setDesc("公开课A");
            return curriculumn;
        } else if (name.equals("B")) {
            Curriculumn curriculumn =  new PublicCurriculumB();
            curriculumn.setDesc("公开课B");
            return curriculumn;
        } else {
            return null;
        }

    }
}

/**
 * 内部课工厂
 */
public class InsideCurriculumFactory implements CurriculumFactory {
    public Curriculumn makeCurriculumn(String name) {
        if (name.equals("A")) {
            Curriculumn curriculumn =  new InsideCurriculumA();
            curriculumn.setDesc("内部课A");
            return curriculumn;
        } else if (name.equals("B")) {
            Curriculumn curriculumn =  new InsideCurriculumB();
            curriculumn.setDesc("内部课B");
            return curriculumn;
        } else {
            return null;
        }
    }
}

```

这里主要是存在了不同的工厂，我们所要做的就是选择正确的工厂，使用方法为：

```
public static void main(String[] args){
    CurriculumFactory curriculumFactory = new PublicCurriculumFactory();
    Curriculumn curriculumn = curriculumFactory.makeCurriculumn("B");
    System.out.println(curriculumn.getDesc());
}
```

这里选取了公开课的工厂进行实例化工厂，然后通过实例化的工厂去获取对应的课程类

#### 抽象工厂模式

有一个场景就是涉及到产品族的时候，需要用到抽象工厂模式，

抽象工厂模式有一个很明显的缺陷，就是需要添加一个内容的时候，需要修改所有的工厂方法
违反了对修改关闭，对扩展开放的原则，所以，一般不使用，这里也不做详细的介绍


#### 单例模式

饿汉模式--简单

上代码
```
public class Singleton {
    // 首先，将 new Singleton() 堵死
    private Singleton() {};
    // 创建私有静态实例，意味着这个类第一次使用的时候就会进行创建
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
    public static Date getDate(String mode) {return new Date();}
}
```

饱汉模式 -- 容易出错

```
public class Singleton {
    // 首先，也是先堵死 new Singleton() 这条路
    private Singleton() {}
    // 和饿汉模式相比，这边不需要先实例化出来，注意这里的 volatile，它是必须的
    private static volatile Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            // 加锁
            synchronized (Singleton.class) {
                // 这一次判断也是必须的，不然会有并发问题
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

>双重检查，指的是两次检查 instance 是否为 null。
 
 >volatile 在这里是需要的，希望能引起读者的关注。
 
 >很多人不知道怎么写，直接就在 getInstance() 方法签名上加上 synchronized，这就不多说了，性能太差。
 
 嵌套类--最经典
 
 ```
public class Singleton3 {

    private Singleton3() {}
    // 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder {
        private static Singleton3 instance = new Singleton3();
    }
    public static Singleton3 getInstance() {
        return Holder.instance;
    }
}
```

#### 建造者模式

我们在代码中可以看到XXXBuilder,说明这里用到了建造者模式，一般我们使用的是

```
Curriculumn curriculum = new CurriculumnBuilder.a().b().c().build();
Curriculumn curriculum = Curriculum.builder().a().b().c().build();
```

 