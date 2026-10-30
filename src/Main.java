import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String castle = "C";
        int sizeBoard = 5;

        Person person = new Person(sizeBoard);

        int step = 0;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }

        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Random r = new Random();

        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test;
        while (count <= countMonster) {
            int monsterType = r.nextInt(3);
            if (monsterType == 0) {
                test = new Monster(sizeBoard);
            } else if (monsterType == 1) {
                test = new BigMonster(sizeBoard);
            } else {
                test = new SmallMonster(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }



        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;

        board[castleY][castleX] = castle;

        System.out.println("Hi! Are you ready to start playing the game? (Type: YES or NO)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Your Answer:\t" + answer);

        switch (answer) {
            case "YES" -> {
                System.out.println("Choose the game difficulty (from 1 to 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Selected difficulty:\t" + difficultGame);

                while (true) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive());
                    System.out.println("Enter where the character should move (movement is possible only vertically or horizontally by one square)"
                            + "\nCharacter coordinates - (x: " + person.getX() + ", y: " + person.getY() + ")");

                    int x = sc.nextInt();
                    int y = sc.nextInt();

                    if (person.moveCorrect(x, y)) {
                        String next = board[y - 1][x - 1];

                        if (next.equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;
                            System.out.println("Move is valid; New coordinates: " + person.getX() + ", " + person.getY()
                                    + "\nMove number: " + step);
                        } else if (next.equals(castle)) {
                            System.out.println("You’ve beaten the game!");
                            break;
                        } else {
                            for (Monster monster : arrMonster) {
                                if (monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                        step++;
                                    } else {
                                        person.downLive();
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid move");
                    }


                    if (person.getLive() <= 0) {
                        System.out.println("Lives exhausted. Game over!");
                        break;
                    }
                }
            }
            case "NO" -> System.out.println("Too bad-come back again!");
            default -> System.out.println("The data has been entered incorrectly.");
        }
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ -- + -- + -- + -- + -- +";

        for (String[] row : board) {
            System.out.println(wall);
            for (String col : row) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Number of lives:\t" + live + "\n");
    }
}
