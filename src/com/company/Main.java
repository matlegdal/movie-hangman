package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            String movieToGuess = chooseMovie();

            if (movieToGuess != null) {
                Game game = new Game(movieToGuess);
                while (!game.hasWon()){
                    game.playTurn();
                    if (game.getNumGuessesLeft() < 1) {
                        System.out.println("Game Over! The correct answer was " + movieToGuess);
                        break;
                    }
                }

                Scanner scanner = new Scanner(System.in);
                System.out.println("Play again? (y/n)");
                String res = scanner.nextLine();
                if (!res.equals("y")) {
                    break;
                }

            } else {
                System.out.println("Something seems wrong with the file of movies or the code.");
            }
        }

    }

    private static String chooseMovie() {
        String[] movies = new String[30];

        try {
            File moviesFile = new File("movies.txt");
            Scanner scanner = new Scanner(moviesFile);
            int i = 0;
            while (scanner.hasNextLine() && i < 30){
                movies[i] = scanner.nextLine();
                i++;
            }
            return movies[(int) (Math.random()*i)];
        } catch (FileNotFoundException e) {
            System.out.println("movies.txt is not found");
        }
        return null;
    }
}
