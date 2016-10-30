package jp.smaphonia;

public class Chip {
	public static final int INIT_COUNT = 100;
	private int count;
	
	public Chip() {
		count = INIT_COUNT;
	}
	
	public int getCount() {
		return count;
	}
	public void win(int chip) {
		count += chip;
	}
	
	public void lose(int chip) {
		this.count -= chip;
		if (count < 0) {
			count = 0;
		}
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
