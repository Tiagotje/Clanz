package clanz.protection;


import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import clanz.Clanz;
import clanz.entity.Clan;

public class Region {
	
	
	public Region(String st, Clan c, Clanz cl) {
		name = st;
		clan = c;
		area = cl.WGP.getRegionManager(cl.getServer().getWorld("world")).getRegion(st);
	}
	
	
	String name;
	Clan clan;
	ProtectedRegion area;
	
	
}
