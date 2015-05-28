package clanz;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Cuboid {
	
	XZLoc high;
	XZLoc low;
	
	int getXZLocCount(){
		return ( (high.x - low.x) * (high.z - low.z));
	}
	
	List<XZLoc> getXZLocs(){
		ArrayList<XZLoc> locs = new ArrayList<>();
		for(int x = low.x; x<=high.x; x++){
			for(int z = low.z; x<=high.z; z++){
				locs.add(new XZLoc(x,z));
			}
		}
		return locs;
		
	}
	
	
	class XZLoc {
		int x;  int z;
		public XZLoc(int x, int z){
			this.x = x; this.z = z;
		}
		boolean match(Location l){
			return (l.getBlockX() == x && l.getBlockZ() == z);
		}
	}
	
	
}
