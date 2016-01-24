package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Board {
	private List<Partial> partials;
	private GridPane grid;
	private TextField count;
	private int clicks_count;

	public Board(GridPane grid, TextField count) {
		this.count = count;
		this.clicks_count = 0;
		this.grid = grid;
		this.partials = new ArrayList<>();
	}

	public void add(Partial partial) {
		partial.setDad(this);
		partials.add(partial);
	}

	public boolean checkVicotory() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {

				Partial partial = getPartialFromGrid(j, i);
				if (partial != null) {
					if (partial.getGrid_id() != (i*4)+j) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void change(Partial partial) {
		PointInt left = getLeftNeighbor(
				partial.getGrid_x(), partial.getGrid_y());
		PointInt right = getRightNeighbor(
				partial.getGrid_x(), partial.getGrid_y());
		PointInt bottom = getBottomNeighbor(
				partial.getGrid_x(), partial.getGrid_y());
		PointInt top = getTopNeighbor(
				partial.getGrid_x(), partial.getGrid_y());


		if (left != null) {
			if (getPartialFromGrid(left.x, left.y) == null) {
				grid.getChildren().remove(partial);
				partial.setGrid_x(left.x);
				partial.setGrid_y(left.y);
				addClicks();
				grid.add(partial, left.x, left.y);
			}
		}
		if (right != null) {
			if (getPartialFromGrid(right.x, right.y) == null) {
				grid.getChildren().remove(partial);
				partial.setGrid_x(right.x);
				partial.setGrid_y(right.y);
				addClicks();
				grid.add(partial, right.x, right.y);
			}
		}
		if (top != null) {
			if (getPartialFromGrid(top.x, top.y) == null) {
				grid.getChildren().remove(partial);
				partial.setGrid_x(top.x);
				partial.setGrid_y(top.y);
				addClicks();
				grid.add(partial, top.x, top.y);
			}
		}
		if (bottom != null) {
			if (getPartialFromGrid(bottom.x, bottom.y) == null) {
				grid.getChildren().remove(partial);
				partial.setGrid_x(bottom.x);
				partial.setGrid_y(bottom.y);
				addClicks();
				grid.add(partial, bottom.x, bottom.y);
			}
		}

	}

	private Partial getPartialFromGrid(int x, int y) {
        for(Node node : grid.getChildren()) {
            if(grid.getRowIndex(node) == y && grid.getColumnIndex(node) == x) {
                return (Partial) node;
            }
        }
        return null;
	}

	public void reset() {
		clicks_count = -1;
		addClicks();
	}


	private PointInt getLeftNeighbor(int x, int y) {
		if (x-1 >= 0) {
			return new PointInt(x-1, y);
		} else {
			return null;
		}
	}

	private PointInt getRightNeighbor(int x, int y) {
		if (x+1 <= 3) {
			return new PointInt(x+1, y);
		} else {
			return null;
		}
	}

	private PointInt getTopNeighbor(int x, int y) {
		if (y-1 >= 0) {
			return new PointInt(x, y-1);
		} else {
			return null;
		}
	}

	private PointInt getBottomNeighbor(int x, int y) {
		if (y+1 <= 3) {
			return new PointInt(x, y+1);
		} else {
			return null;
		}
	}

	private void addClicks() {
		clicks_count++;
		count.setText(String.valueOf(clicks_count));
	}

	private class PointInt {
		public int x;
		public int y;

		PointInt(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}


