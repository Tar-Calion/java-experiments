package tar.calion.java21;

public class Records {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        record Person(String name, int age) {
            public Person {
                if (age < 0) {
                    throw new IllegalArgumentException("Age cannot be negative");
                }
            }
        }

        Person p = new Person("John", 30);

        record Student(Person person, String school) {
        }

        Student s = new Student(p, "School");

        switch (s) {
            case Student(Person(String name, int age), String school) ->
                    System.out.println(name + " " + age + " " + school);
        }
    }
}
