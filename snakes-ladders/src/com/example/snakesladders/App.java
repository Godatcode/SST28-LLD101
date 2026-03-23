package com.example.snakesladders;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board dimension (n for nxn board): ");
        int n = scanner.nextInt();

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        System.out.print("Enter difficulty (easy/hard): ");
        String diffInput = scanner.nextLine().trim().toUpperCase();
        DifficultyLevel difficulty = DifficultyLevel.valueOf(diffInput);

        Board board = new BoardBuilder().build(n, difficulty);
        Dice dice = new Dice();

        GameEngine engine = new GameEngine(board, players, dice);
        engine.play();

        scanner.close();
    }
}
