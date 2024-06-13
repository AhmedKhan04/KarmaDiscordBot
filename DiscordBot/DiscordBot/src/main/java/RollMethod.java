public class RollMethod {

	public static String Roller(int values) {
		String supers = "";
		int f = 0;
		while(f < values) {
		int x = (int)(Math.random()*10);
		supers += x + "\n";
		}
		return supers;
		
	}
}
