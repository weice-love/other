### 实体类字符串转double排序比较: Comparator.comparing(待转字符串, Comparator.comparing(Double::new)

#### Spring Boot 读取配置文件

```
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("a.txt");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
```

