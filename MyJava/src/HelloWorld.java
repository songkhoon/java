import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class HelloWorld {

    public static void main(String[] args) {
    	//DBManager.connectionTest();
    	//IOManager.testReadFile();
    	
    	World world = new World();
    	world.testArray();
    	world.testArrayList();
    	world.testHashMap();
    	world.TestIterator();
    }
    
    private static final class World{
    	public World(){
    		
    	}
    	public void testArray(){
        	//* Array
        	int[] arr = new int[5];
        	arr[0]=3;
        	System.out.println("----- Array -----");
        	System.out.println(arr[0]);
    		System.out.println("length: "+arr.length);
    	}
     	
    	public void testArrayList(){
    		//* Collection, List
        	ArrayList<String> list = new ArrayList<String>(5);
        	list.add(0, "a");
        	list.add(1, "b");
        	list.add(1, "c");
        	list.set(1, "d");
        	System.out.println("----- ArrayList -----");
        	System.out.println(list);
        	System.out.println(list.get(1));
        	System.out.println("size: "+list.size());
    	}
    	
    	public void testHashMap(){
    		//* Map, HashMap
    		HashMap<String, String> map = new HashMap<String, String>(5);
    		map.put("me", "12345");
    		map.put("you", "54321");
    		map.put("me", "123");
    		System.out.println("----- HashMap -----");
    		System.out.println(map.get("me"));
    		System.out.println(map.get("you"));
    		System.out.println("size: "+map.size());
    	}
    	
    	private void TestIterator(){
    		System.out.println("----- Iterator -----");
    		HashSet<String> s = new HashSet<String>(5);
    		s.add("1");
    		s.add("2");
    		s.add("3");
    		System.out.println(s);
    		Iterator<String> i = s.iterator();
    		while(i.hasNext()){
    			System.out.println(i.next());
    		}
    		i.remove();
    		System.out.println(s);
    		i = s.iterator();
    		while(i.hasNext()){
    			System.out.println(i.next());
    		}
    		System.out.println(s);
    	}
   }
}