package taco.mineopoly;

public enum MineopolyColor {

	PURPLE(new int[]{1, 3}, '5'),
	LIGHT_BLUE(new int[]{6, 8, 9}, '3'),
	MAGENTA(new int[]{11, 13, 14}, 'd'),
	ORANGE(new int[]{16, 18, 19}, '6'),
	RED(new int[]{21, 23, 24}, '4'),
	YELLOW(new int[]{26, 28, 29}, 'e'),
	GREEN(new int[]{31, 32, 34}, '2'),
	BLUE(new int[]{37, 39}, '2');
	
	private int[] ids;
	private char color;
	
	private MineopolyColor(int[] ids, char color){
		this.ids = ids;
		this.color = color;
	}
	
	public int[] getIds(){
		return this.ids;
	}
	
	public String getName(){
		return this.color + name();
	}
	
	public String toString(){
		return "&" + color;
	}
	
	public static MineopolyColor getColor(String name){
		for(MineopolyColor mc : MineopolyColor.values()){
			if(name.equalsIgnoreCase(mc.name())){
				return mc;
			}
		}
		return null;
	}

	public char getChar() {
		return color;
	}
	
}
