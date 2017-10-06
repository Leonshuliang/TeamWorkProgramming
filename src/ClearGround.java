
public class ClearGround extends GameItem {

	public ClearGround(char showLook) {
		super(showLook);
	}

	public char display() {
		char showLook = '.';

		return super.display(showLook);
	}

}
