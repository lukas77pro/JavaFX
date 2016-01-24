package application;

import javafx.scene.control.Button;

public class Reset extends Button{
	private Board dad;

	public Reset() {
		super();
	}

	public Board getDad() {
		return dad;
	}

	public void setDad(Board dad) {
		this.dad = dad;
	}

}
