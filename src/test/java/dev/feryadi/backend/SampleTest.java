package dev.feryadi.backend;

import org.junit.jupiter.api.Test;

public class SampleTest {


    @Test
    void test() {
        Person person1 = new Person("person 1");
        Person person2 = person1; // reference copy

        System.out.println("person1 : " + person1);
        System.out.println("person2 : " + person2);

        System.out.println("before");
        System.out.println("person1.name : " + person1.name);
        System.out.println("person2.name : " + person2.name);

        person2.name = "new name";

        System.out.println("after");
        System.out.println("person1.name : " + person1.name);
        System.out.println("person2.name : " + person2.name);


        // shallow copy
        Person person3 = person1.clone();

        System.out.println("person3 : " + person3);
        System.out.println("person3.name" + person3.name);

    }


    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public Person clone() {
            return new Person(this.name);
        }
    }
}
