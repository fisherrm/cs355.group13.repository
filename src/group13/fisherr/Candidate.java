package group13.fisherr;



public class Candidate {
	
	private String desc;
	private int count;
	public Candidate(String desc)
	{
		this.desc = desc;
		this.count = 1;
	}
	
	public void incrementCount()
	{
		this.count++;
	}
	public int getCount()
	{
		return this.count;
	}
	
	public String toString()
	{
		String s = desc + ": " + count;
		return s;
		
	}

}
