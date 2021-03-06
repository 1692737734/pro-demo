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

 下面上代码
```
/**
 * 用户类
 */
public class User {

    // 下面是“一堆”的属性
    private String name;
    private String password;
    private String nickName;
    private int age;
    //私有化构造方法
    private User(String name, String password, String nickName, int age){
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
    }
    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder{
        private String name;
        private String password;
        private String nickName;
        private int age;

        private UserBuilder(){}
        //链式调用各个属性值，返回this
        public UserBuilder name(String name){
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }
        //build方法负责将UserBuilder中的属性复制到User
        //在复制的同时需要做一些检验
        public User build(){
            if (name == null || password == null) {
                throw new RuntimeException("用户名和密码必填");
            }
            if (age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }
            // 还可以做赋予”默认值“的功能
            if (nickName == null) {
                nickName = name;
            }
            return new User(name, password, nickName, age);
        }
    }
}

```

当前核心为：首先将所有的属性都设置给Builder,然后调用build方法，将这些属性复制给对象

怎么调用：
```
public static void main(String[] args){
    User user = User.builder()
            .name("foo")
            .password("pAss12345")
            .age(25)
            .build();
}
```

>题外话，推荐使用lombok，这样可以节省很多的代码，不过如果使用idea，则需要使用插件，否则会报错

```
//使用Builder作为build
@Builder
//暴露get
@Getter
//暴露toString方法
@ToString
public class NewUser {
    //不能为空
    @NonNull
    private String name;
    @NonNull
    private String password;
    //设置默认值
    @Builder.Default
    private String nickName = "aaa";
    private int age;
}
```

#### 原型模式

原型模式说起来也简单，就是有一个实例，可以基于这个实例产生新的实例，说白了就是克隆

Object 类中有一个 clone() 方法，它用于生成一个新的对象，当然，如果我们要调用这个方法，java 要求我们的类必须先实现 Cloneable 接口，此接口没有定义任何方法，但是不这么做的话，在 clone() 的时候，会抛出 CloneNotSupportedException 异常。

>java 的克隆是浅克隆，碰到对象引用的时候，克隆出来的对象和原对象中的引用将指向同一个对象。通常实现深克隆的方法是将对象进行序列化，然后再进行反序列化。

#### 创建型模式总结

创建型模式说白了就是为了创建对象

1 简单工厂模式最简单

2 工厂模式在简单工厂模式的基础上添加了选择工厂的维度

3 抽象工厂有了产品族的概念

4 单例模式就是为了确保全局使用的是同一个对象，一方面是为了安全考虑，一方面是为了节省资源

5 建造者模式专门对付属性很多的类，让代码更加优美，

6 原型模式一般不怎么应用

### 结构型模式
 
 一般创建型模式是为了创建对象存在，而结构型模式则旨在通过改变代码结构来达到解耦的目的
 ，让代码更加容易扩展
 
 #### 代理模式
 
 代理的存在是为了影藏具体实现类的实现细节而存在的，通常还会用于在真实实现的前后添加一部分逻辑
 
 上代码：
```
public interface CurriculumService {
    Curriculumn makePublicCurriculum();
    Curriculumn makeInsideCurriculum();
}

public class CurriculumServiceImpl implements CurriculumService {
    public Curriculumn makePublicCurriculum() {
        Curriculumn curriculumn = new PublicCurriculum();
        curriculumn.setDesc("公开课");
        return curriculumn;
    }

    public Curriculumn makeInsideCurriculum() {
        Curriculumn curriculumn = new InsideCurriculum();
        curriculumn.setDesc("内部课");
        return curriculumn;
    }
}

// 代理要表现得“就像是”真实实现类，所以需要实现CurriculumService
public class CurriculumServiceProxy implements CurriculumService {
    // 内部一定要有一个真实的实现类，当然也可以通过构造方法注入
    private CurriculumService curriculumService = new CurriculumServiceImpl();
    public Curriculumn makePublicCurriculum() {
        System.out.println("我们马上要创建公开课了");
        Curriculumn curriculumn = curriculumService.makePublicCurriculum();
        //可以做一些增强操作
        System.out.println("公开课创建完成了");
        curriculumn.setPrice(1);
        return curriculumn;
    }

    public Curriculumn makeInsideCurriculum() {
        System.out.println("我们马上要创建内部课了");
        Curriculumn curriculumn = curriculumService.makeInsideCurriculum();
        //可以做一些增强操作
        System.out.println("内部课创建完成了");
        return curriculumn;
    }
}


public static void main(String[] args){
        //实例化代理来进行代理
        CurriculumService curriculumService = new CurriculumServiceProxy();
        curriculumService.makePublicCurriculum();
    } 

```
> 其实说白了，代理模式就是“方法增强”或者“方法包装”，具体的代码模式后面会有具体的文章说明

#### 适配器模式

适配器其实就是有一个接口，但是我们现有的对象都不满足这个类，这个时候就需要加一个适配器来适配

适配器分为三种，分别为 默认适配器模式、对象适配器模式、类适配器模式

##### 默认适配器

我们用java Appache commons-io 包中的 FileAlterationListener 做例子,这个接口定义了很多的
方法，用于对文件以及文件夹进行监控

```
public interface FileAlterationListener {
    void onStart(final FileAlterationObserver observer);
    void onDirectoryCreate(final File directory);
    void onDirectoryChange(final File directory);
    void onDirectoryDelete(final File directory);
    void onFileCreate(final File file);
    void onFileChange(final File file);
    void onFileDelete(final File file);
    void onStop(final FileAlterationObserver observer);
}
```

此接口的一大问题是抽象方法太多了，如果我们要用这个接口，意味着我们要实现每一个抽象方法，如果我们只是想要监控文件夹中的文件创建和文件删除事件，可是我们还是不得不实现所有的方法，很明显，这不是我们想要的。

所以，我们需要下面的一个适配器，它用于实现上面的接口，但是所有的方法都是空方法，这样，我们就可以转而定义自己的类来继承下面这个类即可。

```
public class FileAlterationListenerAdaptor implements FileAlterationListener {

    public void onStart(final FileAlterationObserver observer) {
    }

    public void onDirectoryCreate(final File directory) {
    }

    public void onDirectoryChange(final File directory) {
    }

    public void onDirectoryDelete(final File directory) {
    }

    public void onFileCreate(final File file) {
    }

    public void onFileChange(final File file) {
    }

    public void onFileDelete(final File file) {
    }

    public void onStop(final FileAlterationObserver observer) {
    }
}
```

比如我们可以定义以下类，我们仅仅需要实现我们想实现的方法就可以了：

```
public class FileMonitor extends FileAlterationListenerAdaptor {
    public void onFileCreate(final File file) {
        // 文件创建
        doSomething();
    }

    public void onFileDelete(final File file) {
        // 文件删除
        doSomething();
    }
}

```

##### 对象适配器
对象适配器我们来看一个例子，就是怎么将鸡适配成鸭

```
public interface Duck {
    public void quack(); // 鸭的呱呱叫
      public void fly(); // 飞
}

public interface Cock {
    public void gobble(); // 鸡的咕咕叫
      public void fly(); // 飞
}

public class WildCock implements Cock {
    public void gobble() {
        System.out.println("咕咕叫");
    }
      public void fly() {
        System.out.println("鸡也会飞哦");
    }
}
```

鸭接口有 fly() 和 quare() 两个方法，鸡 Cock 如果要冒充鸭，fly() 方法是现成的，
但是鸡不会鸭的呱呱叫，没有 quack() 方法。这个时候就需要适配了：

```
// 毫无疑问，首先，这个适配器肯定需要 implements Duck，这样才能当做鸭来用
public class CockAdapter implements Duck {
     Cock cock;
  // 构造方法中需要一个鸡的实例，此类就是将这只鸡适配成鸭来用
       public CockAdapter(Cock cock) {
         this.cock = cock;
     }
     // 实现鸭的呱呱叫方法
   @Override
     public void quack() {
       // 内部其实是一只鸡的咕咕叫
       cock.gobble();
   }
  
     @Override
     public void fly() {
       cock.fly();
   }    
}

```
客户端调用很简单了：
```
public static void main(String[] args) {
    // 有一只野鸡
      Cock wildCock = new WildCock();
      // 成功将野鸡适配成鸭
      Duck duck = new CockAdapter(wildCock);
      ...
}
```

到这里，大家也就知道了适配器模式是怎么回事了。无非是我们需要一只鸭，
但是我们只有一只鸡，这个时候就需要定义一个适配器，由这个适配器来充当鸭，
但是适配器里面的方法还是由鸡来实现的。

##### 类适配器模式

![avatar](https://user-gold-cdn.xitu.io/2018/10/19/1668ac9a06512b0c?imageslim)



看到这个图，大家应该很容易理解的吧，通过继承的方法，
适配器自动获得了所需要的大部分方法。这个时候，客户端使用更加简单，
直接 Target t = new SomeAdapter(); 就可以了。

##### 适配器模式的总结

1 类适配和对象适配的异同

> 类适配器采用继承，对象适配器采用组合类适配属于静态实现，对象适配属于组合的动态实现，对象适配需要多实例化一个对象。
                    
>总体来说，对象适配用得比较多。

2 适配器模式和代理模式的异同

比较这两种模式，其实是比较对象适配器模式和代理模式，在代码结构上，它们很相似，
都需要一个具体的实现类的实例。但是它们的目的不一样，

代理模式做的是增强原方法的活；

适配器做的是适配的活，为的是提供“把鸡包装成鸭，然后当做鸭来使用”，
而鸡和鸭它们之间原本没有继承关系。

#### 桥梁模式

理解桥梁模式其实就是理解代码的抽象与解耦

首先我们定义一个桥梁，定义一个接口

```
public interface DrawAPI {
   public void draw(int radius, int x, int y);
}
```

然后定义一系列实现类

```
public class RedPen implements DrawAPI {
   @Override
   public void draw(int radius, int x, int y) {
      System.out.println("用红色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
   }
}
public class GreenPen implements DrawAPI {
   @Override
   public void draw(int radius, int x, int y) {
      System.out.println("用绿色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
   }
}
public class BluePen implements DrawAPI {
   @Override
   public void draw(int radius, int x, int y) {
      System.out.println("用蓝色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
   }
}

```
定义一个抽象类，此类的实现类都需要使用 DrawAPI：

```
public abstract class Shape {
   protected DrawAPI drawAPI;

   protected Shape(DrawAPI drawAPI){
      this.drawAPI = drawAPI;
   }
   public abstract void draw();    
}
```

定义抽象类的子类：

```
public abstract class Shape {
   protected DrawAPI drawAPI;

   protected Shape(DrawAPI drawAPI){
      this.drawAPI = drawAPI;
   }
   public abstract void draw();    
}
复制代码定义抽象类的子类：// 圆形
public class Circle extends Shape {
   private int radius;

   public Circle(int radius, DrawAPI drawAPI) {
      super(drawAPI);
      this.radius = radius;
   }

   public void draw() {
      drawAPI.draw(radius, 0, 0);
   }
}
// 长方形
public class Rectangle extends Shape {
    private int x;
      private int y;

      public Rectangle(int x, int y, DrawAPI drawAPI) {
        super(drawAPI);
          this.x = x;
          this.y = y;
    }
      public void draw() {
      drawAPI.draw(0, x, y);
   }
}
```

最后，我们来看客户端演示：

```
public static void main(String[] args) {
    Shape greenCircle = new Circle(10, new GreenPen());
      Shape redRectangle = new Rectangle(4, 8, new RedPen());

      greenCircle.draw();
      redRectangle.draw();
}
```

![avatar](https://user-gold-cdn.xitu.io/2018/10/19/1668ac9a07119726?imageslim)

其实这里的桥梁就是DrawAPI，无论是画圆还是画方形都需要使用画笔，但是这里画笔存在
不同的实现，那么DrawAPI就是桥梁

#### 装饰模式

理解装饰模式先看一个图

![avatar](https://user-gold-cdn.xitu.io/2018/10/19/1668ac9a076dbb49?imageslim)

从上面的图可以看出，接口Component已经有了ConcreteComponentA 和 ConcreteComponentB
两个实现类了，但是我们要增强这两个实现类的话，就要使用装饰模式了，以达到增强的目的

下面来看一个例子，在这个例子中，红茶，绿茶，咖啡是最基础的饮料，其他的
像金桔柠檬、芒果、珍珠、椰果、焦糖等都属于装饰用的

上代码：
```
//饮料抽象类
public abstract class Beverage {
      // 返回描述
      public abstract String getDescription();
      // 返回价格
      public abstract double cost();
}
```

然后是三个基础饮料实现类，红茶、绿茶和咖啡：

```
public class BlackTea extends Beverage {
  public String getDescription() {
    return "红茶";
   }
      public double cost() {
        return 10;
    }
}
public class GreenTea extends Beverage {
    public String getDescription() {
        return "绿茶";
    }
      public double cost() {
        return 11;
    }
}
...// 咖啡省略

```

定义调料，也就是装饰者的基类，此类必须继承自 Beverage：

```
public abstract class Condiment extends Beverage {

}

```

然后我们来定义柠檬、芒果等具体的调料，它们属于装饰者，毫无疑问，这些调料肯定都需要继承 Condiment 类：

```
public class Lemon extends Condiment {
    private Beverage bevarage;
      // 这里很关键，需要传入具体的饮料，如需要传入没有被装饰的红茶或绿茶，
      // 当然也可以传入已经装饰好的芒果绿茶，这样可以做芒果柠檬绿茶
      public Lemon(Beverage bevarage) {
        this.bevarage = bevarage;
    }
      public String getDescription() {
        // 装饰
        return bevarage.getDescription() + ", 加柠檬";
    }
      public double cost() {
          // 装饰
        return beverage.cost() + 2; // 加柠檬需要 2 元
    }
}
public class Mango extends Condiment {
    private Beverage bevarage;
      public Mango(Beverage bevarage) {
        this.bevarage = bevarage;
    }
      public String getDescription() {
        return bevarage.getDescription() + ", 加芒果";
    }
      public double cost() {
        return beverage.cost() + 3; // 加芒果需要 3 元
    }
}
...// 给每一种调料都加一个类

```
看客户端调用：

```
public static void main(String[] args) {
      // 首先，我们需要一个基础饮料，红茶、绿茶或咖啡
    Beverage beverage = new GreenTea();
      // 开始装饰
      beverage = new Lemon(beverage); // 先加一份柠檬
      beverage = new Mongo(beverage); // 再加一份芒果

      System.out.println(beverage.getDescription() + " 价格：￥" + beverage.cost());
      //"绿茶, 加柠檬, 加芒果 价格：￥16"
}
```


#### 门面模式

门面模式也叫外观模式，在许多的源码中有使用，比如 slf4j 就可以理解为是门面模式的应用。这是一个简单的设计模式，我们直接上代码再说吧。

上代码：
```
public interface Shape {
    void draw();
}


public class Circle implements Shape{

    public void draw() {
        System.out.println("Circle::draw()");
    }
}

public class Rectangle implements Shape {
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}

public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
    }

    /**
     * 下面定义一堆方法，具体应该调用什么方法，由这个门面来决定
     */

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }

}

public static void main(String[] args) {
    ShapeMaker shapeMaker = new ShapeMaker();

    // 客户端调用现在更加清晰了
    shapeMaker.drawCircle();
    shapeMaker.drawRectangle();
}

```
门面模式的优点显而易见，客户端不再需要关注实例化时应该使用哪个实现类，
直接调用门面提供的方法就可以了，因为门面类提供的方法的方法名对于客户端
来说已经很友好了。
                   
#### 组合模式

组合模式用于表示具有层次结构的数据，使得我们对单个对象和组合对象的访问具有一致性。

```
public class Employee {
   private String name;
   private String dept;
   private int salary;
   private List<Employee> subordinates; // 下属

   public Employee(String name,String dept, int sal) {
      this.name = name;
      this.dept = dept;
      this.salary = sal;
      subordinates = new ArrayList<Employee>();
   }

   public void add(Employee e) {
      subordinates.add(e);
   }

   public void remove(Employee e) {
      subordinates.remove(e);
   }

   public List<Employee> getSubordinates(){
     return subordinates;
   }

   public String toString(){
      return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary+" ]");
   }   
}

```   

通常，这种类需要定义 add(node)、remove(node)、getChildren() 这些方法。

#### 享元模式
英文是 Flyweight Pattern，不知道是谁最先翻译的这个词，
感觉这翻译真的不好理解，我们试着强行关联起来吧。Flyweight 是轻量级的意思，
享元分开来说就是 共享 元器件，也就是复用已经生成的对象，
这种做法当然也就是轻量级的了。复用对象最简单的方式是，
用一个 HashMap 来存放每次新生成的对象。每次需要一个对象的时候，
先到 HashMap 中看看有没有，如果没有，再生成新的对象，
然后将这个对象放入 HashMap 中。

#### 结构模式总结

1 代理模式是做方法增强的

2 适配器模式是把鸡包装成鸭

3 桥梁模式是来做解耦的

4 装饰模式 适合做增强类的场景

5 门面模式的优点的客户端不需要关注实例化的细节，只要调用方法就好了

6 组合模式用于描述具有层次结构的数据

7 享元模式是为了在特定的场景中缓存已经创建的对象，用于提高性能。

### 行为模式

行为型模式关注的是各个类之间的相互作用，将职责划分清楚，使得我们的代码更加地清晰。

#### 策略模式

上代码：

先定义一个策略类
```
public interface Strategy {
   public void draw(int radius, int x, int y);
}
```

然后我们定义具体的几个策略：

```
public class RedPen implements Strategy {
   @Override
   public void draw(int radius, int x, int y) {
      System.out.println("用红色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
   }
}
public class GreenPen implements Strategy {
   @Override
   public void draw(int radius, int x, int y) {
      System.out.println("用绿色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
   }
}
public class BluePen implements Strategy {
   @Override
   public void draw(int radius, int x, int y) {
      System.out.println("用蓝色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
   }
}

```

使用策略的类：
```
public class Context {
   private Strategy strategy;

   public Context(Strategy strategy){
      this.strategy = strategy;
   }

   public int executeDraw(int radius, int x, int y){
      return strategy.draw(radius, x, y);
   }
}

```
客户端演示：
```
public static void main(String[] args) {
    Context context = new Context(new BluePen()); // 使用绿色笔来画
      context.executeDraw(10, 0, 0);
}
```

放到一张图上，让大家看得清晰些：
![avatar](https://user-gold-cdn.xitu.io/2018/10/19/1668ac9a07775812?imageslim)


这个时候，大家有没有联想到结构型模式中的桥梁模式，它们其实非常相似，我把桥梁模式的图拿过来大家对比下：


![avatar](https://user-gold-cdn.xitu.io/2018/10/19/1668ac9a07119726?imageslim)

要我说的话，它们非常相似，桥梁模式在左侧加了一层抽象而已。桥梁模式的耦合更低，结构更复杂一些。

#### 观察者模式

对于观察者模式来说， 就是两个操作，观察者订阅自己关心的主题和主题有数据变化后通知观察者们。

首先，需要定义主题，每个主题需要持有观察者列表的引用，用于在数据变更的时候通知各个观察者：

```
public class Subject {

   private List<Observer> observers = new ArrayList<Observer>();
   private int state;

   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
      // 数据已变更，通知观察者们
      notifyAllObservers();
   }

   public void attach(Observer observer){
      observers.add(observer);        
   }

   // 通知观察者们
   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   }     
}

```

定义观察者接口：

```
public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}
```

其实如果只有一个观察者类的话，接口都不用定义了，不过，通常场景下，既然用到了观察者模式，
我们就是希望一个事件出来了，会有多个不同的类需要处理相应的信息。比如，订单修改成功事件，
我们希望发短信的类得到通知、发邮件的类得到通知、处理物流信息的类得到通知等。

我们来定义具体的几个观察者类：

```
public class BinaryObserver extends Observer {

      // 在构造方法中进行订阅主题
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        // 通常在构造方法中将 this 发布出去的操作一定要小心
        this.subject.attach(this);
    }

      // 该方法由主题类在数据变更的时候进行调用
    @Override
    public void update() {
        String result = Integer.toBinaryString(subject.getState());
        System.out.println("订阅的数据发生变化，新的数据处理为二进制值为：" + result);
    }
}

public class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
          String result = Integer.toHexString(subject.getState()).toUpperCase();
        System.out.println("订阅的数据发生变化，新的数据处理为十六进制值为：" + result);
    }
}


```

客户端使用也非常简单：

```

public static void main(String[] args) {
    // 先定义一个主题
      Subject subject1 = new Subject();
      // 定义观察者
      new BinaryObserver(subject1);
      new HexaObserver(subject1);

      // 模拟数据变更，这个时候，观察者们的 update 方法将会被调用
      subject.setState(11);
}


```


#### 责任链模式

责任链通常需要先建立一个单向链表，然后调用方只需要调用头部节点就可以了，后面会自动流转下去。
比如流程审批就是一个很好的例子，只要终端用户提交申请，根据申请的内容信息，自动建立一条责任链，
然后就可以开始流转了。

有这么一个场景，用户参加一个活动可以领取奖品，但是活动需要进行很多的规则校验然后才能放行，
比如首先需要校验用户是否是新用户、今日参与人数是否有限额、全场参与人数是否有限额等等。
设定的规则都通过后，才能让用户领走奖品。

首先，我们要定义流程上节点的基类：

```
public abstract class RuleHandler {

      // 后继节点
    protected RuleHandler successor;

    public abstract void apply(Context context);

    public void setSuccessor(RuleHandler successor) {
        this.successor = successor;
    }
    public RuleHandler getSuccessor() {
        return successor;
    }
}


```

校验用户是否是新用户：

```
public class NewUserRuleHandler extends RuleHandler {

    public void apply(Context context) {
        if (context.isNewUser()) {
              // 如果有后继节点的话，传递下去
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("该活动仅限新用户参与");
        }
    }

}

```

校验用户所在地区是否可以参与：

```
public class LocationRuleHandler extends RuleHandler {
    public void apply(Context context) {
        boolean allowed = activityService.isSupportedLocation(context.getLocation);
          if (allowed) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else  {
            throw new RuntimeException("非常抱歉，您所在的地区无法参与本次活动");
        }
    }
}

```

校验奖品是否已领完：
```
public class LimitRuleHandler extends RuleHandler {
    public void apply(Context context) {
          int remainedTimes = activityService.queryRemainedTimes(context); // 查询剩余奖品
        if (remainedTimes > 0) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(userInfo);
            }
        } else {
            throw new RuntimeException("您来得太晚了，奖品被领完了");
        }
    }
}


```
客户端：

```
public static void main(String[] args) {
    RuleHandler newUserHandler = new NewUserRuleHandler();
      RuleHandler locationHandler = new LocationRuleHandler();
      RuleHandler limitHandler = new LimitRuleHandler();

      // 假设本次活动仅校验地区和奖品数量，不校验新老用户
      locationHandler.setSuccessor(limitHandler);
      locationHandler.apply(context);
}


```
代码其实很简单，就是先定义好一个链表，然后在通过任意一节点后，如果此节点有后继节点，那么传递下去。
             
#### 模板方法模式

在含有继承结构的代码中，模板方法模式是非常常用的，这也是在开源代码中大量被使用的。

通常会有一个抽象类：

```
public abstract class AbstractTemplate {
    // 这就是模板方法
      public void templateMethod(){
        init();
        apply(); // 这个是重点
        end(); // 可以作为钩子方法
    }
    protected void init() {
        System.out.println("init 抽象层已经实现，子类也可以选择覆写");
    }
      // 留给子类实现
    protected abstract void apply();
    protected void end() {
    }
}

``` 
模板方法中调用了 3 个方法，其中 apply() 是抽象方法，子类必须实现它，
其实模板方法中有几个抽象方法完全是自由的，我们也可以将三个方法都设置为抽象方法，
让子类来实现。也就是说，模板方法只负责定义第一步应该要做什么，第二步应该做什么，
第三步应该做什么，至于怎么做，由子类来实现。


我们写一个实现类：

```
public class ConcreteTemplate extends AbstractTemplate {
    public void apply() {
        System.out.println("子类实现抽象方法 apply");
    }
      public void end() {
        System.out.println("我们可以把 method3 当做钩子方法来使用，需要的时候覆写就可以了");
    }
}
```

客户端调用演示：

```
public static void main(String[] args) {
    AbstractTemplate t = new ConcreteTemplate();
      // 调用模板方法
      t.templateMethod();
}
```


#### 状态模式

我们说一个简单的例子。商品库存中心有个最基本的需求是减库存和补库存，我们看看怎么用状态模式来写。

核心在于，我们的关注点不再是 Context 是该进行哪种操作，而是关注在这个 Context 会有哪些操作。

定义状态接口：

```
public interface State {
   public void doAction(Context context);
}
```

定义减库存的状态：

```
public class DeductState implements State {

   public void doAction(Context context) {
      System.out.println("商品卖出，准备减库存");
      context.setState(this);

      //... 执行减库存的具体操作
   }

   public String toString(){
      return "Deduct State";
   }
}


```
定义补库存状态：

```
public class RevertState implements State {
    public void doAction(Context context) {
        System.out.println("给此商品补库存");
          context.setState(this);

          //... 执行加库存的具体操作
    }
      public String toString() {
        return "Revert State";
    }
}

```    
                  
前面用到了 context.setState(this)，我们来看看怎么定义 Context 类：

```
public class Context {
    private State state;
      private String name;
      public Context(String name) {
        this.name = name;
    }

      public void setState(State state) {
        this.state = state;
    }
      public void getState() {
        return this.state;
    }
}
```                    
                    
我们来看下客户端调用，大家就一清二楚了：

```
public static void main(String[] args) {
    // 我们需要操作的是 iPhone X
    Context context = new Context("iPhone X");

    // 看看怎么进行补库存操作
      State revertState = new RevertState();
      revertState.doAction(context);

    // 同样的，减库存操作也非常简单
      State deductState = new DeductState();
      deductState.doAction(context);

      // 如果需要我们可以获取当前的状态
    // context.getState().toString();
}
```

在上面这个例子中，如果我们不关心当前 context 处于什么状态，那么 Context 就可以不用维护 state 属性了，那样代码会简单很多。

不过，商品库存这个例子毕竟只是个例，我们还有很多实例是需要知道当前 context 处于什么状态的。


#### 行为型模式总结
行为模式包括：策略模式、观察者模式、责任链模式、模板方法模式以及状态模式

其实，经典的行为型模式还包括备忘录模式、命令模式等，但是它们的使用场景比较有限
