### 在涉及重载的上下文里，将匿名类转换为Lambda表达式可能导致最终的代码更加晦涩
```
interface Task{ 
 public void execute(); 
} 
public static void doSomething(Runnable r){ r.run(); } 
public static void doSomething(Task a){ a.execute(); }

doSomething(new Task() { 
 public void execute() { 
 System.out.println("Danger danger!!"); 
 } 
});

doSomething(() -> System.out.println("Danger danger!!"));

doSomething((Task)() -> System.out.println("Danger danger!!"));

```

### 代码灵活性
1. 有条件得延迟执行
```
if (logger.isLoggable(Log.FINER)){ 
 logger.finer("Problem: " + generateDiagnostic()); 
}

logger.log(Level.FINER, "Problem: " + generateDiagnostic());

logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());
```

2. 环绕执行
```
String oneLine = 
 processFile((BufferedReader b) -> b.readLine()); 
String twoLines = 
 processFile((BufferedReader b) -> b.readLine() + b.readLine()); 
public static String processFile(BufferedReaderProcessor p) throws 
 IOException { 
 try(BufferedReader br = new BufferedReader(new FileReader("java8inaction/ 
 chap8/data.txt"))){ 
 return p.process(br); 
 } 
} 
public interface BufferedReaderProcessor{ 
 String process(BufferedReader b) throws IOException; 
}
```

#### 设计模式
1. 策略模式
```
 private final ValidationStrategy strategy; 
 public Validator(ValidationStrategy v){ 
 this.strategy = v; 
 } 
 public boolean validate(String s){ 
 return strategy.execute(s); 
 } 
}

Validator numericValidator = 
 new Validator((String s) -> s.matches("[a-z]+")); 
boolean b1 = numericValidator.validate("aaaa"); 
Validator lowerCaseValidator = 
 new Validator((String s) -> s.matches("\\d+")); 
boolean b2 = lowerCaseValidator.validate("bbbb");
```

2. 模板方法
```
public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){ 
 Customer c = Database.getCustomerWithId(id); 
 makeCustomerHappy.accept(c); 
}

new OnlineBankingLambda().processCustomer(1337, (Customer c) -> 
 System.out.println("Hello " + c.getName());
```

3. 观察者模式
```
class Feed implements Subject{ 
 private final List<Observer> observers = new ArrayList<>(); 
 public void registerObserver(Observer o) { 
 this.observers.add(o); 
 } 
 public void notifyObservers(String tweet) { 
 observers.forEach(o -> o.notify(tweet)); 
 } 
}

 if(tweet != null && tweet.contains("money")){ 
 System.out.println("Breaking news in NY! " + tweet); 
 } 
}); 
f.registerObserver((String tweet) -> { 
 if(tweet != null && tweet.contains("queen")){ 
 System.out.println("Yet another news in London... " + tweet); 
 } 
});
```


4. 责任链模式
```
UnaryOperator<String> headerProcessing =
 (String text) -> "From Raoul, Mario and Alan: " + text;
UnaryOperator<String> spellCheckerProcessing = 
 (String text) -> text.replaceAll("labda", "lambda"); 
Function<String, String> pipeline = 
 headerProcessing.andThen(spellCheckerProcessing); 
String result = pipeline.apply("Aren't labdas really sexy?!!");
```

5. 工厂模式
```
public class ProductFactory { 
 public static Product createProduct(String name){ 
 switch(name){ 
 case "loan": return new Loan(); 
 case "stock": return new Stock(); 
 case "bond": return new Bond(); 
 default: throw new RuntimeException("No such product " + name); 
 } 
 } 
}



final static Map<String, Supplier<Product>> map = new HashMap<>(); 
static { 
 map.put("loan", Loan::new); 
 map.put("stock", Stock::new); 
 map.put("bond", Bond::new); 
}
public static Product createProduct(String name){ 
 Supplier<Product> p = map.get(name); 
 if(p != null) return p.get(); 
 throw new IllegalArgumentException("No such product " + name); 
}

```

#### 不同类型的兼容性：二进制、源代码和函数行为
变更对Java程序的影响大体可以分成三种类型的兼容性，分别是：二进制级的兼容、源代
码级的兼容，以及函数行为的兼容。①刚才我们看到，向接口添加新方法是二进制级的兼容，
但最终编译实现接口的类时却会发生编译错误。
1. 二进制级的兼容性表示现有的二进制执行文件能无缝持续链接（包括验证、准备和解析）
和运行。比如，为接口添加一个方法就是二进制级的兼容，这种方式下，如果新添加的方法不
被调用，接口已经实现的方法可以继续运行，不会出现错误。
2. 简单地说，源代码级的兼容性表示引入变化之后，现有的程序依然能成功编译通过。比如，
向接口添加新的方法就不是源码级的兼容，因为遗留代码并没有实现新引入的方法，所以它们
无法顺利通过编译。
3. 最后，函数行为的兼容性表示变更发生之后，程序接受同样的输入能得到同样的结果。比
如，为接口添加新的方法就是函数行为兼容的，因为新添加的方法在程序中并未被调用（抑或
该接口在实现中被覆盖了）。

#### 默认方法的使用模式
1. 可选方法
2. 行为的多继承


#### 解决（菱形问题）冲突规则
1. 类中的优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
2. 如果无法依据第一条进行判断（如果类或者父类没有对应的方法），那么子接口的优先级最高；函数签名相同时，优先选择拥有最具体实现的默认方法的接口，即如果B继承了A，那么B就比A更具体
3. 如果还是无法判断，继承了多个接口的类必须通过显示的覆盖和调用期望的方法，显示的选择使用哪一个默认方法的实现
    ```
   public class C implements B, A { 
        void hello(){
            B.super.hello();
        }
    }
    ```


#### Optional说明
在你的代码中始终如一地使用Optional，能非常清晰地界定出变量值的缺失是结构上的问
题，还是你算法上的缺陷，抑或是你数据中的问题。另外，我们还想特别强调，引入Optional
类的意图并非要消除每一个null引用。与此相反，它的目标是帮助你更好地设计出普适的API，
让程序员看到方法签名，就能了解它是否接受一个Optional的值。