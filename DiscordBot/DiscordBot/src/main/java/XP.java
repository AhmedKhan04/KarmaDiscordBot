import net.dv8tion.jda.api.entities.Member;

public class XP {

	private Member mem; 
	private int number; 
	
	public XP(Member a) 
	{
		mem = a; 
		number = 0;
	}
	
	public void add() {
		number += 1; 
	}
	
	public int getNUM() {
		return this.number; 
	}
	
	public String getAuthor() {
		return mem.getEffectiveName();
	}
}
