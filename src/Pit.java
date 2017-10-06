
public class Pit extends GameItem {

	public Pit(char showLook) {
		super(showLook);
	}

	public char display() {
		char showLook = 'p';

		return super.display(showLook);
	}

}
