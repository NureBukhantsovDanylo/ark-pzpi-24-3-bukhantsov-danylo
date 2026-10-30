// ========================================
// NamingExample.java
// ========================================
1  // ❌ Погано: магічні числа та скорочення
2  if (u.s == 1 && u.age >= 18) {
3      res.add(u);
4  }
5  
6  // ✅ Добре: самодокументований код
7  private static final int LEGAL_ADULT_AGE = 18;
8  
9  if (user.isActive() && user.getAge() >= LEGAL_ADULT_AGE) {
10      activeAdults.add(user);
11  }

// ========================================
// RefactoringExample.java
// ========================================
1   // ✗ До рефакторингу
2   if (age >= 18 && price * 0.2 > 50) { ... }
3  
4   // ✓ Після рефакторингу (константи + методи)
5   private static final int ADULT_AGE = 18;
6   private static final double VAT_RATE = 0.2;
7   private static final double MIN_VAT = 50.0;
8  
9   if (isAdult(age) && calculateVat(price) > MIN_VAT) {
10      // Оновлена чиста логіка
11  }

// ========================================
// CodeStructureExample.java
// ========================================
1  // ✗ God-class: робить усе
2  class OrderService { 
3      void save() {} 
4      void sendEmail() {} 
5      void printPdf() {} 
6  }
7  
8  // ✓ Розділення відповідальності
9  class OrderRepository { void save(Order o) {} }
10  class EmailNotifier   { void send(Order o) {} }
11  class InvoicePrinter  { void print(Order o) {} }

// ========================================
// ImmutableExample.java
// ========================================
1  // ✅ Сучасний незмінний об'єкт
2  public record PaymentRequest(
3          String accountId, 
4          BigDecimal amount, 
5          List<String> tags) {
6  
7      public PaymentRequest {
8          Objects.requireNonNull(accountId);
9          // Захисна незмінна копія колекції
10          tags = List.copyOf(tags);
11      }
12  }

// ========================================
// ModernJavaExample.java
// ========================================
1  public sealed interface Delivery permits Standard, Drone {}
2  
3  public double calcShipping(Delivery delivery) {
4      return switch (delivery) {
5          case Standard(double w) -> w * 1.5;
6          case Drone(double w) when w <= 5.0 -> 25.0;
7          case Drone _ -> throw new IllegalArgumentException("Важке");
8      };
9  }

// ========================================
// ParadigmsExample.java
// ========================================
1  // ООП: програмуйте до інтерфейсу
2  interface Discount { 
3      double apply(double price); 
4  }
5  
6  // Функціональний стиль (Java 8+)
7  List<String> names = users.stream()
8      .filter(User::isActive)
9      .map(User::getName)
10      .toList(); // Замість складних циклів

// ========================================
// OptimizationExample.java
// ========================================
1  // ❌ Повільно: створення нових String
2  String res = "";
3  for (String w : words) { 
4      res += w; 
5  }
6  
7  // ✅ Швидко: один змінюваний буфер
8  StringBuilder sb = new StringBuilder();
9  for (String w : words) { 
10      sb.append(w); 
11  }

// ========================================
// ErrorHandlingExample.java
// ========================================
1  // ✗ Поганий приклад: порожній catch приховує помилку
2  try { readFile(); } catch (Exception e) { }
3  
4  // ✓ Гарний приклад: конкретний виняток та ресурси
5  public Optional<String> readFirstLine(Path path) {
6      try (BufferedReader br = Files.newBufferedReader(path)) {
7          return Optional.ofNullable(br.readLine());
8      } catch (IOException e) {
9          throw new DataLoadException("Не вдалося прочитати", e);
10      }
11  }

// ========================================
// DocumentationExample.java
// ========================================
1  /**
2   * Обчислює ціну зі знижкою.
3   *
4   * @param price   початкова ціна (більше 0)
5   * @param percent відсоток знижки (0–100)
6   * @return ціна після знижки
7   * @throws IllegalArgumentException якщо дані некоректні
8   */
9  public double apply(double price, int percent) {
10      // реалізація методу
11  }

// ========================================
// TestingExample.java
// ========================================
1  @Test
2  void shouldApplyTenPercentDiscount() {
3      // Arrange (Підготовка)
4      PriceCalculator calc = new PriceCalculator();
5      
6      // Act (Дія)
7      double result = calc.apply(100.0, 10);
8      
9      // Assert (Перевірка)
10      assertEquals(90.0, result, 0.001);
11  }

