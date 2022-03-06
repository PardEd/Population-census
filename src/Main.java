import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long result = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количесвто несовершеннолетних: " + result);

        List<String> family = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество призывного возраста: " + family.size());

        List<Person> employeesHigherEducation = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> !(x.getAge() > 65 && x.getSex().toString().equals("MAN")))
                .filter(x -> !(x.getAge() > 60 && x.getSex().toString().equals("WOMAN")))
                .filter(x -> x.getEducation().toString().equals("HIGHER"))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Число работников с высшим образованием: " + employeesHigherEducation.size());
    }
}
