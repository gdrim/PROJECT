import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "M";
    private final int x, y;
    Random r = new Random();

    Monster(int sizeBoard) {
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictPerson(int perX, int perY) {
        return perY - 1 == this.y && perX - 1 == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster(int difficultGame) {
        System.out.println("Solve the problem:");
        int a = r.nextInt(100);
        int b = r.nextInt(100);
        int trueAnswer = a + b;
        System.out.println("Solve the problem: " + a + " + " + b + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Correct! You’ve defeated the monster.");
            return true;
        } else {
            System.out.println("You have lost this battle!");
            return false;
        }
    }
}
