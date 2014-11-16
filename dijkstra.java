import java.util.*;

public class DijkstraAlgo {
	
	public static final int NIL = -1;
	public static final int MAX = Integer.MAX_VALUE;
	
	private float[][] poids ;
	private int[] P ;
	private float[] distance ;
	private Vector<Integer> S = new Vector<Integer>();
	private Vector<Integer> T = new Vector<Integer>();
	private int depart,arrive;
	
	private void init() {
		P = new int[poids.length];
		distance = new float[poids.length];
		for(int i=0;i<distance.length;i++) distance[i] = poids[depart][i];
		for(int i=0;i<poids.length;i++) P[i] = NIL;
		for(int i=0;i<poids.length;i++) if(poids[depart][i]<MAX) P[i] = depart;
   		for(int i=0;i<poids.length;i++) if(i!=depart) T.add(i);
   		S.add(depart);
	}
	
	private void dijkstra() {
		while(T.size()!=0) {
			int s = findIndexOfMin();
			T.remove(new Integer(s));
			S.add(s);
			Vector<Integer> v = voisin(s); 
			for(int i=0;i<v.size();i++) maj_distances(s,v.get(i).intValue());
		}
	}
	
	private int findIndexOfMin() {
		float min = distance[T.get(0).intValue()];
		int index = T.get(0).intValue();
		for(int i=0;i<T.size();i++) {
			if(min>distance[T.get(i).intValue()]) {
				min = distance[T.get(i).intValue()];
				index = T.get(i).intValue();
			}
		}
		return index;
	}
	

	private Vector<Integer> voisin(int s) {
		Vector<Integer> v = new Vector<Integer>();
		for(int i=0;i<poids[s].length;i++)
				if(poids[s][i]<MAX && T.contains(i)) v.add(i);
		return v;
	}
		
	private void maj_distances(int s1,int s2) {
		if(distance[s2] > distance[s1] + poids[s1][s2]) {
				distance[s2] =  distance[s1] + poids[s1][s2];
				P[s2] = s1;
		}
	}
	
	public void printf(float[] z) {
		for(int i=0;i<z.length;i++) System.out.print(""+z[i]+" :: ");
			System.out.println("");
	}
	
	public void printf(int[] z) {
		for(int i=0;i<z.length;i++) System.out.print(""+z[i]+" :: ");
			System.out.println("");
	}
	
	
	
	
	public Vector<Integer> way(int sfin) {
		arrive = sfin;
		Vector<Integer> W = new Vector<Integer>();
		int s = sfin;
		W.add(s);
		while(s!=this.depart) {
			s = P[s];
			W.add(s);
		}
		Vector<Integer> V = new Vector<Integer>();
		for(int i=0;i<W.size();i++) V.add(W.get(W.size()-1-i));
		return V;
	}
	
	public String getFinalResult() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<poids.length;i++) sb.append(way(i).toString()+"="+distance[i]+"\n");
		return sb.toString();
	}
		
	public DijkstraAlgo(float[][] poids,int depart) {
		this.poids = new float[poids.length][poids.length];
		for(int i=0;i<poids.length;i++) {
			for(int j=0;j<poids.length;j++) {
				if(poids[i][j]!=-1) this.poids[i][j] = poids[i][j];
				else this.poids[i][j] = MAX;
			}
		}
		this.depart = depart;
		init();
		dijkstra();
	}
	
	public float getDistance() {
		return distance[arrive];
	}
	
	public static void main(String[] args) {
		new DijkstraAlgo(new float[][] 
		{
			{0,15,MAX,MAX,4},
			{MAX,0,MAX,MAX,MAX},
			{MAX,3,0,2,MAX},
			{10,3,MAX,0,MAX},
			{MAX,MAX,7,5,0},
		},0);
	}
	
	
	class MyHashtable extends Hashtable {
		public String toString() {
			StringBuffer sb = new StringBuffer();
			Enumeration en = this.keys();
			while(en.hasMoreElements()) {
				Vector<Integer> v = (Vector<Integer>)en.nextElement();
				 Float f = (Float)this.get(v);
				 sb.append(v.toString()+" <-> "+f.toString()+"\n");
			}
			return sb.toString();
		}
	}	
	
}
 
