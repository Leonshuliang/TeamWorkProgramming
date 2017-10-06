
public class Wumpus extends GameItem {

	public Wumpus(char showLook) {
		super(showLook);
	}

	public char display() {
		char showLook = 'W';

		return super.display(showLook);
	}

}
