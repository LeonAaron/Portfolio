package com.comp301.a01heist;

import java.util.*;

public class Practice {
  public static void checkLevels(ArrayList<Integer> levels) {
    int best = levels.get(0);

    for (int i = 0; i < levels.size(); i++) {
      if (levels.get(i) < best) {
        best = levels.get(i);
      }
    }

    System.out.println("The best is: " + best);
    System.out.println(levels.size());
  }

  public static void main(String[] args) {}

  //    @Test
  //    public void playOutputTest() {
  //        Guiter g = new Guitar("n", "m", 6);
  //        String actual = g.play();
  //        String expected = "Strumming the guitar!";
  //        assertEquals(expected, actual, "The actual and expexted differ in length by " +
  // Math.abs(actual.length() - expected.length()) + " characters.");
  //    }

  public String checkInstance() {
    String retVal = "";

    if (james instanceof Professor) {
      retVal += "Foo";
    }
    if (james instanceof Student) {
      retVal += "Bar";
    }
    if (james instanceof Person) {
      retVal += "!";
    }

    Person hanna = new Student("Hanna");

    String ret = "";
    if (hanna instanceof Student) {
      ret += "foo";
    }
    if (hanna instanceof Person) {
      ret += "bar";
    }
    return ret;
  }

  public void casting(){
      Person leon = new Student("Leon");
      Student leon2 = (Student) leon;

      Professor p = new Professor("p");
      Person p2 = p;

  }
}

//public class A implements AInt{}
//public class B extends A implements BInt{}
//public class C extends B implements CInt{}
//
//public void casting(BInt x) {
//  AInt x_as_Aint = (AInt) x;
//  B x_as_b = (B) BInt;
//  CInt x_as_Cint = (CInt) x;
//}
//
//public void casting2(C x) {
//  AInt x_as_Aint = x;
//  B x_as_b = x;
//  CInt x_as_Cint = x;
//}

class Person {
  private String name;

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}

class Professor extends Person{
  private int status;

  public Professor(String name) {
    this(name, 0);
  }

  public Professor(String name, int status) {
    super(name);
    this.status = status;
  }

  public Professor() {
    this("Prarie");
  }

  public void promote(){
    if (this.status >= 2) {
      throw new RuntimeException("Impossible to promote");
    }
    this.status++;
  }

  public void promote(int status) {
    if (status < 0 || status > 2) {
      throw new IllegalArgumentException("Invalid Promotion");
    }
    this.status = status;
  }

  public String getStatus() {
    if (this.status == 0) {
      return "Beginner";
    } else if (this.status == 1) {
      return "Medium";
    } else {
      return "Expert";
    }
  }

  @Override
  public String getName() {
    return "Prof " + super.getName();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Professor) {
      Professor p = (Professor) o;
      return this.getName().equals(p.getName());
    }
    return false;
  }

  public static void main(String[] args) {
    Professor p = new Professor("Prarie");
    Professor l = new Professor("Kris", 2);



    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(1);
    stack.pop();

    Deque<String> queue = new ArrayDeque<>();
    queue.offer("Hello");
    queue.peek();
    queue.poll();


    Deque<Double> list = new LinkedList<>();
    list.add(1.1);
    list.remove();
  }
}


class Container<E> {
  private E contents;

  public Container(E contents) {
    this.contents = contents;
  }

  public E getContents() {
    return this.contents;
  }

  public void setContents(E newContents) {
    this.contents = newContents;
  }

  public static void main(String[] args) {
    Container<String> strContainer = new Container<>("String");
    Container<Integer> intCont = new Container<>(1);

    System.out.println(strContainer.getContents());
    System.out.println(intCont.getContents());

  }
}

interface Animal{
  void speak();
  String getDesc();
}
class Dolphin implements Animal {
  String desc = "Hello";

  @Override
  public void speak(){
    System.out.println("Hello");
  }

  @Override
  public String getDesc() {
    return this.desc;
  }
}

class Node<E>{
  private E value;
  private Node<E> next;

  public Node(E value, Node<E> next) {
    this.value = value;
    this.next = next;
  }

  public E getValue() {
    return this.value;
  }

}