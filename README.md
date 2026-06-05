# Minesweeper Game Java (Sprint 3)

This project is a console-based implementation of the classic Minesweeper game written in Java. Players reveal cells on a grid while avoiding hidden mines. The game supports revealing cells, placing flags, automatic expansion of empty areas, win/loss detection, and saving the current game state to a file.

## Instructions for Build and Use

Steps to build and/or run the software:

1. Install Java Development Kit (JDK 17 or later).

2. Save the source code as `Minesweeper.java`.

3. Open a terminal or command prompt and navigate to the project folder.

4. Compile the program using:

   `javac Minesweeper.java`

5. Run the program using:

   `java Minesweeper`

Instructions for using the software:

1. Start the program to generate a new 10x10 Minesweeper board with 20 mines.
2. Enter commands in the following formats:

   * `r row col` to reveal a cell
   * `f row col` to place or remove a flag
   * `s filename` to save the game state to a file
3. Continue revealing cells and marking suspected mines until you either clear the board or reveal a mine.
4. If all non-mine cells are revealed, you win the game.

## Development Environment

To recreate the development environment, you need the following software and/or libraries with the specified versions:

* Java Development Kit (JDK) 17 or later
* Java Standard Library (`java.util`, `java.io`)
* Visual Studio Code, IntelliJ IDEA, or another Java-compatible IDE
* Command Prompt, Terminal, or PowerShell

## Useful Websites to Learn More

I found these websites useful in developing this software:

* [Oracle Java Documentation](https://docs.oracle.com/en/java/)
* [W3Schools Java Tutorial](https://www.w3schools.com/java/)
* [GeeksforGeeks Java Programming](https://www.geeksforgeeks.org/java/)
* [Java Scanner Class Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)

## Future Work

The following items I plan to fix, improve, and/or add to this project in the future:

* [ ] Add the ability to load previously saved games.
* [ ] Create multiple difficulty levels with different board sizes and mine counts.
* [ ] Improve input validation to prevent invalid coordinates and commands.
* [ ] Add a graphical user interface (GUI) using Java Swing or JavaFX.
* [ ] Track player statistics such as wins, losses, and completion times.
