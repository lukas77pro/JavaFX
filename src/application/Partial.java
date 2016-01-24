package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Partial extends ImageView{

	private int grid_x;
	private int grid_y;
	private int grid_id;
	private Board dad;

	public int getGrid_x() {
		return grid_x;
	}

	public void setGrid_x(int grid_x) {
		this.grid_x = grid_x;
	}

	public int getGrid_y() {
		return grid_y;
	}

	public void setGrid_y(int grid_y) {
		this.grid_y = grid_y;
	}


	public int getGrid_id() {
		return grid_id;
	}

	public void setGrid_id(int grid_id) {
		this.grid_id = grid_id;
	}

	public Partial(Image img, int grid_id, int grid_x, int grid_y) {
		super(img);
		this.grid_id = grid_id;
		this.grid_x = grid_x;
		this.grid_y = grid_y;
	}

	public Board getDad() {
		return dad;
	}

	public void setDad(Board dad) {
		this.dad = dad;
	}

}
