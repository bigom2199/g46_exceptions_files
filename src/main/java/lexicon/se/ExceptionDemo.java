package lexicon.se;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

public class ExceptionDemo {
    public static void main(String[] args) {

      /*  Path filePath = Paths.get("dir/lastnames.txt");
        BufferedReader reader = Files.newBufferedReader(filePath);

        int [] numbers = {1,2,3,4};
        System.out.println(numbers[10]);*/
        ex6();

    }


    // catching unchecked-exception
    public static void ex1() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a Number:");
            try {
                int number = Integer.parseInt(Scanner.nextLine()); // 12A
                if (number <= 0) {
                    System.out.println("Try again - Number should not be a zero");
                }
                System.out.println("Entered number is : " + number);
            } catch (NumberFormatException e) {
                System.out.println("Try again -Number is not valid");

            }
        }
    }

    //  catching unchecked -exception
    public static void ex2() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your birthdate: ");
            try {
                LocalDate date = LocalDate.parse(Scanner.nextLine());
                System.out.println("Your Next birth date is: " + date.plusYears(1));


            } catch (DataFormatException e) {
                // System.out.println(e.getMessage());
                //  e.printStackTrace();
                System.out.println("Date Format is not valid - Try again");
            }
        }
    }

    // catching checked-exception + Read a File  using NIO
    public static void ex3() {
        Path filePath = Paths.get("dir/lastnames.txt");
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            List<String> names = reader.lines().toList();
            names.forEach(System.out::println);
            System.out.println("---------");
            Stream<String> stringStream = Files.lines(filePath);
            stringStream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // catching checked-exception with multiples catches  using NIO
    public static void ex4() {
        Path sourcFilePath = Path.of("source/java_logo.png");
        Path destinationDirPath = Path.of("destination");
        try {
            Files.copy(sourcFilePath, destinationDirPath.resolve(sourcFilePath.getFileName()));
        } catch (FileAlreadyExistsException e) {
            System.out.println("File Already Exists.");

        } catch (IOException e) {
            System.out.println("IoException:" + e);

        }
        System.out.println("DONE");
    }

    // throw an exception using throw keyword
    public static void ex5() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Num 1:");
        int num1 = Scanner.nextInt();
        System.out.println("Enter Num 2:");
        int num2 = scanner.nextInt();
        if (num2 == 0) {
            // throw keyword is used to throw an exception within a method or block of code
            throw new ArithmeticException("number2 should not be a zero number.");

        }
        int result = num1 / num2;
        System.out.println("Result:" + result);
    }

    public static void ex6() throws InsufficientFundsException {
        double balance = 100;
        double amount = 200;
        System.out.println("Operation name: withdraw");
        System.out.println("Current Balance is :" + balance);
        System.out.println("Amount is:" + amount);
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount, "Balance is insufficient...:");
        }
        // balance = balance - amount;
        balance -= amount;
        System.out.println("Current Balance is:" + balance); // 100

    }

    // write data to a file and close the resources with finally block...
    public static void ex7() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(
                    Path.of("dir/new-file.txt"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
            writer.append("Hello NIO");
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    // try- with -recourses
    public static void ex8() {
        try (
                BufferedWriter writer = Files.newBufferedWriter(
                        Path.of("dir/new -file.txt"),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND

                ); // 20 kb
        ) {
            writer.append("NEW LINE");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } // it will close the used resources

    }

    public static void ex9() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charles", 35));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // do more configurations...
        File file = new File("people.json");
        try {
            // serialize the list to JSON File
            objectMapper.weiteValue(file, people);
            System.out.println("People date saved to the file.");
        } catch (IOException e) {
            e.printStackTrace();


        }
        List<Person> personList = null;
        try {
            // Deserialize the JSON file to List of Person
            PersonList = objectMapper.readvalue(file, new TypeReference<List<person>>() {

            });


            System.out.println("People data read from  the file.");
            personList.forEach(System.out::println);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
