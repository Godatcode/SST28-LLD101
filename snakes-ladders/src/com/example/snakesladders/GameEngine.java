package com.example.snakesladders;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private int currentRank;

    public GameEngine(Board board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = new ArrayList<>(players);
        this.dice = dice;
        this.currentRank = 0;
    }

    public void play() {
        printBoard();

        while (activePlayers() >= 2) {
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                if (player.isFinished()) continue;
                if (activePlayers() < 2) break;

                playTurn(player);
            }
        }

        // last remaining player gets the final rank
        for (Player player : players) {
            if (!player.isFinished()) {
                currentRank++;
                player.setRank(currentRank);
                player.setFinished(true);
            }
        }

        printResults();
    }

    private void playTurn(Player player) {
        int roll = dice.roll();
        int oldPos = player.getPosition();
        int newPos = oldPos + roll;

        System.out.print(player.getName() + " rolled " + roll + " | " + oldPos);

        // can't move beyond the final cell
        if (newPos > board.getFinalCell()) {
            System.out.println(" -> stays (would exceed " + board.getFinalCell() + ")");
            return;
        }

        // check for snake or ladder
        int destination = board.getDestination(newPos);
        if (destination != newPos) {
            String type = destination < newPos ? "Snake!" : "Ladder!";
            System.out.println(" -> " + newPos + " " + type + " -> " + destination);
            newPos = destination;
        } else {
            System.out.println(" -> " + newPos);
        }

        player.setPosition(newPos);

        if (newPos == board.getFinalCell()) {
            currentRank++;
            player.setRank(currentRank);
            player.setFinished(true);
            System.out.println("  " + player.getName() + " wins! Rank: #" + currentRank);
        }
    }

    private int activePlayers() {
        int count = 0;
        for (Player p : players) {
            if (!p.isFinished()) count++;
        }
        return count;
    }

    private void printBoard() {
        System.out.println("\n=== Snakes & Ladders ===");
        System.out.println("Board size: " + board.getSize() + " cells");
        System.out.println("Snakes:  " + board.getSnakes());
        System.out.println("Ladders: " + board.getLadders());
        System.out.println("Players: " + players);
        System.out.println();
    }

    private void printResults() {
        System.out.println("\n=== Final Rankings ===");
        for (int r = 1; r <= players.size(); r++) {
            for (Player p : players) {
                if (p.getRank() == r) {
                    System.out.println("#" + r + " " + p.getName());
                }
            }
        }
    }
}
