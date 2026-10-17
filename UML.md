Before writing a single line of production code, clear systems are designed visually. **Unified Modeling Language (UML)** is the standardized visual language software engineers use to specify, visualize, construct, and document the artifacts of a software system.

Without clear visual blueprints, complex Low-Level Design (LLD) decisions get lost in verbal discussions, leading to architectural mismatch and costly refactoring down the line.

## The Two Main Pillars of UML

UML diagrams fall into two primary categories based on what perspective of the system they represent:

```
+-----------------------------------------------------------------+
|                          UML DIAGRAMS                           |
+-----------------------------------------------------------------+
            |                                            |
            v                                            v
+-------------------------------+         +-------------------------------+
|     Structural (Static)       |         |     Behavioral (Dynamic)      |
|                               |         |                               |
| Shows what components exist   |         | Shows how components interact |
| in the code layout.           |         | and change state over time.   |
+-------------------------------+         +-------------------------------+
            |                                             |
            v                                             v
+-------------------------------+         +-------------------------------+
|      Focus: Class Diagram     |         |    Focus: Sequence Diagram    |
+-------------------------------+         +-------------------------------+
```

### 1. Structural (Static) UML Diagrams

Structural diagrams model the static organization of a system. They outline components, class blueprints, attributes, and relationships without dealing with execution time or dynamic logic.

- **Diagram Count:** There are **7 structural diagrams** in standard UML (_Class, Component, Deployment, Object, Package, Composite Structure, and Profile Diagrams_).
- **Core Focus in LLD:** Out of these 7, we focus heavily on the **Class Diagram**. The remaining 6 diagrams deal with specific deployment setups, high-level packaging, or hardware layouts, which are highly case-specific and rarely used during low-level application design.

### 2. Behavioral (Dynamic) UML Diagrams

Behavioral diagrams model how a system behaves at runtime. They illustrate dynamic execution, message flows, state transitions, and object interactions over time.

- **Diagram Count:** There are **7 behavioral diagrams** in standard UML (_Sequence, Use Case, Activity, State Machine, Communication, Interaction Overview, and Timing Diagrams_).
- **Core Focus in LLD:** Out of these 7, we focus primarily on the **Sequence Diagram**. The other 6 diagrams cater to broad business processes or hardware timing specifications, whereas the **Sequence Diagram** directly models method invocations and runtime message passing between objects.

## Class Diagram Essentials

A **Class Diagram** is a structural diagram that represents the static blueprint of your application by showing classes, their attributes, methods, and access boundaries.<br>
We represent **class structures** and **associations or connections** in a **class diagram**.

### Class structures:

#### 1. Anatomy of a Class Block

In UML, a class is rendered as a rectangle divided into three horizontal compartments:

```
+-----------------------------------+
|             ClassName             |  <-- 1. Class Name
+-----------------------------------+
| - attributeName : DataType        |  <-- 2. Attributes (Variables)
+-----------------------------------+
| + methodName(param : Type) : Type |  <-- 3. Operations (Methods)
+-----------------------------------+
```

1. **Top Compartment:** The name of the class or interface.
2. **Middle Compartment:** Fields and member variables with their data types.
3. **Bottom Compartment:** Methods, parameters, and return types.

#### 2. Access Modifiers

Visibility of attributes and methods is defined using standard UML symbols:

| Symbol | Visibility            | Java Equivalent                              |
| :----: | :-------------------- | :------------------------------------------- |
|  `+`   | **Public**            | Accessible from any class                    |
|  `-`   | **Private**           | Accessible only within the defining class    |
|  `#`   | **Protected**         | Accessible within the package and subclasses |
|  `~`   | **Package / Default** | Accessible only within the same package      |

---

#### 3. Abstract Classes and Interfaces

UML uses specific notation to distinguish concrete classes from abstract types and contracts:

**Abstract Class** <br>
An abstract class cannot be instantiated directly and is denoted by rendering the class name in _italics_, using the `{abstract}` property tag or using the `«abstract»` stereotype at the top of the block.

```
                   «abstract»
+-----------------------------------------------------+
|                PaymentGateway                       |
+-----------------------------------------------------+
| # merchantKey : String                              |
+-----------------------------------+
| + processPayment(amt: double)  : void {abstract}    |  <-- Abstract Method
+-----------------------------------------------------+
```

**Interface** <br>
An interface defines a pure contract and is denoted using the `<<interface>>` stereotype at the top of the block.

```
            <<interface>>
+-----------------------------------+
|             Notification          |
+-----------------------------------+
| + sendNotification(msg: String)   |
+-----------------------------------+
```

### Associations & Relationships

In UML, an **Association** defines how two or more classes interact or connect with each other. Associations are broadly categorized into two types:

1. **Class Association** — Defined at compile-time via structural relationships like inheritance.
2. **Object Association** — Defined at runtime via instance references (Simple Association, Aggregation, Composition).

#### Class Association (Inheritance)

A **Class Association** represents an **"IS-A"** relationship. It signifies that a child class inherits the fields and behaviors of a parent class or implements the contract defined by an interface.

**1. Generalization (Class Inheritance)**
Used when a subclass extends a superclass.

- **UML Symbol:** A solid line with a hollow/filled triangle (`───▷`) arrowhead pointing from the child class to the parent class.

**2. Realization (Interface Implementation)**
Used when a class implements an interface.

- **UML Symbol:** A dashed line with a hollow/filled triangle (`── ── ▷`) arrowhead pointing from the implementing class to the interface.

```
        «interface»
 +-----------------------+
 |     PaymentGateway    |
 +-----------------------+
             ^
             |  (Realization: Dashed Line)
             |
 +-----------------------+
 |   BaseCardProcessor   |
 +-----------------------+
             ^
             |  (Generalization: Solid Line)
             |
 +-----------------------+
 |  CreditCardProcessor  |
 +-----------------------+
```

> The solid line in diagram may also look like a dashed line, but for showing inheritance from class we use solid line with filled or hollow arrow (`───▷`).

```java
// Interface
public interface PaymentGateway {
    void processPayment(double amount);
}

// Parent Abstract Class (Realization)
public abstract class BaseCardProcessor implements PaymentGateway {
    protected String merchantId;

    public BaseCardProcessor(String merchantId) {
        this.merchantId = merchantId;
    }
}

// Child Class (Generalization)
public class CreditCardProcessor extends BaseCardProcessor {
    public CreditCardProcessor(String merchantId) {
        super(merchantId);
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount + " via merchant: " + merchantId);
    }
}
```

#### Object Association

An **Object Association** represents a **"HAS-A"** relationship between instances at runtime. Unlike class associations (inheritance), object associations define how objects hold references to other objects.

Object associations are categorized into three levels based on the strength of ownership and lifecycle dependency:

```
Simple Association  --->  Aggregation  --->  Composition
(Weakest)               (Stronger)          (Strongest)
```

> **Note on Implementation:** In actual code, all three associations look virtually identical—a class holding a field reference to another class. The distinction between simple association, aggregation, and composition exists purely at the **conceptual/design level** to define ownership and lifecycle dependencies.

**1. Simple Association (Weakest)** <br>

- **Concept:** Represents a loose, peer-to-peer connection between two independent objects. Neither object owns the other, and both can exist completely independently.
- **UML Representation:** A plain solid line (optionally with an open arrow `───>` indicating navigation direction).

**2. Aggregation (Stronger)** <br>

- **Concept:** Represents a **"whole-part"** relationship where a parent object contains a child object, but the child object **can exist independently** if the parent is destroyed.
- **UML Representation:** A hollow diamond `◇───` attached to the owner (parent) object.

**3. Composition (Strongest)** <br>

- **Concept:** Represents a **strict "whole-part"** relationship where the child object **cannot exist independently** without the parent object. If the parent is destroyed, the child object is destroyed with it.
- **UML Representation:** A filled diamond `◆───` attached to the owner (parent) object.

**4. Dependency / Uses (Weakest of All)** <br>

- **Concept:** Represents a **"USES-A"** relationship. One class temporarily depends on another — typically as a **method parameter, local variable, or return type** — without holding a long-term field reference. It's the most transient and loosest coupling possible. A change in the _used_ class can affect the _using_ class.
- **UML Representation:** A **dashed open arrow** (`- - - ->` or `--->`) pointing from the dependent class to the class it uses.
- **Key distinction from Simple Association:** In a Simple Association the dependency is stored as a **field** (permanent reference). In a Dependency/Uses relation the other class only appears inside a **method signature** (temporary, local scope).

```
+------------------+          +-----------------+
|   ReportService  | - - - -> |   PDFFormatter  |
+------------------+  uses    +-----------------+
| + generate()     |          | + format()      |
+------------------+          +-----------------+
```

```java
// Dependency ("uses"): PDFFormatter is NOT stored as a field.
// It is only used temporarily inside the method — classic "--->".
public class ReportService {
    public void generate(PDFFormatter formatter) {   // <-- method param
        formatter.format();
    }
}
```

> **Quick Reference — Relationship Strength (weakest → strongest):**
>
> ```
> Dependency  --->  Simple Association  --->  Aggregation  --->  Composition
>  (uses-a)         (has-a, field ref)       (whole-part)       (owns lifecycle)
> ```

---

### Comprehensive Example: All-in-One Class Diagram

To understand all these relationships in a single context, consider a **Person** driving a **Car**:

- **Person** and **Car** $\rightarrow$ **Simple Association** _(A Person uses a Car (Person has-a Car), but both exist independently)_
- **Manual Car** and **Electric Car** extend **Car** $\rightarrow$ **Generalization / Inheritance** _(IS-A relationship)_
- **Car** and **Engine** / **Battery** $\rightarrow$ **Aggregation** _(An Engine or Battery can exist outside a specific Car)_
- **Car** and **Tyre** / **Windscreen** $\rightarrow$ **Composition** _(Tyres and Windscreens are integral parts tied strictly to the Car instance)_

```
       +--------------+                    +------------------+

       |    Person    |───────────────────>|       Car        |
       +--------------+ Simple Association +------------------+
                                             ◆      ▲       ◇
                                            /       |        \
                              Composition  /        |         \  Aggregation
                                          /         |          \
                                         /          |           \
                               +--------------+     |         +---------------+
                               |     Tyre     |     |         |    Engine     |
                               +--------------+     |         +---------------+
                                                    |
                                                    │
                                                    │ Generalization (IS-A)
                                                    │
                                            +--------------+
                                            |  ElectricCar |
                                            +--------------+
```

````java
```java
// 1. Independent components (Engine for Aggregation)
public class Engine {
    private String engineNumber;

    public Engine(String engineNumber) {
        this.engineNumber = engineNumber;
    }
}

// 2. Tightly bound components (Tyre for Composition)
public class Tyre {
    private String brand;

    public Tyre(String brand) {
        this.brand = brand;
    }
}

// 3. Parent Class managing Aggregation, Composition, and Inheritance
public abstract class Car {
    // Composition: Tyres are created inside the Car constructor (Tight Lifecycle)
    private final List<Tyre> tyres;

    // Aggregation: Engine is passed in from outside (Independent Lifecycle)
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine; // Aggregation
        this.tyres = List.of( // Composition
            new Tyre("Michelin"),
            new Tyre("Michelin"),
            new Tyre("Michelin"),
            new Tyre("Michelin")
        );
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}

// Inheritance (IS-A)
public class ElectricCar extends Car {
    public ElectricCar(Engine electricMotor) {
        super(electricMotor);
    }
}

// 4. Driver class representing Simple Association
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    // Simple Association: Car is passed as a method parameter (Weak Connection)
    public void drive(Car car) {
        System.out.println(name + " is driving the car.");
    }
}
````

## Sequence Diagrams: Modeling Dynamic Interactions

While Class Diagrams capture the static skeleton of your application, **Sequence Diagrams** illustrate the dynamic runtime behavior. They show how objects interact, exchange messages, and execute business logic in a strict time-ordered sequence.

### Key Components of a Sequence Diagram

Every sequence diagram uses four primary visual elements:

```
[ Object / Class ]           <-- 1. Participant Block
        |
        |                    <-- 2. Lifeline
        |
        +---+
        |   |                <-- 3. Activation Bar (Focus of Control)
        |   | === Message ==> <-- 4. Message Line
        +---+
        |
```

1. **Participant / Class:** Represents the object or system entity participating in the interaction (placed horizontally at the top).
2. **Lifeline:** A vertical dashed line extending downward from each participant, representing the existence of the object over time.
3. **Activation Bar (Execution Specification):** A thin vertical rectangle overlaid on a lifeline showing when an object is actively executing a method or processing an operation.
4. **Message Lines:** Horizontal arrows drawn between lifelines showing data exchanges, method calls, or responses.

### Message Types & Notation

Messages define how objects communicate. The shape of the arrowhead indicates the exact nature of the interaction:

| Message Type             |            Symbol / Line Style            | Description                                                                     | Code Mapping                              |
| :----------------------- | :---------------------------------------: | :------------------------------------------------------------------------------ | :---------------------------------------- |
| **Synchronous Message**  |  `──────►` _(Solid line, filled arrow)_   | Sender blocks and waits for a response before continuing execution.             | Regular method call: `a.doSomething()`    |
| **Synchronous Reply**    |  `- - - -►` _(Dashed line, open arrow)_   | Return value or control flowing back to the caller.                             | Method return statement: `return result;` |
| **Asynchronous Message** |   `──────>` _(Solid line, open arrow)_    | Sender fires the message and immediately continues without waiting.             | Thread/Executor, Event, Queue message     |
| **Create Message**       |            `- - - -► «create»`            | Instantiates a new participant object during execution.                         | Object instantiation: `new Order()`       |
| **Destroy Message**      |     `──► ✖` _(Target ended with 'X')_     | Explicitly terminates an object lifeline.                                       | Object destruction / explicit cleanup     |
| **Found Message**        |  `●─────►` _(Filled circle to lifeline)_  | Message originates from an unknown or external source outside the system scope. | Inbound webhook, system signal            |
| **Lost Message**         | `──────► ●` _(Lifeline to filled circle)_ | Message sent to a target that is outside the system scope or unhandled.         | Log output, unhandled notification        |

### Step-by-Step Guide: How to Draw a Sequence Diagram

To construct a clear and readable sequence diagram for Low-Level Design:

1. **Identify the Use Case Scope:** Define the single business flow or endpoint you want to model (e.g., _User Places an Order_).
2. **Identify Participating Objects:** List the specific classes/services involved in this workflow from left to right in order of initial interaction.
3. **Establish Lifelines:** Draw vertical dashed lines extending downward from each participant block.
4. **Draw Sequential Messages (Top to Bottom):** Lay out method calls chronologically from top to bottom.
5. **Add Activation Bars & Replies:** Overlay activation bars on lifelines to mark active processing periods and draw dashed return lines where necessary.

### Sequence Diagram Example: Order Checkout Flow

```
@startuml
autonumber

actor User
participant ClientApp
participant OrderService
database PaymentGateway

User -> ClientApp : placeOrder()
activate ClientApp

ClientApp -> OrderService : processOrder()
activate OrderService

OrderService -> PaymentGateway : charge(amount)
activate PaymentGateway
PaymentGateway --> OrderService : txSuccess
deactivate PaymentGateway

OrderService -> OrderService : sendAsyncEmail()
note right: Async Message

OrderService --> ClientApp : orderSuccess
deactivate OrderService

ClientApp --> User : Display Conf
deactivate ClientApp

@enduml
```

```
User          ClientApp       OrderService      PaymentGateway

 |                |                |                  |
 |--placeOrder()-->|                |                  |
 |                |--processOrder()->|                  |
 |                |                |--charge(amount)->|
 |                |                |<---txSuccess-----|
 |                |                |                  |
 |                |                |--sendAsyncEmail()| (Self-loop Async)
 |                |                |<--orderSuccess---|
 |                |<--Display Conf-|                  |
 |                |                |                  |

```

> In real-world application design, a single system handles dozens of interactions. Because of this, **a system does not have just one sequence diagram—you create one Sequence Diagram per Use Case** (e.g., _Successful Payment_, _Failed Checkout_, _User Cancellation_).

> To model complex business logic like conditional execution and loops within a single Sequence Diagram, UML provides **Combined Fragments**<br> Combined Fragments: `alt` (`if-else`), `opt` (`if`), and `loop` (`for/while`)
