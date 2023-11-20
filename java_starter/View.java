import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel {
	JButton b1;
	BufferedImage[] images;
	Model model;

	View(Controller c, Model m, Game game) {
		// Make a button
		// b1 = new JButton("Never push me!");
		// b1.addActionListener(c);
		// this.add(b1);

		// Link up to other objects
		c.setView(this);
		model = m;

		// Send mouse events to the controller
		this.addMouseListener(c);

		// Load the images
		this.images = new BufferedImage[Game.THINGS.length];

		for (int i = 0; i < Game.THINGS.length; i++) {
			try {

				String filename = "images/" + Game.THINGS[i] + ".png";
				this.images[i] = ImageIO.read(new File(filename));

			} catch (Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

	public void paintComponent(Graphics g) {
		// Clear the background
		g.setColor(new Color(55, 128, 47));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Draw purple square at the top right
		g.setColor(new Color(150, 30, 120));
		g.fillRect(0, 0, 200, 200);

		int w = this.images[model.selectedThing].getWidth();
		int h = this.images[model.selectedThing].getHeight();

		int imageW = (200 - w) / 2;
		int imageH = (200 - h) / 2;

		// Draw currently selected image centered in the purple square
		g.drawImage(images[model.selectedThing], imageW, imageH, null);

		// Draw the image so that its bottom center is at (x,y)
		// g.drawImage(images[model.selectedThing], model.turtle_x, model.turtle_y,
		// null);

		// Draw all the things from the ArrayList
		for (Thing thing : model.things) {
			int thingImageIndex = thing.kind; // Assuming kind is the index for the image
			int thingImageWidth = this.images[thingImageIndex].getWidth();
			int thingImageHeight = this.images[thingImageIndex].getHeight();

			g.drawImage(images[thingImageIndex], thing.x - thingImageWidth / 2, thing.y - thingImageHeight, null);
		}
	}

	void removeButton() {
		this.remove(this.b1);
		this.repaint();
	}
}
