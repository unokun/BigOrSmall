package jp.smaphonia;

public class Chip {

	private int count;
	
	public Chip() {
		this.count = 100;
	}
	
	public int getCount() {
		return this.count;
	}
	public void win(int chip) {
		this.count += chip;
	}
	
	public void lose(int chip) {
		this.count -= chip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("総計： " + this.count)
			   .append("(")
			   .append("[10]" + this.count / 10 + "枚")
			   .append(", ")
			   .append("[1]" + this.count % 10 + "枚")
			  .append(")");
		return builder.toString();
	}
	
}
