package clanz.entity;

public enum ClanRelation {
	
	LEADER(4),
	ASSISTANT(3),
	MEMBER(2),
	ALLY(1),
	STRANGER(0),
	ENEMY(-1);
	
	public int level;
	
	private ClanRelation(int l){
		level = l;
	}
}
