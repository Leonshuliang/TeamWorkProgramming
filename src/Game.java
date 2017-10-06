import java.util.Random;
import java.util.Scanner;

public class Game {
	Random random = new Random();
	int ROW = 4;// 4X4 grid rows
	int COLUMN = 4;// 4X4 grid columns
	private GameItem[][] board = new GameItem[ROW][COLUMN];// grid array
	int numPit = 3; // the number of pit;
	int numWumpus = 1;// the number of Wumpus
	int numGold = random.nextInt(3) + 1;// the number of gold
	int numClear = ((ROW) * (COLUMN)) - 1 - numPit - numWumpus - numGold; // the number of clear ground
	public int iPosition = 2;// player initially position
	public int jPosition = 0;
	char itemLook;
	int upItem; //
	int downItem; // row coordination after moving down
	int leftItem; // column coordination after moving left
	int rightItem;// column coordination after moving right
	int choicePosition; // choose to move player up or down or left or right
	static int score;
	Gold gold = new Gold(itemLook);
	Pit pit = new Pit(itemLook);
	Wumpus wumpus = new Wumpus(itemLook);
	ClearGround clearground = new ClearGround(itemLook);
	private Scanner input;

	public void runGame() {
		setPlayer();// set player on a clear ground
		setBoard();// instantiation of everytime
		display();// show grid
		menu();// introduction of this game
		senseNearby(iPosition, jPosition);
		movePlayer(iPosition, jPosition);// choose a number to move player

	}

	private void menu() {
		System.out.print("======Wumpus======\n" + "1.Move player left\n" + "2.Move player right\n"
				+ "3.Move player up\n" + "4.Move player down\n" + "5.Quit\n ");
		System.out.println("Now,your score is " + score);

	}

	public int getChoice() {
		System.out.println("please input your choice");
		input = new Scanner(System.in);
		int choiceNumber = input.nextInt();
		return choiceNumber;
	}

	private void display() {

		System.out.println("introduction");

		for (int i = 0; i < ROW; i++) {

			System.out.println(" --------------------");

			for (int j = 0; j < COLUMN; j++) {

				System.out.print("|");
				System.out.print("  ");
				System.out.print(board[i][j].getShowLook() + " ");
				if (j == COLUMN - 1) {
					System.out.print("|");
				}
			}
			System.out.println();

		}
		System.out.println(" --------------------");
	}

	public void setPlayer() {
		board[iPosition][jPosition] = new GameItem('*');
	};

	private void setBoard() // set groud to pit gold wumpus and clearground
	{

		setItem(numGold, gold.display());
		setItem(numPit, pit.display());
		setItem(numWumpus, wumpus.display());
		setItem(numClear, clearground.display());

	}

	public void setItem(int num, char itemLook) {

		for (int i = 0; i < num; i++) {// put all items on the grids
			int randomRow = random.nextInt(ROW);
			int randomClum = random.nextInt(COLUMN);
			if (board[randomRow][randomClum] == null) {
				board[randomRow][randomClum] = new GameItem(itemLook);
			} else {
				i--;
			}
		}
	}

	private void senseNearby(int i, int j) {

		upItem = this.nextGrid(i - 1);// position on the up side of player
		downItem = nextGrid(i + 1);// position on the under side of player
		leftItem = nextGrid(j - 1);// position on the left side of player
		rightItem = nextGrid(j + 1);// position on the right side of player

		char p = pit.display();
		char g = gold.display();
		char w = wumpus.display();
		char c = clearground.display();
		System.out.println("-----------BE CAREFUL------------");
		if (board[upItem][j].getShowLook() == p) {
			System.out.println("Breeze");
		}
		if (board[downItem][j].getShowLook() == p) {
			System.out.println("Breeze");
		}
		if (board[i][leftItem].getShowLook() == p) {
			System.out.println("Breeze");
		}
		if (board[i][rightItem].getShowLook() == p) {
			System.out.println("Breeze");
		}
		if (board[upItem][j].getShowLook() == g) {
			System.out.println("faint glitter");
		}
		if (board[downItem][j].getShowLook() == g) {
			System.out.println("faint glitter");
		}
		if (board[i][leftItem].getShowLook() == g) {
			System.out.println("faint glitter");
		}
		if (board[i][rightItem].getShowLook() == g) {
			System.out.println("faint glitter");
		}
		if (board[upItem][j].getShowLook() == w) {
			System.out.println("vile smell");
		}
		if (board[downItem][j].getShowLook() == w) {
			System.out.println("vile smell");
		}
		if (board[i][leftItem].getShowLook() == w) {
			System.out.println("vile smell");
		}
		if (board[i][rightItem].getShowLook() == w) {
			System.out.println("vile smell");
		}
		System.out.println("----------------------------------");

	}

	private int nextGrid(int g) {
		if (g == -1) {
			g = board.length - 1;

		}
		if (g == board.length) {
			g = 0;
		} else {
			return g;
		}
		return g;
	}

	public void movePlayer(int i, int j) {

		choicePosition = getChoice();
		// System.out.println(choicePosition);
		if (choicePosition == 1) {
			board[i][j] = new GameItem(clearground.display());
			jPosition = nextGrid(j - 1);
			doAfterMove(i, jPosition);

		} else if (choicePosition == 2) {
			board[i][j] = new GameItem(clearground.display());
			jPosition = nextGrid(j + 1);
			doAfterMove(i, jPosition);
		} else if (choicePosition == 3) {
			board[i][j] = new GameItem(clearground.display());
			iPosition = nextGrid(i - 1);
			doAfterMove(iPosition, j);
		} else if (choicePosition == 4) {
			board[i][j] = new GameItem(clearground.display());
			iPosition = nextGrid(i + 1);
			doAfterMove(iPosition, j);
		} else if (choicePosition == 5) {
			System.out.println("you had choosed to leave this game!!!!!");
			stopGame();// game over by choosing 5
		}
	}

	public void doAfterMove(int i, int j) {
		if (board[i][j].getShowLook() == gold.display()) {
			score++;
			System.out.println("you eat a gold\n" + "your score now is " + score);

			setPlayer();
			display();
			menu();
			senseNearby(i, j);
			movePlayer(i, j);
		} else if (board[i][j].getShowLook() == pit.display()) {
			System.out.println("**********OOPS*******GAME OVER*********\n" + "you are falling in  pit \n"
					+ "Your score now is " + score);
			stopGame();
		} else if (board[i][j].getShowLook() == wumpus.display()) {
			System.out.println("**********OOPS*******GAME OVER*********\n" + "you ar killed by Wumpus\n"
					+ "your score now is " + score);
			stopGame();
		} else {
			setPlayer();
			display();
			menu();
			senseNearby(i, j);
			movePlayer(i, j);
		}
	}

	public void stopGame()//
	{
		System.exit(0);
	}

}
