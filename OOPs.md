## What is OOP and Why Do We Need It?

**Object-Oriented Programming (OOP)** is a programming paradigm centered around **objects** rather than actions or pure logic.

In OOP, you can represent any real-world entity—like a `User`, a `BankAccount`, or a `Car`—as an object in your code, bundling its data (attributes) and behavior (methods) together.

* * *

### Procedural vs. Object-Oriented Approach

In traditional procedural programming languages (like **C** or **BASIC**), execution follows a step-by-step sequence of instructions, relying heavily on functions, conditions, and loops:

```c
// Procedural Approach (C Example)
#include <stdio.h>

struct Car {
    char brand[20];
    int speed;
};

void drive(struct Car c) {
    printf("%s is driving at %d km/h\n", c.brand, c.speed);
}

int main() {
    struct Car myCar = {"Toyota", 80};
    drive(myCar);
    return 0;
}
```

### Why and When We Shifted to OOP

As software grew more complex, maintaining global variables and detached functions became difficult. Developers realized that software ultimately models the real world—where entities have state, behavior, and clear boundaries.

OOP allows us to model these entities directly in code, making applications easier to scale, organize, and maintain:

```java
// Object-Oriented Approach (Java Example)
class Car {
    private String brand;
    private int speed;

    public Car(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public void drive() {
        System.out.println(brand + " is driving at " + speed + " km/h");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", 80);
        myCar.drive();
    }
}
```

## The Two Core Building Blocks: Class & Object

Think of OOP like building real things. Everything in OOP revolves around just two main concepts: **Class** and **Object**.

*   **Class:** The design, template, or blueprint. (It doesn't take space in physical memory until created).
    
*   **Object:** The actual physical thing created using that blueprint. (It actually exists and takes up memory).
    

* * *

### Understanding with a Simple Example

Imagine you want to manufacture a **Smartphone**.

1.  First, engineers design a blueprint on paper. They decide:
    
    *   What properties it will have: `brand`, `color`, `ram`
        
    *   What actions it can do: `makeCall()`, `takePhoto()`*This blueprint on paper is your* ***Class****.*
        
2.  Next, the factory uses that blueprint to make actual phones (like your phone, your friend's phone, etc.). *These physical phones in your hand are* ***Objects****.*
    

* * *

### 1\. Class (The Blueprint)

A class defines what data (attributes) an object will store and what actions (methods) it can perform.

```java
// Blueprint: Class definition
class Smartphone {
    // State / Data (Attributes)
    String brand;
    String color;
    int ramInGB;

    // Behavior / Action (Methods)
    void makeCall(String phoneNumber) {
        System.out.println("Calling " + phoneNumber + "...");
    }
}
```

> **Note:** Class names in Java should always follow **TitleCase** (also called `PascalCase`), where every word starts with a capital letter (e.g., `Smartphone`, `BankAccount`). While Java won't throw an error if you don't follow this, it is standard industry convention and makes your code clean and readable.

### 2\. Object (The Real Deal)

An object is an actual instance created from the class. You create it using the new keyword in Java. Once created, each object gets its own copy of the variables defined in the class and can perform the actions defined in it.

```java
public class Main {
    public static void main(String[] args) {
        // Creating an Object from the Smartphone class
        Smartphone myPhone = new Smartphone();

        // Setting data for THIS specific object
        myPhone.brand = "Samsung";
        myPhone.color = "Black";
        myPhone.ramInGB = 8;

        // Calling a method on this object
        myPhone.makeCall("9876543210");

        // Creating ANOTHER object from the SAME blueprint
        Smartphone friendPhone = new Smartphone();
        friendPhone.brand = "Apple";
        friendPhone.color = "White";
        friendPhone.ramInGB = 6;
    }
}
```

### Reference Variables & Heap Memory

When you write `Smartphone myPhone = new Smartphone();`:

1.  `new Smartphone()`: Allocates memory for the object in the **Heap** at runtime. (Dynamic Memory Allocation)
    
2.  `myPhone`: Is a **Reference Variable** stored on the **Stack**. It holds the memory address pointing to the actual object sitting in the Heap.
    

```plaintext
STACK MEMORY                    HEAP MEMORY
--------------            -----------------------
|            |            |  SmartPhone Object: |
|  myPhone---|------------|-> brand: "Samsung"  |
|            |            |   color: "Black"    |
|            |            |   ramInGb: 8        |
--------------            -----------------------
```

* * *

### Instance Variables vs. Local Variables

*   **Instance Variables (Class Attributes):** Belong to the object in the Heap. Java **automatically initializes them** with default values if you don't assign them.
    
*   **Local Variables:** Created inside methods/blocks on the Stack. Java **does NOT give them default values**. You must initialize them before using them, or the compiler throws an error.
    

| Variable Type | Where it lives | Default Value? | Example Defaults |
| --- | --- | --- | --- |
| **Instance Variable** | Heap (inside object) | **Yes** | `int` $\\rightarrow$ `0`, `double` $\\rightarrow$ `0.0`, `boolean` $\\rightarrow$ `false`, `String` / Objects $\\rightarrow$ `null` |
| **Local Variable** | Stack (inside method) | **No** (Must assign before use) | *N/A (Compile error if read unassigned)* |

#### Quick Code Example

```java
class Smartphone {
    // Instance variables (Get automatic default values)
    int ramInGB;        // Default: 0
    String brand;       // Default: null
    boolean is5G;       // Default: false

    void checkStorage() {
        // Local variable
        int appCount; 
        
        // System.out.println(appCount); 
        // ❌ COMPILE ERROR! Local variable 'appCount' must be initialized before use.
        
        appCount = 5; 
        System.out.println(appCount); // ✅ Works fine now
    }
}
```

## Constructors in Java

A **Constructor** is a special method used to initialize a newly created object. It sets up the initial state (values) for the object when memory is allocated.

*   **Auto-called:** Executes automatically when you use the `new` keyword.
    
*   **Same Name:** Must have the exact same name as the `Class`.
    
*   **No Return Type:** Unlike regular methods, constructors do not have a return type (not even `void`).
    
*   **Cannot be called manually:** You cannot do `myPhone.SmartPhone()` later in your code—it runs **only once** during object creation.
    

* * *

### Default Constructor

If you **do not** write any constructor in your class, Java automatically provides a **default no-argument constructor** behind the scenes.

```java
class SmartPhone {
    // Provided automatically by Java compiler if no constructor is written
    SmartPhone() {
        // Empty body - sets instance variables to default values (0, null, false)
    }
}
```

> Important Rule: If you define any custom constructor (parameterized or no-arg), Java stops providing the default constructor automatically.

### Types of Constructors

#### 1\. No-Argument Constructor (Explicit)

A constructor that takes no parameters. You write it yourself to give default custom values to objects.

```java
class SmartPhone {
    String brand;

    // Explicit No-Arg Constructor
    SmartPhone() {
        brand = "Generic Brand";
    }
}
```

#### 2\. Parameterized Constructor

A constructor that takes arguments so you can initialize different objects with custom values at creation time.

```java
class SmartPhone {
    String brand;
    int ram;

    // Parameterized Constructor
    SmartPhone(String b, int r) {
        brand = b;
        ram = r;
    }
}
```

### Constructor Overloading

Just like normal methods, you can have multiple constructors in the same class, as long as they have different parameter lists (different number, types, or order of parameters).

```java
class SmartPhone {
    String brand;
    int ram;

    // Constructor 1: Default
    SmartPhone() {
        this.brand = "Unknown";
        this.ram = 4;
    }

    // Constructor 2: Takes only brand
    SmartPhone(String brand) {
        this.brand = brand;
        this.ram = 4; // default ram
    }

    // Constructor 3: Takes both brand and ram
    SmartPhone(String brand, int ram) {
        this.brand = brand;
        this.ram = ram;
    }
}
```

### Constructor Chaining (using this())

Constructor Chaining is the process of calling one constructor from another constructor within the same class using the this() keyword. It avoids code duplication.

> Rule: this() call must be the very first statement inside the constructor body.

**Example 1: Forwarding defaults to a master constructor**

```java
class SmartPhone {
    String brand;
    int ram;

    // Default constructor calls Parameterized constructor
    SmartPhone() {
        this("Generic Brand", 4); // Calls Constructor 2
    }

    // Master Parameterized Constructor
    SmartPhone(String brand, int ram) {
        this.brand = brand;
        this.ram = ram;
    }
}
```

**Example 2: Multi-step chaining**

```java
class SmartPhone {
    String brand;
    int ram;
    boolean is5G;

    // 1st Constructor
    SmartPhone() {
        this("Unknown"); // Calls 2nd
    }

    // 2nd Constructor
    SmartPhone(String brand) {
        this(brand, 8, true); // Calls 3rd
    }

    // 3rd Constructor (Main setup)
    SmartPhone(String brand, int ram, boolean is5G) {
        this.brand = brand;
        this.ram = ram;
        this.is5G = is5G;
    }
}
```

### Memory Error: OutOfMemoryError

When you create objects using new, memory is allocated in the Heap.

If you create millions of objects continuously (or run into an infinite loop creating objects) until the Heap space gets completely exhausted, Java throws an OutOfMemoryError at runtime.

```java
// Example that can crash the JVM Heap
while (true) {
    SmartPhone p = new SmartPhone(); // Continuously fills Heap memory
}
// Output: java.lang.OutOfMemoryError: Java heap space
```

## Destructor in Java (Does it exist?)

Unlike C++, **Java does NOT have explicit destructors**.

In C++, developers must manually delete objects to free memory. In Java, memory management is handled automatically by the **Garbage Collector (GC)**, which runs in the background and frees up Heap memory occupied by unreferenced objects.

* * *

### Key Points to Remember

*   **No Manual Freeing:** You cannot manually destroy an object in Java.
    
*   `finalize()` **is Deprecated:** Java used to have a `finalize()` method that ran before garbage collection, but it has been deprecated and should not be used.
    
*   **Resource Cleanup:** For closing resources like files, database connections, or network sockets, use `AutoCloseable` with a **try-with-resources** block instead:
    

```java
// Recommended way to clean up resources in Java
try (FileReader fr = new FileReader("file.txt")) {
    // Read file operations
} catch (IOException e) {
    e.printStackTrace();
} // 'fr' is automatically closed here when exiting the block
```

## The `this` Keyword in Java

The `this` keyword is a reference variable in Java that refers to the **current object** (the specific object whose method or constructor is currently running).

Think of `this` as the object referring to **itself**.

* * *

### Key Uses of `this`

#### 1\. Distinguishing Instance Variables from Parameters (Shadowing)

When a constructor or method parameter has the exact same name as an instance variable, the parameter name "shadows" (hides) the instance variable. Use `this` to explicitly tell Java you mean the instance variable.

```java
class SmartPhone {
    String brand; // Instance variable

    SmartPhone(String brand) { // Parameter has same name
        // this.brand refers to the instance variable
        // brand refers to the constructor parameter
        this.brand = brand; 
    }
}
```

#### 2\. Constructor Chaining (this())

You can use this() to invoke another constructor within the same class.

> Rule: The this() call must be the very first line inside the constructor.

```java
class SmartPhone {
    String brand;
    int ram;

    SmartPhone() {
        this("Generic Brand", 4); // Calls the parameterized constructor below
    }

    SmartPhone(String brand, int ram) {
        this.brand = brand;
        this.ram = ram;
    }
}
```

#### 3\. Passing the Current Object as an Argument

You can pass this into another method if that method needs to operate on the current object.

```java
class SmartPhone {
    void display() {
        Printer printer = new Printer();
        printer.printPhoneDetails(this); // Passes 'this' phone object
    }
}
```

## Object Size in Java

When you create an object in Java (e.g., `new Object()`), it doesn't just store your variables. Java adds some **hidden metadata overhead** so the Java Virtual Machine (JVM) can manage the object (locking, garbage collection, type tracking).

In a standard 64-bit JVM (with Compressed OOPs enabled by default), every object's memory layout consists of 3 main parts:

```plaintext
┌─────────────────────────────────────────────────────────-┐
│                 JAVA OBJECT MEMORY LAYOUT                │
├─────────────────────────────────────────────────────────-┤
│ 1. Object Header (12 Bytes)                              │
│    ├── Mark Word  (8 Bytes)  -> HashCode, GC, Locks      │
│    └── Klass Word (4 Bytes)  -> Pointer to Class Metadata│
├─────────────────────────────────────────────────────────-┤
│ 2. Instance Fields / Data (Variable Bytes)               │
│    └── Your actual primitive variables & references      │
├─────────────────────────────────────────────────────────-┤
│ 3. Padding (0 to 7 Bytes)                                │
│    └── Extra empty space to make total size multiple of 8│
└─────────────────────────────────────────────────────────-┘
```

* * *

### The 3 Parts Breakdown

#### 1\. Object Header (12 Bytes Metadata)

Every object in Java comes with a **12-byte header** split into two sections:

*   **Mark Word (8 Bytes):** Stores internal JVM metadata:
    
    *   **Identity HashCode:** Generated when `System.identityHashCode(obj)` is called.
        
    *   **GC Age:** Tracks how many Garbage Collection cycles this object survived.
        
    *   **Locking & Synchronization Info:** Stores synchronization status (whether a thread locked this object using `synchronized`).
        
*   **Klass Word (4 Bytes with Compressed OOPs):**
    
    *   A pointer that tells the JVM: *"I am an instance of* `SmartPhone.class`*!"* It links the object in Heap to its class definition in Metaspace.
        

* * *

#### 2\. Instance Fields (Your Actual Data)

This is where your actual variables live. Primitive data types have fixed memory sizes:

| Data Type | Memory Size |
| --- | --- |
| `boolean`, `byte` | **1 byte** |
| `char`, `short` | **2 bytes** |
| `int`, `float` | **4 bytes** |
| `long`, `double` | **8 bytes** |
| **Object References** (e.g., `String brand`) | **4 bytes** *(with Compressed OOPs)* |

* * *

#### 3\. Alignment Padding (Rule of 8)

The JVM processes memory in **8-byte chunks** for 64-bit CPU architecture efficiency.

> **8-Byte Alignment Rule:** The total memory consumed by any object **MUST be a multiple of 8 bytes**.

If `Header + Fields` equals 21 bytes, the JVM adds **3 bytes of empty padding** to round it up to **24 bytes**.

* * *

### Real Example Calculation

Let's calculate the memory occupied by this `SmartPhone` object:

```java
class SmartPhone {
    int ramInGB;      // 4 bytes
    boolean is5G;     // 1 byte
    String brand;     // 4 bytes (Reference pointer)
}
```

**Step-by-Step Breakdown:**

1.  Header: 12 bytes
    
2.  Fields: 4 (int) + 1 (boolean) + 4 (reference) = 9 bytes
    
3.  Subtotal: 12 + 9 = 21 bytes
    
4.  Padding: 21 is not a multiple of 8. The next multiple of 8 is 24 $\\rightarrow$ 3 bytes of padding added.
    
5.  Total Heap Size = 24 Bytes
    

**Key Takeaways:**

1.  An empty Java object (new Object()) takes 16 bytes (12B Header + 4B Padding).
    
2.  Object references inside an object take 4 bytes on the Heap (pointing to another Heap memory address).
    
3.  Padding stores no data—it exists solely for CPU alignment performance.
    

## Primitives vs. Reference Types (And Why Both Exist)

This is one of the biggest confusion points in Java: **Why do we have** `int` **AND** `Integer`**? Why not just use one?**

Let's clear this up once and for all.

* * *

### 1\. Primitive Variables vs. Reference Variables

Java has two main types of variables:

#### Primitives (`int`, `double`, `boolean`, `char`, etc.)

*   **What they are:** Raw, basic values stored **directly inside the variable** on the **Stack**.
    
*   **No methods, no** `null`**:** You can't call `.something()` on an `int`, and it cannot be `null`.
    

```java
int age = 20; // The number 20 is stored directly inside 'age' on the Stack
```

#### Reference Variables / Objects (`String`, `Integer`, `SmartPhone`, etc.)

*   **What they are:** Variables that hold a **memory address (pointer)** pointing to the actual object sitting in the **Heap**.
    
*   **Has methods and can be null:** They can access methods and can be set to `null` (pointing to nothing).
    

```java
Integer count = new Integer(10); // 'count' lives on Stack, holding an address pointing to '10' in Heap
```

```plaintext
STACK MEMORY                           HEAP MEMORY
┌──────────────────────────┐           ┌──────────────────────────┐
│ int age = 20             │           │                          │
│                          │           │                          │
│ Integer count ───────────┼──────────>│ [Integer Object: 10]     │
└──────────────────────────┘           └──────────────────────────┘
```

### 2\. Wrapper Classes (`Integer`, `Long`, `Boolean`, etc.)

Java provides a **Wrapper Class** for every primitive type:

| Primitive Type | Wrapper Class (Reference Object) |
| --- | --- |
| `int` | `Integer` |
| `long` | `Long` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |

* * *

### 3\. Why Do BOTH Exist? (Pros & Cons)

If `Integer` can do everything `int` does, why keep primitives? **Performance.**

#### Primitives (`int`, `long`)

*   ✅ **Pros:** Extremely fast, uses very little memory (`int` = 4 bytes flat, no object header overhead).
    
*   ❌ **Cons:** Cannot be used in Java Collections (`ArrayList<int>` is invalid), cannot represent a missing/`null` value.
    

#### Wrapper Classes (`Integer`, `Long`)

*   ✅ **Pros:** Works with Collections (`ArrayList<Integer>`), supports utility methods (`Integer.parseInt()`), can be `null` (useful for database fields).
    
*   ❌ **Cons:** Much slower, takes ~16+ bytes of memory (12B header + 4B data + padding) vs. 4 bytes for primitive `int`.
    

* * *

### 4\. Autoboxing and Unboxing

Because primitives and Wrapper objects are different, Java provides automatic conversion between them so you don't have to write manual conversion code every time.

```plaintext
                 Autoboxing
    Primitive  ───────────────>  Wrapper Object
     (int)     <───────────────   (Integer)
                   Unboxing
```

*   Autoboxing: Primitive $\\rightarrow$ Wrapper Object (`Integer.valueOf()`)
    
*   Unboxing: Wrapper Object $\\rightarrow$ Primitive (`obj.intValue()`)
    

#### Autoboxing (Primitive $\\rightarrow$ Wrapper Object)

The automatic conversion that the Java compiler makes between a **primitive type** and its corresponding **Wrapper Object**.

*   Happens automatically when you assign a primitive value to a Wrapper reference or pass it into a method expecting an Object (like adding to an `ArrayList`).
    

```java
// Behind the scenes: Compiler converts this to Integer.valueOf(10)
Integer obj = 10; // Autoboxing (int primitive -> Integer object)

List<Integer> list = new ArrayList<>();
list.add(5); // Autoboxing (int primitive 5 -> Integer object)
```

#### Unboxing (Wrapper Object $\\rightarrow$ Primitive)

The reverse process: converting a **Wrapper Object** back to its corresponding **primitive type**.

*   Happens automatically when an object is used in mathematical operations (`+`, `-`, `*`), assigned to a primitive variable, or evaluated in conditions (`if`, `while`).
    

```java
Integer obj = new Integer(20);

// Behind the scenes: Compiler converts this to obj.intValue()
int num = obj; // Unboxing (Integer object -> int primitive)

int sum = obj + 5; // Unboxing happens to perform addition
```

#### Common Pitfall: `NullPointerException` during Unboxing

Since Wrapper Objects can be `null`, trying to unbox a `null` object into a primitive variable will crash your program at runtime with a `NullPointerException`.

```java
Integer count = null;

// ❌ RUNTIME ERROR! Throws NullPointerException
// Java tries to run count.intValue(), but 'count' is null!
int total = count;
```

## Call by Value vs. Call by Reference

Here is a absolute truth in Java that trips up even experienced developers:

> **Java is ALWAYS Call by Value. There is NO Call by Reference in Java.**

* * *

### What Happens When You Pass Arguments to a Function?

When you pass a variable to a method, Java **always creates a copy of the variable's value** and passes that copy into the function's parameter (on the Stack).

#### 1\. Passing Primitives (Pure Value Copy)

If you pass a primitive variable (`int`, `boolean`, etc.), Java copies the actual value. Changing the parameter inside the function **does not affect** the original variable.

```java
void modifyAge(int age) {
    age = 30; // Modifies local copy on method Stack frame
}

int originalAge = 20;
modifyAge(originalAge);
System.out.println(originalAge); // Output: 20 (Unchanged!)
```

#### 2\. Passing Objects / Reference Variables (Copy of the Reference Pointer)

When you pass an Object, people often mistake it for Call by Reference because changes inside the method reflect outside. However, Java is still passing by value! The value being copied and passed is the **memory address (reference pointer)**. Both the original reference variable and the method's parameter copy now **point to the exact same object in the Heap**.

```java
class SmartPhone {
    String brand = "Samsung";
}

class Main {
    static void updateBrand(SmartPhone phoneCopy) {
        phoneCopy.brand = "Apple"; // Modifies the actual object in Heap!
    }

    public static void main(String[] args) {
        SmartPhone myPhone = new SmartPhone();
        updateBrand(myPhone);
        System.out.println(myPhone.brand); // Output: Apple
    }
}
```

```plaintext
       STACK MEMORY                                      HEAP MEMORY
 ┌──────────────────────────────┐                 ┌──────────────────────┐
 │ myPhone (Ref Address: 0x101) ├────────────────>│ SmartPhone Object    │
 │                              │                 │                      │
 │ phoneCopy (Copy: 0x101)  ────┼────────────────>│ - brand: "Apple"     │
 └──────────────────────────────┘                 └──────────────────────┘
```

**Proof that Java is NOT Call by Reference:** If you reassign `phoneCopy = new SmartPhone()` inside `updateBrand()`, the original `myPhone` outside will NOT change to point to the new object.

## Shallow Copy vs. Deep Copy

When you want to duplicate an object, how far down do you copy its data? This depends on whether you perform a **Shallow Copy** or a **Deep Copy**.

* * *

### 1\. Shallow Copy (Surface Level)

A **Shallow Copy** creates a new object, but it simply copies the exact values of all fields from the original object:

*   Primitive fields get copied directly.
    
*   Reference fields (nested objects) get their **memory addresses copied**.
    

This means both the original object and the new copy will **share the same nested objects** in Heap memory.

```plaintext
     ORIGINAL OBJECT (HEAP)                        SHALLOW COPY (HEAP)
┌─────────────────────────────┐               ┌─────────────────────────────┐
│ ram = 8                     │               │ ram = 8                     │
│ batteryRef (0x101) ─────────┼──────┐ ┌──────┼─ batteryRef (0x101)         │
└─────────────────────────────┘      │ │      └─────────────────────────────┘
                                     ▼ ▼
                            ┌─────────────────────┐
                            │ Shared Battery      │
                            │ Address: 0x101      │
                            └─────────────────────┘
```

#### Code Example (Shallow Copy using Copy Constructor)

```java
class Battery {
    int capacity = 5000;
}

class SmartPhone {
    int ram = 8;
    Battery battery = new Battery();

    // Default Constructor
    SmartPhone() {}

    // Shallow Copy Constructor
    SmartPhone(SmartPhone original) {
        this.ram = original.ram;
        this.battery = original.battery; // Just copies the reference pointer!
    }
}

public class Main {
    public static void main(String[] args) {
        SmartPhone phone1 = new SmartPhone();
        
        // Create a shallow copy
        SmartPhone phone2 = new SmartPhone(phone1);

        // Modifying battery in phone2 will ALSO change phone1's battery!
        phone2.battery.capacity = 3000;

        System.out.println(phone1.battery.capacity); // Output: 3000 (Affected!)
    }
}
```

### 2\. Deep Copy (Completely Independent)

A **Deep Copy** creates a new object **AND manually creates new instances** for every nested object inside it.

*   Primitive fields are copied directly.
    
*   Reference fields point to brand new objects created on the Heap.
    
*   The copy and the original share zero memory addresses.
    

```plaintext
      ORIGINAL OBJECT (HEAP)                          DEEP COPY (HEAP)
 ┌─────────────────────────────┐               ┌─────────────────────────────┐
 │ ram = 8                     │               │ ram = 8                     │
 │ batteryRef (0x101) ─────────┼──┐         ┌──┼─ batteryRef (0x202)         │
 └─────────────────────────────┘  │         │  └─────────────────────────────┘
                                  ▼         ▼
                            ┌──────────┐ ┌──────────┐
                            │ Battery  │ │ Battery  │
                            │ (0x101)  │ │ (0x202)  │
                            └──────────┘ └──────────┘
```

#### Code Example (Deep Copy using Copy Constructors)

```java
class Battery {
    int capacity;

    Battery(int capacity) {
        this.capacity = capacity;
    }

    // Copy Constructor for Battery
    Battery(Battery originalBattery) {
        this.capacity = originalBattery.capacity;
    }
}

class SmartPhone {
    int ram;
    Battery battery;

    SmartPhone(int ram, int batteryCapacity) {
        this.ram = ram;
        this.battery = new Battery(batteryCapacity);
    }

    // Deep Copy Constructor for SmartPhone
    SmartPhone(SmartPhone original) {
        this.ram = original.ram;
        // Explicitly allocate a NEW Battery object on the Heap
        this.battery = new Battery(original.battery);
    }
}

public class Main {
    public static void main(String[] args) {
        SmartPhone phone1 = new SmartPhone(8, 5000);

        // Create a deep copy
        SmartPhone phone2 = new SmartPhone(phone1);

        // Modifying battery in phone2 will NOT affect phone1!
        phone2.battery.capacity = 3000;

        System.out.println(phone1.battery.capacity); // Output: 5000 (Safe & Unchanged!)
        System.out.println(phone2.battery.capacity); // Output: 3000
    }
}
```

| Feature | Shallow Copy | Deep Copy |
| --- | --- | --- |
| **Nested Objects** | Shared (pointers copied) | Completely new objects created |
| **Independence** | Modifying nested data affects both | Completely independent |
| **Speed & Memory** | Faster, uses less memory | Slower, allocates new Heap memory |
| **Best Used When** | Objects contain only primitives or immutable data | Objects contain nested, mutable references |

## The `static` Keyword in Java

In Java, the `static` keyword means **"belongs to the class itself, not to any individual object."**

When a member (variable or method) is declared `static`, it exists at the **class level**. It is shared across **all instances (objects)** created from that class.

* * *

### 1\. Static Variables (Class Variables)

*   **Class-Level Data:** Shared equally by every object of the class. If one object modifies a static variable, the change reflects across all objects.
    
*   **Where they live:** They are **NOT** stored inside individual object instances on the Heap. They live in a special memory area managed by JVM (Metaspace / Class Static Area).
    
*   **How to access:** Can be accessed using an object reference, but the **recommended way** is directly using the class name (`ClassName.variableName`).
    

```java
class SmartPhone {
    String model;             // Instance variable (Each phone has its own model)
    static String os = "Android"; // Static variable (Shared by ALL SmartPhone objects)

    SmartPhone(String model) {
        this.model = model;
    }
}

public class Main {
    public static void main(String[] args) {
        SmartPhone p1 = new SmartPhone("Pixel");
        SmartPhone p2 = new SmartPhone("Galaxy");

        // Recommended access using Class name
        System.out.println(SmartPhone.os); // Output: Android

        // Access via object also works, but points to the same shared variable
        p1.os = "Android 15";
        System.out.println(p2.os); // Output: Android 15
    }
}
```

### 2\. Static Methods

A static method belongs to the class and can be executed without creating any object of that class (e.g., `Math.sqrt()` or `main()`).

Because static methods run at the class level without requiring an object, they follow 3 strict rules:

*   **Rule 1:** **Static methods can ONLY directly call other static methods.**  
    **Reasoning:** A non-static method belongs to an instance and requires a specific object to execute. If a static method tries to call a non-static method without an object present, Java has no idea which object's method to run!
    
*   **Rule 2: Static methods can ONLY directly access static variables.**  
    **Reasoning:** Instance variables exist inside an object in Heap memory. Since a static method can be executed even when zero objects exist, accessing an instance variable would mean trying to read memory that hasn't been created yet.
    
*   **Rule 3: Static methods CANNOT use the** `this` **keyword.**  
    **Reasoning:** The this keyword represents the current object instance. Since static methods belong to the class and run without an instance, this has no object to refer to (this == null).
    

```java
class SmartPhone {
    String model = "Pixel";
    static String os = "Android";

    static void printInfo() {
        System.out.println(os); // ✅ Allowed (static accessing static)

        // System.out.println(model); 
        // ❌ COMPILE ERROR! Cannot make a static reference to the non-static field model

        // System.out.println(this.os); 
        // ❌ COMPILE ERROR! Cannot use 'this' in a static context

        // displayModel(); 
        // ❌ COMPILE ERROR! Cannot call non-static method directly
    }

    void displayModel() {
        System.out.println(model); // Non-static method accessing instance variable
    }
}
```

### 3\. Static Block

A **Static Initialization Block** is a block of code used to initialize static variables or execute class-level setup tasks.

*   **Runs Exactly Once:** Executes automatically when the class is **first loaded into JVM memory** by the ClassLoader.
    
*   **Runs Before Constructor:** Executes before any constructor runs, and even before any object is created.
    

```java
class SmartPhone {
    static String os;

    // Static Block
    static {
        os = "Android OS";
        System.out.println("1. Static block executed (Class loaded!)");
    }

    SmartPhone() {
        System.out.println("2. Constructor executed (Object created!)");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Main method started.");
        
        SmartPhone p1 = new SmartPhone();
        SmartPhone p2 = new SmartPhone();
    }
}
```

output:

```plaintext
Main method started.
1. Static block executed (Class loaded!)
2. Constructor executed (Object created!)
2. Constructor executed (Object created!)
```

> Notice how the static block runs only once when SmartPhone is first referenced, while the constructor runs for every new object.

### 4\. What Can and Cannot Be static?

| Component | Can be static? | Reasoning |
| --- | --- | --- |
| **Variables** | **YES** | Used to store shared, class-level state. |
| **Methods** | **YES** | Used for utility functions that don't need object state (e.g., `Math.max()`). |
| **Parameters** | **NO** | Method parameters are local variables created on the stack frame when a method is called. They only exist for that specific execution, making static meaningless. |
| **Top-Level Class** | **NO** | A top-level class is already at the package level. Making it static would be redundant because there is no outer instance enclosing it. |
| **Nested Class** | **YES** | A class defined inside another class can be static. |

> Nested Classes: A static nested class is an inner class that can be instantiated without needing an instance of the outer class. We will cover Nested Classes in detail later in this blog!

## The `final` Keyword in Java

In Java, the `final` keyword is used to **restrict modifications**. It acts as a lock on variables, methods, and classes:

*   **For Variables:** Makes the value constant (unmodifiable).
    
*   **For Methods:** Prevents method overriding. (Don't worry if this sounds unfamiliar right now—we will break down method overriding in detail when we reach the Polymorphism section later in this blog!)
    
*   **For Classes:** Prevents class inheritance. (If you're wondering how class relationships work, sit tight—we'll cover inheritance inside out in the upcoming Pillars of OOP section!)
    

* * *

### 1\. `final` Variables (Constants)

Once a `final` variable is assigned a value, it **cannot be reassigned**. Any attempt to change its value results in a compile-time error.

```java
public class Main {
    public static void main(String[] args) {
        final double PI = 3.14159;

        // PI = 3.14; 
        // ❌ COMPILE ERROR! Cannot assign a value to final variable 'PI'
    }
}
```

> Important Rule for Initialization: A final variable does not need to be initialized at the exact line of declaration, but it must be initialized before use (e.g., inside the constructor for instance variables). Once assigned, it becomes immutable.

### `static final` Variables (Class Constants)

When you combine static and final, you create a true global constant:

*   `static` $\\rightarrow$ Only one copy exists in memory, shared across all objects.
    
*   `final` $\\rightarrow$ Value cannot be changed.
    

```java
class Circle {
    // Shared by all Circle instances AND cannot be changed
    public static final double PI = 3.14159; 
}
```

### Why Naming Convention Uses ALL\_CAPS?

By standard Java naming convention, `static final` primitive variables (and immutable constants) are written in **ALL\_CAPITAL\_LETTERS** with words separated by underscores (`SNAKE_CASE`):

*   `Math.PI`
    
*   `Integer.MAX_VALUE`
    
*   `static final int MAX_RETRY_COUNT = 5;`
    

> Why? It gives an immediate visual signal to anyone reading your code that this variable is a compile-time constant and cannot be modified anywhere.

### 3\. What Can Be final?

| Target | Can be final? | Behavior & Reasoning |
| --- | --- | --- |
| **Variables** | **YES** | **Prevents Reassignment.** Primitive values cannot be changed. For object references, the reference pointer cannot point to a new object (though internal object state can still be modified). |
| **Parameters** | **YES** | **Prevents Reassignment inside Method.** The method parameter value cannot be modified within the function body, protecting input data from accidental mutation. |
| **Methods** | **YES** | **Prevents Overriding.** A final method cannot be overridden by child classes during inheritance. Used to preserve core security or implementation logic. |
| **Classes** | **YES** | **Prevents Inheritance.** A final class cannot be extended (`extends`) by any other class (e.g., Java's built-in `String` class is final for security and performance). |

**Code Example: final in Action**

```java
// 1. FINAL CLASS: Cannot be extended by any other class
final class DatabaseConfig {

    // 2. FINAL VARIABLE: Value can be assigned only once
    final String dbUrl = "jdbc:mysql://localhost:3306/my_db";
    
    // STATIC FINAL CONSTANT: Global constant shared across class
    public static final int MAX_CONNECTIONS = 100;

    // 3. FINAL METHOD: Cannot be overridden by child classes
    final void printConnectionStatus() {
        System.out.println("Connected to: " + dbUrl);
    }

    // 4. FINAL PARAMETER: 'timeoutInSeconds' cannot be modified inside the method
    void connect(final int timeoutInSeconds) {
        // timeoutInSeconds = 60; 
        // ❌ COMPILE ERROR! Cannot assign a value to final parameter 'timeoutInSeconds'

        System.out.println("Connecting with timeout: " + timeoutInSeconds + "s");
    }
}

// ❌ COMPILE ERROR! Cannot inherit from final class 'DatabaseConfig'
// class CustomConfig extends DatabaseConfig {}

public class Main {
    public static void main(String[] args) {
        DatabaseConfig config = new DatabaseConfig();

        // config.dbUrl = "jdbc:postgresql://localhost:5432/my_db";
        // ❌ COMPILE ERROR! Cannot reassign final variable 'dbUrl'

        config.connect(30);
        config.printConnectionStatus();
    }
}
```

## The 4 Pillars of Object-Oriented Programming

Welcome to the heart of modern software engineering!

Up until now, we’ve covered the fundamental mechanics of Java—how classes, objects, memory, constructors, and keywords (`static`, `final`) operate behind the scenes. Now, it's time to put those mechanics to work.

Object-Oriented Programming is built on **4 core pillars**. These aren't just features of Java—they are practical design principles that help us write clean, secure, reusable, and scalable code that mirrors the real world.

```plaintext
         ┌─────────────────────────────────────────────────────────┐
         │            THE 4 PILLARS OF OOP IN JAVA                 │
         └─────────────────────────────────────────────────────────┘
               │              │                │              │
               ▼              ▼                ▼              ▼
        ┌──────────────┐┌────────────┐  ┌─────────────┐┌──────────────┐
        │Encapsulation ││Inheritance │  │ Abstraction ││ Polymorphism │
        └──────────────┘└────────────┘  └─────────────┘└──────────────┘
```

### Quick Overview of the Pillars

Here is what each pillar means:

1.  **Encapsulation:** Bundling data (variables) and methods together into a single unit (a class) while restricting direct access to internal details for data protection.
    
2.  **Inheritance:** Allowing a child class to acquire properties and behaviors from a parent class to foster maximum code reuse.
    
3.  **Abstraction:** Hiding complex implementation details from the user and exposing only the essential operational features.
    
4.  **Polymorphism:** The ability of a single method, object, or action to take on multiple forms depending on the context.
    

### Pillar 1: Encapsulation

**Encapsulation** is the first pillar of Object-Oriented Programming. It literally means "enclosing into a capsule."

In Java, Encapsulation revolves around two core rules:

1.  **Bundling:** Storing data (variables) and the methods that operate on that data together inside a single unit (a class).
    
2.  **Data Hiding:** Protecting sensitive data from direct, unrestricted access or modification from outside the class.
    

> **Real-World Analogy:** Think of a capsule pill. The medicine inside is protected by the outer casing. You cannot touch the raw medicine directly—you interact with it through the pill as a single unit.

* * *

#### Why Unrestricted Access is Dangerous

If class fields are left open without protection, any external code can alter them to invalid or harmful states:

```java
class BankAccount {
    public double balance; // ❌ Unrestricted public access
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.balance = -10000; // Dangerous! Balance changed directly to a negative number.
    }
}
```

To prevent this, we hide data using Access Modifiers and expose controlled interactions via Getters and Setters.

#### Understanding Packages in Java

Before diving into Access Modifiers, let's clarify what a Package is:

> A Package in Java is simply a folder that groups related classes and interfaces together (e.g., package com.app.banking;). It helps organize code and prevents naming collisions between classes.

#### Access Modifiers in Java

Access Modifiers control **who can access** a class variable, method, or constructor.  
Here are the 4 Access Modifiers ordered from **Most Restrictive $\\rightarrow$ Most Permissive:** $$\\text{private} \\longrightarrow \\text{default} \\longrightarrow \\text{protected} \\longrightarrow \\text{public}$$

1.  `private`: Accessible **ONLY inside the same class.** Outside classes cannot see or touch private members.
    
2.  `default` **(package-private)**: Used when no modifier keyword is written. Accessible by **any class in the same package**, but invisible outside the package.
    
3.  `protected`: Accessible by **any class in the same package** AND **by child (inherited) classes in different packages**.
    
4.  `public`: Accessible **from anywhere in the project** across all packages.
    

| Access Modifier | Same Class | Same Package | Subclass (Outside Package) | World (Any Package) |
| --- | --- | --- | --- | --- |
| **private** | ✅ | ❌ | ❌ | ❌ |
| **default** | ✅ | ✅ | ❌ | ❌ |
| **protected** | ✅ | ✅ | ✅ | ❌ |
| **public** | ✅ | ✅ | ✅ | ✅ |

##### Code Example: Implementing Proper Encapsulation

To implement encapsulation properly:

*   Make instance variables private.
    
*   Provide public getter and setter methods to validate and control access.
    

```java
class BankAccount {
    // 1. Data Hiding (Private variables)
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    // 2. Getter (Controlled Read Access)
    public double getBalance() {
        return this.balance;
    }

    // 3. Setter with Validation (Controlled Write Access)
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC12345", 500.0);

        // acc.balance = -1000; 
        // ❌ COMPILE ERROR! balance has private access in BankAccount

        // Access data securely through public methods
        acc.deposit(200.0);
        System.out.println("Current Balance: $" + acc.getBalance());
    }
}
```

#### What is a Package in Java?

A **Package** in Java acts like a folder in your computer's file system that groups related classes, interfaces, and sub-packages together. Packages solve two major problems in software development: they prevent **naming conflicts** (e.g., two different developers can create a class named `User` as long as they live in different packages) and they enforce **access protection** by controlling which classes can see and interact with one another.

* * *

#### Types of Packages

In Java, packages are broadly divided into two categories:

1.  **Built-in Packages (Standard Java Library):** These are pre-written packages provided by Java to handle common tasks out of the box.
    
    *   `java.util`: Contains utility classes like `Scanner`, `ArrayList`, and `HashMap`.
        
    *   `java.io`: Handles input/output operations like reading and writing files.
        
    *   `java.lang`: Contains core fundamental classes like `String`, `Math`, and `System` (this package is automatically imported in every Java file).
        
2.  **User-Defined Packages:** These are custom packages created by developers to organize their own project code cleanly into logical modules.
    
    *   Created using the `package` keyword at the very top of a Java file (e.g., `package com.mycompany.banking;`).
        
    *   To use a class from another package, you import it using the `import` keyword (e.g., `import com.mycompany.banking.BankAccount;`).
        

### Pillar 2: Inheritance

**Inheritance** is a mechanism in Java where one class (child or subclass) acquires the properties (fields) and behaviors (methods) of another class (parent or superclass).

It establishes an **IS-A relationship** between objects. For example:

*   A `Dog` **IS-A** `Animal`
    
*   A `SmartPhone` **IS-A** `ElectronicDevice`
    

* * *

#### Why Do We Need Inheritance?

1.  **Code Reusability:** You write common fields and methods once in a parent class and automatically reuse them across all child classes without duplicating code.
    
2.  **Supports Polymorphism:** Inheritance lets you treat a child object as an instance of its parent class, enabling dynamic method dispatch at runtime (e.g., calling `animal.makeSound()` works whether `animal` holds a `Dog` or a `Cat`).
    

* * *

#### The `extends` Keyword & `final` Constraint

In Java, inheritance is achieved using the `extends` keyword.

```java
// Parent Class (Superclass)
class Vehicle {
    double speed;

    void startEngine() {
        System.out.println("Engine started...");
    }
}

// Child Class (Subclass) inherits speed and startEngine()
class Car extends Vehicle {
    int numberOfDoors = 4;
}

public class Main{
    public static void main(String[] args){
        Car c1 = new Car();

        // inherited parent class method and attributes
        c1.speed = 47.3;
        System.out.println(c1.speed);
        c1.startEngine(); 

    }
}
```

output:

```plaintext
47.3
Engine started...
```

> Crucial Rule: A final class cannot be inherited. If you declare final class Vehicle, no other class can extend it.

#### Types of Inheritance in Java

```plaintext
1. Single               2. Multi-Level            3. Hierarchical
   [ A ]                   [ A ]                       [ A ]
     │                       │                        ┌──┴──┐
     ▼                       ▼                        ▼     ▼
   [ B ]                   [ B ]                    [ B ] [ C ]
                             │
                             ▼
                           [ C ]
```

#### 1\. Single Inheritance

A single child class extends a single parent class.

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking..."); }
}

public class Main{
    public static void main(String[] args){
        Dog d1 = new Dog();

        d1.eat();    // Eating...
        d1.bark();  // Barking...
    }
}
```

```plaintext
   [ Animal ]                
       │                  
       ▼         
    [ Dog ]   
```

#### 2\. Multi-Level Inheritance

A child class inherits from a parent class, which itself inherits from another class (forming a chain).

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking..."); }
}

// inherit the method and attributes from both Animal and Dog
class Puppy extends Dog {
    void weep() { System.out.println("Weeping..."); }
}
```

```plaintext
   [ Animal ]  
       │    
       ▼                           
    [ Dog ]                        
       │
       ▼
   [ Puppy ]
```

#### 3\. Hierarchical Inheritance

Multiple child classes extend the exact same parent class.

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking..."); }
}

class Cat extends Animal {
    void meow() { System.out.println("Meowing..."); }
}
```

```plaintext
      [Animal]
      ┌──┴──┐
    [Dog] [Cat]
```

#### Multiple Inheritance & The Diamond Problem

Java **does NOT support Multiple Inheritance using classes**(a class cannot extend more than one class at the same time: `class C extends A, B`❌).

##### Why? The Diamond Problem

If Class `A` has a method `show()`, and both Class `B` and Class `C` override `show()`, what happens if Class `D` inherits from both `B` and `C`?

```plaintext
         [ A ] (show)
        ╱     ╲
       ╱       ╲
   [ B ]       [ C ]
  (show)       (show)
       ╲       ╱
        ╲     ╱
         [ D ]  <-- Which show() should D run? Compiler Confusion!
```

When `D.show()` is called, the compiler cannot decide whether to execute `B's` version or `C's` version. To prevent this ambiguity, Java disables multiple inheritance for classes.

> Note: Multiple inheritance in Java is achieved safely using Interfaces, which we will cover in detail later in this blog!

#### The super Keyword in Java

The `super` keyword is a reference variable used to refer directly to the immediate parent class object.

##### 3 Main Uses of super

1.  To Access Parent Class Variable (Handling Variable Shadowing)  
    Used when a child class defines an instance variable with the exact same name as a variable in the parent class.
    

```java
class Vehicle {
    int maxSpeed = 120;
}

class Car extends Vehicle {
    int maxSpeed = 200; // Variable Shadowing

    void displaySpeeds() {
        System.out.println("Child maxSpeed: " + maxSpeed);       // Output: 200
        System.out.println("Parent maxSpeed: " + super.maxSpeed); // Output: 120
    }
}
```

2.  To Access Parent Class Method (Handling Method Overriding)  
    Used when a child class overrides a parent method, but still wants to execute the parent's original implementation.
    

```java
class Vehicle {
    void displayInfo() {
        System.out.println("Vehicle information");
    }
}

class Car extends Vehicle {
    void displayInfo() {
        super.displayInfo(); // Calls parent's displayInfo() first
        System.out.println("Car specific information");
    }
}
```

3.  Call Parent Class Constructor (`super()`)  
    Used to invoke the parent class constructor from inside the child class constructor.
    

> Automatic Behavior: If you don't explicitly write super(), the Java compiler automatically inserts a hidden super() call as the very first line of every child constructor!

```java
class Vehicle {
    String type;

    Vehicle(String type) {
        this.type = type;
        System.out.println("Vehicle constructor called");
    }
}

class Car extends Vehicle {
    int doors;

    Car(String type, int doors) {
        super(type); // Must be the FIRST line in child constructor
        this.doors = doors;
        System.out.println("Car constructor called");
    }
}
```

### Pillar 3: Polymorphism

The word **Polymorphism** comes from Greek:

*   **Poly** = Many
    
*   **Morph** = Forms
    

In Object-Oriented Programming, Polymorphism is the ability of a single method, operator, or object to **behave differently based on the context in which it is used**.

> **Real-World Analogy:** Think of a person. You can act as a **student** in college, a **customer** in a shop, and a **son/daughter** at home. You are the same person, but you exhibit different behaviors depending on the situation!

#### The 2 Types of Polymorphism in Java

```plaintext
                  ┌─────────────────────────────────┐
                  │          POLYMORPHISM           │
                  └─────────────────────────────────┘
                                   │
            ┌──────────────────────┴──────────────────────┐
            ▼                                             ▼
┌───────────────────────────┐                 ┌───────────────────────────┐
│ Compile-Time Polymorphism │                 │   Runtime Polymorphism    │
│     (Static / Early)      │                 │     (Dynamic / Late)      │
└─────────────┬─────────────┘                 └─────────────┬─────────────┘
              │                                             │
              ▼                                             ▼
   [ Method Overloading ]                         [ Method Overriding ]
```

#### 1\. Compile-Time Polymorphism (Static / Early Binding)

**Compile-Time Polymorphism** occurs when the decision of *which method to execute* is made by the compiler at compile time. It is achieved using **Method Overloading**.

##### Method Overloading

A class can have multiple methods with the **exact same name**, as long as their parameter lists are different (either in number of parameters, types of parameters, or order of parameters).

```java
class Calculator {
    // Method 1: Adds two integers
    int add(int a, int b) {
        return a + b;
    }

    // Method 2: Overloaded - Adds three integers
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method 3: Overloaded - Adds two doubles
    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Compiler knows exactly which method to bind based on the arguments
        System.out.println(calc.add(5, 10));        // Calls Method 1 -> Output: 15
        System.out.println(calc.add(5, 10, 15));    // Calls Method 2 -> Output: 30
        System.out.println(calc.add(2.5, 3.5));     // Calls Method 3 -> Output: 6.0
    }
}
```

> Why is it called Compile-Time? Because the JVM doesn't need to guess at runtime. The Java compiler reads the argument signatures and links the exact method call during compilation.

#### 2\. Runtime Polymorphism (Dynamic / Late Binding)

**Runtime Polymorphism** occurs when the decision of which overridden method to execute is resolved at runtime based on the actual object created in Heap memory. It is achieved using **Method Overriding**.

##### Method Overriding

When a child class provides a specific implementation for a method that is already defined in its parent class (with the exact **same name, return type, and parameter list**).

```java
class Animal {
    void sleep() {
        System.out.println("Animal is sleeping...");
    }
}

class Dog extends Animal {
    @Override
    void sleep() {
        System.out.println("Dog is sleeping...");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();

        // Calling overridden method
        myDog.sleep(); // Output: Dog is sleeping...
    }
}
```

##### Upcasting & Dynamic Method Dispatch

When a parent class reference variable points to a child class object (Upcasting), Java decides which method to run based on the actual object in Heap, not the reference type!

```java
class Payment {
    void processPayment() {
        System.out.println("Processing generic payment...");
    }
}

class CreditCardPayment extends Payment {
    @Override
    void processPayment() {
        System.out.println("Processing payment via Credit Card...");
    }
}

class UPIPayment extends Payment {
    @Override
    void processPayment() {
        System.out.println("Processing payment via UPI...");
    }
}

public class Main {
    public static void main(String[] args) {
        // Parent reference pointing to Child objects (Upcasting)
        Payment p1 = new CreditCardPayment();
        Payment p2 = new UPIPayment();

        // Dynamic Method Dispatch (Resolved at Runtime)
        p1.processPayment(); // Output: Processing payment via Credit Card...
        p2.processPayment(); // Output: Processing payment via UPI...
    }
}
```

#### Critical Rules & Exceptions in Method Overriding

Not everything in Java can participate in Runtime Polymorphism. Here are the 4 major rules and the reasoning behind each:

*   **Rule 1:** `static` methods CANNOT be overridden (Method Hiding instead)
    
    *   `Reasoning`: Method overriding relies on dynamic dispatch on an object instance stored in the Heap. Since static methods belong to the class itself and are resolved at compile time using class references, they cannot be dynamically dispatched at runtime. If a child class defines a static method with the same signature, it simply hides the parent's static method.
        
*   **Rule 2:** `private` methods CANNOT be overridden
    
    *   `Reasoning`: Overriding requires the child class to be aware of the parent method. Since `private` members are completely hidden and invisible outside their own class, child classes cannot see them to override them.
        
*   **Rule 3:** `final` methods CANNOT be overridden
    
    *   `Reasoning`: The `final` keyword explicitly locks a method's implementation to prevent any subclass from altering its behavior.
        
*   **Rule 4:** Instance Variables / Fields CANNOT exhibit Polymorphism
    
    *   `Reasoning`: Polymorphism in Java applies ONLY to methods, not fields/variables. Variables are resolved at compile time based solely on the reference type, not the underlying object in Heap memory.
        

```java
class Parent {
    int value = 10;
}

class Child extends Parent {
    int value = 20; // Variable Shadowing
}

public class Main {
    public static void main(String[] args) {
        Parent obj = new Child(); 
        
        // Fields are NOT polymorphic! Evaluated by Reference Type (Parent)
        System.out.println(obj.value); // Output: 10 (Not 20!)
    }
}
```

### Pillar 4: Abstraction

**Abstraction** is the fourth pillar of Object-Oriented Programming, and it is often considered the most confusing. Let's make it crystal clear.

Abstraction is the practice of **focusing on WHAT an object does, while hiding HOW it actually does it**.

> **Real-World Analogy:** When you step on the gas pedal of a car, you know **what** happens: the car speeds up. You don't need to know **how** the fuel injectors, spark plugs, and pistons work under the hood to drive it. The complex mechanical details are *abstracted away* behind a simple interface (the pedal).

* * *

#### Low-Level vs. High-Level Abstraction in Java

1.  **Low-Level Abstraction (Data Level):**
    
    *   Done using **Encapsulation** (making fields `private` and using getters/setters).
        
    *   Hides internal object variables from direct outside tampering.
        
2.  **High-Level Abstraction (Behavioral Level):**
    
    *   Done using **Abstract Classes** and **Interfaces**.
        
    *   Completely separates the **"WHAT"** (the contract or idea) from the **"HOW"** (the actual code execution).
        

##### Why Normal (Concrete) Classes Are Tightly Coupled

In a regular concrete class, the method declaration (the "WHAT") and the method implementation (the "HOW") are glued together:

```java
class Car {
    void accelerate() {
        // 'WHAT' (accelerate) and 'HOW' (internal combustion math) are locked together!
        System.out.println("Injecting fuel into cylinders..."); 
    }
}
```

If you ever want an ElectricCar to accelerate differently using batteries, a standard concrete class forces you to overwrite existing code or create messy conditionals. High-level abstraction solves this by separating the declaration from the implementation.

#### 1\. Abstract Class (Partial Blueprint for a Family of Things)

An **Abstract Class** is a class declared with the `abstract` keyword. It serves as a partial template for a **family of closely related objects** (e.g., `Dog`, `Cat`, `Lion` all belonging to the family Animal).

It can contain both abstract methods (method declarations with no body) and concrete methods (methods with actual code bodies).

##### Core Rules of Abstract Classes

| Feature / Rule | Behavior & Reasoning |
| --- | --- |
| **Cannot Be Instantiated** | You cannot use `new Animal()`. Since abstract classes contain incomplete methods (no bodies), creating an object directly from them would result in undefined behavior. |
| **Meant to Be Extended** | An abstract class exists solely to be inherited (`extends`) by concrete child classes. |
| **Constructors Allowed?** | **YES**. Even though you cannot instantiate an abstract class directly, it can have a constructor. It runs when a child class object is created via `super()`. |
| **Can it be final?** | **NO**. `abstract` demands inheritance, while `final` prevents inheritance. Combining `abstract final` causes a compile error. |
| **static Members?** | **YES**. It can have static variables, static methods, and static blocks that belong to the class level. |
| **private Methods?** | **YES**, for helper methods inside the class. However, an **abstract method CANNOT be private** because child classes must see it to override it! |
| **final Methods?** | **YES**, for concrete methods you want to prevent child classes from changing. However, an **abstract method CANNOT be final** because it must be overridden! |
| **Multiple Inheritance?** | **NO**. A class can extend only one abstract class at a time (standard Java single-inheritance limitation). |

##### Code Example: Abstract Class

```java
// Abstract Class (Family Blueprint)
abstract class BankAccount {
    String accountHolder;

    // 1. Constructor (Used by child classes)
    BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    // 2. Concrete Method (Common "HOW" shared by ALL bank accounts)
    void printReceipt() {
        System.out.println("Receipt printed for: " + accountHolder);
    }

    // 3. Abstract Method (Only "WHAT" - Each bank account calculates interest differently)
    abstract void calculateInterest();
}

// Concrete Child Class 1
class SavingsAccount extends BankAccount {
    SavingsAccount(String name) {
        super(name);
    }

    // MUST define the abstract method, otherwise compile-time error!
    @Override
    void calculateInterest() {
        System.out.println("Calculating 4% interest for Savings Account.");
    }
}

// Concrete Child Class 2
class FixedDepositAccount extends BankAccount {
    FixedDepositAccount(String name) {
        super(name);
    }

    @Override
    void calculateInterest() {
        System.out.println("Calculating 7.5% interest for Fixed Deposit.");
    }
}
```

#### 2\. Interface (A Pure Contract / Capability)

An **Interface** in Java is a 100% pure specification of behavior (until Java 8). It defines **WHAT** an object can do, without saying anything about **HOW** it does it.  
While Abstract Classes represent **identities** ("IS-A" relationship within a family), Interfaces represent **roles, capabilities, or behaviors** (often named as adjectives ending in `-able: Flyable, Cloneable, Runnable, Payable`).

> **Analogy**: A Bird `IS-A` Animal (Abstract Class). But both an Airplane and a Bird can implement `Flyable` (Interface), even though they are completely unrelated objects!

##### Core Rules of Interfaces

| Feature / Rule | Behavior & Reasoning |
| --- | --- |
| **Pure "WHAT" Contract** | All declared methods are implicitly `public abstract` by default. |
| **No Instance Fields** | Cannot hold normal variables. Any variables declared in an interface are implicitly `public static final` (global constants). |
| **No Constructors** | Since interfaces hold zero instance state, they have no constructors and cannot be instantiated with `new`. |
| **Default Methods (Java 8+)** | Interfaces can now have methods with bodies using the `default` keyword. Introduced so developers could add new features to existing interfaces without breaking legacy code that implemented them! |
| **Multiple Inheritance Supported** | A single class can implement multiple interfaces (e.g., `implements Flyable, Swimmable`). |

##### How Interfaces Solve the Diamond Problem in Multiple Inheritance

If a class implements two interfaces (`A` and `B`) that declare the exact same method signature, there is no ambiguity because neither interface provides code execution—the implementing class provides the single, unified implementation body!

Even if both interfaces contain identical `default` methods, Java forces the implementing class to explicitly override and resolve the conflict:

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

// A Duck can do BOTH roles!
class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck is flying in the sky.");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming in water.");
    }
}
```

#### Comparison Table: Abstract Class vs. Interface

| Feature | Abstract Class | Interface |
| --- | --- | --- |
| **Core Concept** | Partial blueprint for a family of related things ("IS-A"). | A contract of capabilities or roles ("CAN-DO"). |
| **Inheritance Keyword** | Extended using `extends`. | Implemented using `implements`. |
| **Multiple Inheritance** | **No**. A class can extend only 1 abstract class. | **Yes**. A class can implement multiple interfaces. |
| **Methods** | Can have abstract, concrete, static, and final methods. | Abstract by default. (Can have `default` & `static` methods since Java 8). |
| **Fields / Variables** | Can have instance variables (`private`, `protected`, etc.). | Only `public static final` constants. |
| **Constructor** | **Yes**, has constructors called by subclasses via `super()`. | **No**, cannot have constructors. |
| **Access Modifiers** | Methods can be `public`, `protected`, or `private`. | All methods are implicitly `public` by default. |
| **Performance** | Slightly faster (direct class hierarchy lookup). | Slightly slower due to dynamic interface lookup tables. |

> Use an Abstract Class when you have a core base identity shared by closely related classes that need to share common state (variables) and code execution (concrete methods).

> Use an Interface when you want to define a peripheral role, capability, or contract that can be applied to completely unrelated classes across your application.

## Java-Specific Object Rules & Design Constructs

Beyond the four core pillars of OOP, Java includes specific rules, root utilities, and design constructs that shape how objects are structured, stored, and managed in memory.

* * *

### 1\. Why Only One `public` Class Per `.java` File?

In Java, you can define multiple classes inside a single `.java` file, but **only ONE top-level class can be marked** `public`, and the file name **MUST match that public class name exactly** (case-sensitive).

For example, if your public class is named `SmartPhone`, the file name must be `SmartPhone.java`.

#### The Core Reasons

1.  **JVM & ClassLoader Fast-Lookup:** When you compile a `.java` file, `javac` generates a separate `.class` bytecode file for **every single class** inside it. At runtime, the ClassLoader locates bytecode using package directory paths. Matching the file name to the public class lets the JVM find and load classes instantly without scanning the entire file contents.
    
2.  **Clean Project Structure:** In large projects, finding where a public component lives would be difficult if public classes could hide inside arbitrarily named files. The rule guarantees a 1:1 match between class names and file names.
    
3.  **API Boundaries:** A `public` class forms part of your package's exposed interface. Enforcing one public class per file keeps major architectural components modular and easy to navigate.
    

```java
// File Name: SmartPhone.java

// 1. PUBLIC CLASS: Only ONE allowed per file. File MUST be SmartPhone.java
public class SmartPhone {
    private String brand;
    private Battery battery = new Battery();

    public SmartPhone() {
        System.out.println("SmartPhone created!");
    }
}

// 2. DEFAULT / PACKAGE-PRIVATE CLASS: Multiple allowed in the same file!
class Battery {
    int capacity = 5000;
}

// ❌ COMPILE ERROR IF ADDED IN THIS FILE:
// public class Camera {} 
// "The public type Camera must be defined in its own file named Camera.java"
```

### 2\. POJO (Plain Old Java Object) Class

A **POJO** is an ordinary Java class that has no special framework dependencies—it does not extend framework classes, implement framework interfaces, or require external annotations.

It serves as a simple data container to hold state and transfer data cleanly across application layers.

##### Characteristics of a Standard POJO:

*   Instance fields are `private` (Encapsulation).
    
*   Provides `public` **Getters and Setters** for controlled field access.
    
*   Provides a **No-Argument Constructor**.
    
*   Contains no framework-specific boilerplate or code
    

```java
// Standard POJO Class
public class UserPOJO {
    private String name;
    private int age;

    // Default No-Arg Constructor
    public UserPOJO() {}

    // Parameterized Constructor
    public UserPOJO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
```

### 3\. The java.lang.Object Class (Root of All Classes)

In Java, **every single class directly or indirectly inherits from** `java.lang.Object`.  
Even if you write `class SmartPhone {}`, the Java compiler automatically converts it to:

```java
class SmartPhone extends Object {}
```

Because of this universal inheritance, every object in Java inherits a set of core built-in methods provided by `Object`.

```plaintext
                            ┌───────────────────────────────┐
                            │     java.lang.Object Class    │
                            └───────────────┬───────────────┘
                                            │
         ┌──────────────────┬───────────────┼───────────────┬──────────────────┐
         ▼                  ▼               ▼               ▼                  ▼
  [ String Output ]   [ Comparison ]   [ Metadata ]   [ GC Cleanup ]   [ Concurrency ]
   • toString()        • equals()       • getClass()   • finalize()     • wait()
                       • hashCode()     • clone()                       • notify()
                                                                        • notifyAll()
```

#### Key Methods Provided by the Object Class

| Category | Method | Default Behavior |
| --- | --- | --- |
| **String Representation** | `toString()` | Returns `ClassName@HexHashCode` (e.g., `SmartPhone@15db9742`). Override to display meaningful field values. |
| **Comparison** | `equals(Object obj)` | Compares memory addresses (`==`). Override for logical field equality. |
| **Hashing** | `hashCode()` | Returns a numerical hash value based on memory location by default. Used in collections like `HashMap`. |
| **Metadata** | `getClass()` | Returns the runtime `Class` type of the object. |
| **Garbage Collection** | `finalize()` | *(Deprecated)* Called by GC before destroying an unreferenced object. |
| **Cloning** | `clone()` | Creates a shallow field-for-field copy of the object (requires implementing the `Cloneable` interface). |
| **Concurrency** | `wait()`, `notify()`, `notifyAll()` | Low-level thread synchronization mechanisms built onto object monitor locks. |

#### Why Must We Override `equals()` and `hashCode()` for Custom Classes?

By default, the `Object` class implementation of `equals()` **uses reference equality** (`==`). It checks whether two reference variables point to the **exact same memory address on the Heap**.

```java
class Student {
    int id;
    Student(int id) { this.id = id; }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(101);
        Student s2 = new Student(101);

        // Default Object.equals() compares Heap memory addresses!
        System.out.println(s1.equals(s2)); // Output: false (Even though IDs match!)
    }
}
```

##### ❌ The Problem with Default `Object` Methods

Here is how the `java.lang.Object` class implements these methods behind the scenes:

```java
// Default implementation inside java.lang.Object
public class Object {

    public boolean equals(Object obj) {
        return (this == obj); // Compares MEMORY ADDRESSES, not field values!
    }

    public native int hashCode(); // Returns internal memory address hash!
}
```

##### The `equals()` & `hashCode()` Contract

If two objects are logically equal (their field values match), they **MUST return true for** `equals()` **AND return the exact same** `hashCode()` **integer.**

If you override `equals()` but forget to override `hashCode()`, hash-based collections like `HashMap` or `HashSet` will place identical objects into different buckets and fail to retrieve them!

```java
import java.util.Objects;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Overriding equals() for logical field comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Same memory reference
        if (o == null || getClass() != o.getClass()) return false; // Null or different class
        
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name);
    }

    // Overriding hashCode() to match logical equality
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

### 4\. Nested Classes in Java

A **Nested Class** is a class defined inside an outer enclosing class. Nested classes group logically related components together, improving readability and encapsulation.

```plaintext
                             ┌───────────────────────────────┐
                             │         NESTED CLASSES        │
                             └───────────────┬───────────────┘
                                             │
                      ┌──────────────────────┴──────────────────────┐
                      ▼                                             ▼
            [ Static Nested Class ]                       [ Inner Classes ]
                                                                    │
                                           ┌────────────────────────┼────────────────────────┐
                                           ▼                        ▼                        ▼
                                 [ Member Inner Class ]   [ Local Inner Class ]   [ Anonymous Inner Class ]
```

#### 1\. Static Nested Class

Declared with the `static` keyword inside an outer class. It **cannot directly access non-static fields or methods** of the outer class—it acts like a regular top-level class scoped inside the outer namespace for organizational convenience.

```java
class Outer {
    static int outerData = 10;

    static class StaticNested {
        void display() {
            System.out.println("Static Outer Data: " + outerData);
        }
    }
}

// Instantiated independently without creating an Outer instance:
Outer.StaticNested nested = new Outer.StaticNested();
```

#### 2\. Member Inner Class (Non-Static)

A non-static class defined directly inside an outer class. It has full access to all outer class fields and methods (including `private` ones).

```java
class Outer {
    private String secret = "Top Secret";

    class Inner {
        void reveal() {
            System.out.println("Outer Secret: " + secret); // Direct access to private fields!
        }
    }
}

// Requires an Outer class instance to instantiate:
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
```

#### 3\. Local Inner Class

Defined inside a method body or code block. Its scope is restricted entirely to that method execution.

```java
class Outer {
    void process() {
        // Class scoped inside method execution
        class LocalPrinter {
            void print() {
                System.out.println("Printing from Local Inner Class...");
            }
        }

        LocalPrinter lp = new LocalPrinter();
        lp.print();
    }
}
```

#### 4\. Anonymous Inner Class

An inner class **without a name** declared and instantiated at the exact same time. Used to create a quick, one-time inline implementation of an interface or class method without writing a separate subclass file.

```java
abstract class Greeting {
    abstract void greet();
}

public class Main {
    public static void main(String[] args) {
        // Anonymous Inner Class implementing 'Greeting' on the fly
        Greeting g = new Greeting() {
            @Override
            void greet() {
                System.out.println("Hello from Anonymous Class!");
            }
        };
        
        g.greet();
    }
}
```

## Advanced Interface Types

Beyond standard contracts, Java features two special types of interfaces: **Functional Interfaces** and **Marker Interfaces**.

* * *

### 1\. Functional Interface (Single Abstract Method - SAM)

A **Functional Interface** is an interface that contains **EXACTLY ONE abstract method**. It can contain any number of `default` or `static` methods, but only one un-implemented abstract method.

It is marked with the `@FunctionalInterface` annotation (optional, but forces the compiler to throw an error if someone accidentally adds a second abstract method).

#### Why Are Functional Interfaces Important?

They are the foundation of **Functional Programming in Java**! Functional interfaces allow us to use **Lambda Expressions (**`->`**)**, letting us pass executable code/behavior directly as method arguments instead of writing clunky anonymous inner classes.

```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b); // Single Abstract Method (SAM)
}

public class Main {
    public static void main(String[] args) {
        // 1. Old Anonymous Class Way (Verbose)
        Calculator addOld = new Calculator() {
            public int operate(int a, int b) { return a + b; }
        };

        // 2. Modern Lambda Expression Way (Clean & Concise!)
        Calculator addLambda = (a, b) -> a + b;

        System.out.println(addLambda.operate(10, 20)); // Output: 30
    }
}
```

##### Common Built-in Functional Interfaces:

*   `Comparable<T>` $\\rightarrow$ Contains `compareTo()`
    
*   `Runnable` $\\rightarrow$ Contains `run()`
    
*   `Callable<V>` $\\rightarrow$ Contains `call()`
    

### 2\. Marker Interface (Tagging Interface)

A **Marker Interface** is an interface that contains **ZERO methods and ZERO fields**.

It is used as a "tag" or "marker" to signal metadata to the JVM or compiler that a class possesses a special capability or permission.

```java
// Definition of a Marker Interface (Completely Empty!)
public interface Serializable {
    // No fields, no methods!
}
```

#### Famous Built-in Marker Interfaces:

1.  `Cloneable`: Signals to `Object.clone()` that this object is allowed to be copied field-by-field. Calling `clone()` on a class that doesn't implement `Cloneable` throws `CloneNotSupportedException`.
    
2.  `Serializable`: Signals to the JVM that an object's state can be flattened into a byte stream and saved to a file or sent across a network.
    
3.  `RandomAccess`: Used by collection classes (like `ArrayList`) to indicate that they support ultra-fast, constant-time $O(1)$ random positional index lookup.
    

#### How Marker Interfaces Work Under the Hood

The JVM checks for marker capabilities at runtime using the `instanceof` operator:

```java
SmartPhone phone = new SmartPhone();

if (phone instanceof Cloneable) {
    // JVM permits object cloning
} else {
    throw new CloneNotSupportedException();
}
```

## Appendix: Type Casting & Memory Lookup Rules

To write clean OOP code, you need to understand how Java casts objects across class hierarchies and how memory handles variables vs. methods.

### 1\. Upcasting vs. Downcasting

Type casting between objects in an inheritance hierarchy is called Object Type Casting.

```plaintext
                        [ Parent Class ]
                             ▲   │
                  Upcasting  │   │ Downcasting
                             │   ▼
                        [ Child Class ]
```

#### Upcasting (Child $\\rightarrow$ Parent)

Casting a child object reference to a parent class reference type.

*   **Automatic / Safe:** Done implicitly by Java without explicit casting.
    
*   **Core of Polymorphism:** Allows parent references to point to any child object in Heap memory.
    

```java
class Animal {
    void makeSound() { System.out.println("Some sound..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Woof!"); }
}

public class Main {
    public static void main(String[] args) {
        // Upcasting: Dog object assigned to Animal reference
        Animal animal = new Dog(); // Safe & Implicit
        
        animal.makeSound(); // Output: Some sound...
        // animal.bark(); ❌ COMPILE ERROR! Parent reference can only see methods defined in Animal.
    }
}
```

#### Downcasting (Parent $\\rightarrow$ Child)

Casting a parent reference back down to a child class type.

*   **Explicit / Risky:** Must be written explicitly with (`ChildClass`).
    
*   **Requires Safety Check:** If the object in Heap is NOT actually an instance of that child class, Java throws a runtime `ClassCastException`.
    

```java
public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog(); // Upcasted

        // Downcasting explicitly back to Dog reference
        if (animal instanceof Dog) {
            Dog myDog = (Dog) animal; // Explicit Downcasting
            myDog.bark(); // ✅ Works! Output: Woof!
        }
    }
}
```

### 2\. Variable Shadowing vs. Method Overriding (Memory Resolution)

**What happens when both the Parent and Child classes have fields and methods with the same name?**

##### Golden Rule:

*   **Methods** are resolved dynamically at **RUNTIME based on the actual object in Heap** (Polymorphic / Overriding).
    
*   **Variables / Fields** are resolved at **COMPILE TIME based on the reference type on Stack** (Variable Shadowing / Not Polymorphic).
    

```java
class Parent {
    int x = 10; // Variable

    void display() {
        System.out.println("Parent Method");
    }
}

class Child extends Parent {
    int x = 20; // Variable Shadowing!

    @Override
    void display() {
        System.out.println("Child Method");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent obj = new Child(); // Upcasting

        // 1. Field Access -> Evaluated by REFERENCE TYPE (Parent on Stack)
        System.out.println(obj.x); 
        // Output: 10 (Not 20!)

        // 2. Method Execution -> Evaluated by ACTUAL OBJECT (Child on Heap)
        obj.display(); 
        // Output: Child Method
    }
}
```
