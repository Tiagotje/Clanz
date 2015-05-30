package clanz;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.util.Vector;


//A rectangular area
public class Cuboid {
	
	XZLoc high;
	XZLoc low;
	
	public Cuboid(XZLoc l1, XZLoc l2){
		low = l1;
		high = l2;
		CheckHighLow();
	}
	
	public Cuboid(Location l1, Location l2){
		high = new XZLoc(l1);
		low = new XZLoc(l2);
		CheckHighLow();
	}
	
	
	//Check if the high variable, actually is the highest.
	void CheckHighLow(){
		if(low.x > high.x){
			int temp = low.x;
			low.x = high.x;
			high.x = temp;
		}
		if(low.z > high.z){
			int temp = low.z;
			low.z = high.z;
			high.z = temp;
		}
	}
	
	//Get the number of XZLocs inside this Cuboid
	int getXZLocCount(){
		return ( (high.x - low.x) * (high.z - low.z));
	}
	
	//Get a list of all XZLocs inside this Cuboid
	List<XZLoc> getXZLocs(){
		ArrayList<XZLoc> locs = new ArrayList<>();
		for(int x = low.x; x<=high.x; x++){
			for(int z = low.z; x<=high.z; z++){
				locs.add(new XZLoc(x,z));
			}
		}
		return locs;
		
	}
	
	//Returns a list of 2 vectors, representing this Cuboid.
	List<Vector> toVectorList(){
		ArrayList<Vector> list = new ArrayList<>();
		list.add(new Vector(high.x, 0, high.z));
		list.add(new Vector(low.x, 0, low.z));
		return list;
	}
	
	//Represents a location, where the Y is ignored.
	class XZLoc {
		int x;  int z;
		
		public XZLoc(Location l){
			this.x = l.getBlockX();
			this.z = l.getBlockZ();
		}
		
		public XZLoc(int x, int z){
			this.x = x; this.z = z;
		}
		boolean match(Location l){
			return (l.getBlockX() == x && l.getBlockZ() == z);
		}
	}
	
	
	
}
