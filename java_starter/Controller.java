import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener {
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;

	Controller(Model m) {
		model = m;
	}

	void setView(View v) {
		view = v;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Hey! I said never push that button! This incident will be reported! (j/k)");
		view.removeButton();
	}

	public void mousePressed(MouseEvent e) {
		if (e.getX() <= 200 && e.getY() <= 200) {
			model.selectedThing++;
			if (model.selectedThing >= Game.THINGS.length) {
				model.selectedThing = 0;
			}
		}

		else if (e.getButton() == 1 && (e.getX() > 200 || e.getY() > 200)) {
			model.addThing(e.getX(), e.getY());
			model.setDestination(e.getX(), e.getY());
		}

		else if (e.getButton() == 3 && (e.getX() > 200 || e.getY() > 200)) {
			model.removeThing(e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				keyRight = true;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = true;
				break;
			case KeyEvent.VK_UP:
				keyUp = true;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = true;
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				keyRight = false;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = false;
				break;
			case KeyEvent.VK_UP:
				keyUp = false;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = false;
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
		}
		char c = Character.toLowerCase(e.getKeyChar());
		if (c == 'q')
			System.exit(0);
		if (c == 'r')
			model.reset();
	}

	public void keyTyped(KeyEvent e) {
	}

	void update() {
		if (keyRight)
			model.dest_x += Model.speed;
		if (keyLeft)
			model.dest_x -= Model.speed;
		if (keyDown)
			model.dest_y += Model.speed;
		if (keyUp)
			model.dest_y -= Model.speed;
	}
}
