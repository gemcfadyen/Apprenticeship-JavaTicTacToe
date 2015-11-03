# Apprenticeship-JavaTicTacToe
Using TDD write Human vs Human game of Tic Tac Toe in java

- Concentrate on TDD.
- Small commits with descriptive messages.
- No retrofitting of any tests.
- Spike (learning). Always throw away the code.
- Human vs Human only
- 3x3 only
- Console


# Running the application

1. Clone the repo {code} git@github.com:gemcfadyen/Apprenticeship-JavaTicTacToe.git {code}

1. Ensure you have JUnit4 on your classpath (which should contain hamcrest-core-1.3.jar, hamcrest-library-1.3.jar).

1. There are no external dependencies or build framework needed.

1. To compile the code outside of an IDE:

   _cd Apprenticeship-JavaTicTacToe/src/main/java/ttt_

   _javac PlayerSymbol.java Cell.java Board.java Prompt.java Player.javaReadFromPromptException.java WriteToPromptException.java HumanPlayer.java CommandPrompt.java Board.java Game.java_

1. For the best user experience run from the command line
 
   _cd Apprenticeship-JavaTicTacToe/out/production/Apprenticeship-JavaTicTacToe_
 
   _java ttt/Game_
