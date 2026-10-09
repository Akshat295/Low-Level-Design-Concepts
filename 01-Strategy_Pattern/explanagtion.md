# Strategy Pattern

## 1. Definition

The **Strategy Pattern** defines a family of algorithms/behaviors, encapsulates each one separately, and makes them interchangeable at runtime.

In simple words:

> When a class can perform the same job in multiple different ways, move those different implementations into separate classes and allow the required implementation to be selected dynamically.

---

# 2. What Problem Does It Solve?

Suppose a class needs to support multiple ways of performing an operation.

A common first approach is:

```java
if (type.equals("A")) {
    // logic A
} else if (type.equals("B")) {
    // logic B
} else if (type.equals("C")) {
    // logic C
}
```

This creates tight coupling between the main class and every implementation.

As the number of behaviors increases:

```text
More behaviors
      ↓
More if-else/switch
      ↓
Larger class
      ↓
More modification
      ↓
Harder maintenance
```

Strategy Pattern separates those behaviors.

---

# 3. Structure of Strategy Pattern

There are usually three important components.

## Strategy

An interface or abstract class that defines the common behavior.

```java
public interface PaymentStrategy {

    void pay(double amount);
}
```

It provides the common contract.

---

## Concrete Strategy

Each implementation provides its own behavior.

```text
PaymentStrategy
      |
      +---- CreditCardPayment
      |
      +---- UpiPayment
      |
      +---- CashPayment
```

For example:

```java
class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        // UPI logic
    }
}
```

---

## Context

The Context is the class that **uses the strategy**.

```java
class PaymentService {

    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

The Context does not implement the different algorithms itself.

It delegates the work to the selected strategy.

---

# 4. How It Works Internally

Consider:

```java
PaymentStrategy strategy = new UpiPayment();

PaymentService service =
        new PaymentService(strategy);

service.pay(500);
```

### Step 1

Java creates:

```java
UpiPayment
```

This object implements:

```java
PaymentStrategy
```

---

### Step 2

The reference is stored in the parent/interface type:

```java
PaymentStrategy strategy = new UpiPayment();
```

This gives us polymorphism.

---

### Step 3

The strategy is passed to `PaymentService`.

```java
new PaymentService(strategy);
```

The `PaymentService` stores the reference:

```java
private PaymentStrategy paymentStrategy;
```

---

### Step 4

We call:

```java
service.pay(500);
```

`PaymentService` executes:

```java
paymentStrategy.pay(500);
```

---

### Step 5

At runtime, Java uses dynamic method dispatch.

Although the reference type is:

```java
PaymentStrategy
```

the actual object is:

```java
UpiPayment
```

Therefore:

```java
UpiPayment.pay()
```

is executed.

This is the key mechanism that makes Strategy Pattern work.

---

# 5. Why Is This Better?

| Bad Design | Strategy Pattern |
|---|---|
| `if-else` for every behavior | Separate strategy classes |
| One class knows every implementation | Each strategy owns its behavior |
| Adding behavior modifies existing class | Add a new strategy |
| Tight coupling | Loose coupling |
| Harder to test | Strategies can be tested independently |
| Large class | Smaller focused classes |

---

# 6. Open/Closed Principle

Strategy Pattern strongly supports the **Open/Closed Principle**:

> Software entities should be open for extension but closed for modification.

Suppose tomorrow we add:

```java
class PaypalPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        // PayPal logic
    }
}
```

We did not need to modify:

```java
PaymentService
```

We simply created another strategy.

That is the main advantage.

---

# 7. Dependency Inversion

`PaymentService` does not depend directly on:

```java
CreditCardPayment
UpiPayment
CashPayment
```

Instead, it depends on:

```java
PaymentStrategy
```

Therefore the high-level class depends on an abstraction rather than concrete implementations.

---

# 8. When Should We Use Strategy Pattern?

Use Strategy Pattern when:

- You have multiple ways of performing the same operation.
- Those implementations are interchangeable.
- You expect new implementations to be added.
- You find yourself writing large `if-else` or `switch` blocks based on behavior.
- You want to isolate algorithms from the class that uses them.

Examples:

```text
Payment methods
Sorting algorithms
Compression algorithms
Discount calculation
Pricing strategies
Routing algorithms
Authentication methods
Notification methods
Shipping calculations
Tax calculation
```

---

# 9. When Should We NOT Use It?

Do not automatically create a Strategy for every `if-else`.

If there are only two simple conditions that are unlikely to change, introducing multiple classes may make the system unnecessarily complicated.

The pattern is useful when the behaviors are:

- different enough to deserve separation
- likely to change independently
- likely to grow
- interchangeable

---

# 10. Strategy vs If-Else

A common interview question is:

> Why not just use `if-else`?

The answer is not:

> "if-else is bad."

`if-else` itself is perfectly valid.

The problem occurs when behavior keeps growing and changing.

For example:

```java
if (type == A) {
    // 50 lines
}
else if (type == B) {
    // 80 lines
}
else if (type == C) {
    // 100 lines
}
```

At that point, the class becomes responsible for too many different behaviors.

Strategy Pattern moves those behaviors into separate classes.

So the real principle is:

> Don't remove `if-else` just because it exists. Remove unnecessary coupling and excessive responsibility.

---

# 11. Advantages

### Maintainability

Each strategy has one focused responsibility.

### Extensibility

New strategies can be added without modifying the Context.

### Testability

Each strategy can be unit tested independently.

### Runtime flexibility

The strategy can be selected dynamically.

---

# 12. Disadvantages

### More classes

A simple `if-else` can turn into multiple classes.

### Increased abstraction

The design may initially feel more complicated.

### Client needs to know the strategies

Someone has to decide which strategy should be used.

---

# 13. Important Interview Point

Strategy Pattern is not simply:

> "Create an interface and multiple classes."

The important concept is:

> **Encapsulate interchangeable behavior behind an abstraction and delegate the behavior to the selected implementation.**

The abstraction + polymorphism + delegation together create the Strategy Pattern.

---

# 14. Class Diagram

```text
                <<interface>>
              PaymentStrategy
                     |
          -------------------------
          |           |           |
          v           v           v
   CreditCard      UPI          Cash
    Payment       Payment      Payment
          \          |          /
           \         |         /
            \        |        /
                  Context
              PaymentService
```

More accurately:

```text
PaymentService
      |
      | has
      v
PaymentStrategy
      ^
      |
 -----------------------------
 |             |             |
CreditCard     UPI          Cash
Payment      Payment       Payment
```

---

# 15. Key Takeaways

1. Strategy Pattern is used when multiple interchangeable behaviors/algorithms exist.
2. Each behavior is encapsulated in its own class.
3. The Context depends on the Strategy abstraction.
4. Polymorphism allows different strategies to be used through the same interface.
5. New strategies can usually be added without modifying the Context.
6. The pattern commonly helps with Open/Closed Principle and Dependency Inversion.
7. Its main purpose is to avoid tightly coupling a class with multiple implementations.

---

# 16. One-Line Memory Trick

> **Same job, different ways → extract the ways into Strategies.**