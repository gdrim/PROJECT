import java.util.Random;
import java.util.Scanner;

public class SmallMonster extends Monster {

    private String image = "Sm";

    SmallMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        System.out.println("The little monster guessed a number from 1 to 20.");
        System.out.println("You have 3 tries to guess!");

        Random random = new Random();
        int secret = random.nextInt(20) + 1;
        Scanner scanner = new Scanner(System.in);

        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.print("Attempt " + attempt + ": ");
            int guess = scanner.nextInt();

            if (guess == secret) {
                System.out.println("Correct! You’ve defeated the monster.");
                return true;
            } else if (guess < secret) {
                System.out.println("The chosen number is greater.");
            } else {
                System.out.println("The chosen number is smaller.");
            }
        }

        System.out.println("Ты не угадал. Загаданное число было: " + secret);
        System.out.println("You have lost this battle!");
        return false;
    }
}

