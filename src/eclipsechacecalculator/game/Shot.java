package eclipsechacecalculator.game;

/**
**	
**	@author Bitza Wolf of Convivial Communism
**/
public enum Shot
{
	YELLOW(1, "Yel"),
	ORANGE(2, "Ora"),
	RED(4, "Red");
	
	public final int damagePerHit;
	private final String debugName;
	
	private Shot(int dmg, String name)
	{
		damagePerHit = dmg;
		debugName = name;
	}
	
	@Override
	public String toString()
	{
		return debugName;
	}
}
