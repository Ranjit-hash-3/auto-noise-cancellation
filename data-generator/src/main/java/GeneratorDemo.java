import com.github.javafaker.Faker;

public class GeneratorDemo {

    public static void main(String args[]){
        Faker faker = new Faker();

        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton
        String streetAddress = faker.address().streetAddress();

        System.out.println(name);
    }
}
