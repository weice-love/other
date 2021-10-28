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

#### 默认方法的使用模式
1. 可选方法
2. 行为的多继承