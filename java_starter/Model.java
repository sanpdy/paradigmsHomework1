import java.util.ArrayList;

class Thing {
	public int x;
	public int y;
	public int kind;

	Thing(int x, int y, int kind) {
		this.x = x;
		this.y = y;
		this.kind = kind;
	}
}

class Model {
	int turtle_x;
	int turtle_y;
	int dest_x;
	int dest_y;
	static int speed = 10;
	ArrayList<Thing> things = new ArrayList<Thing>();
	int selectedThing = 0;

	Model() {
		this.turtle_x = 100;
		this.turtle_y = 100;
		this.dest_x = 150;
		this.dest_y = 100;
	}

	public void update() {
		if (this.turtle_x < this.dest_x)
			this.turtle_x += Math.min(speed, dest_x - turtle_x);
		else if (this.turtle_x > this.dest_x)
			this.turtle_x -= Math.max(speed, dest_x - turtle_x);
		if (this.turtle_y < this.dest_y)
			this.turtle_y += Math.min(speed, dest_y - turtle_y);
		else if (this.turtle_y > this.dest_y)
			this.turtle_y -= Math.max(speed, dest_y - turtle_y);
	}

	public void reset() {
		turtle_x = 100;
		turtle_y = 100;
		dest_x = turtle_x;
		dest_y = turtle_y;
	}

	public void setDestination(int x, int y) {
		this.dest_x = x;
		this.dest_y = y;
	}

	public void addThing(int x, int y) {
		things.add(new Thing(x, y, selectedThing));
	}

	public void removeThing(int x, int y) {
		double closestDistance = Double.MAX_VALUE;
		Thing closestThing = null;

		for (Thing thing : things) {
			double distance = Math.sqrt(Math.pow(thing.x - x, 2) + Math.pow(thing.y - y, 2));

			if (distance < closestDistance) {
				closestDistance = distance;
				closestThing = thing;
			}
		}

		if (closestThing != null) {
			things.remove(closestThing);
		}
	}

	public void rotateThings() {

	}
}