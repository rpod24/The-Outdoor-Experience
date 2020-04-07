import java.util.HashMap; 
import java.util.Map;
import java.util.Random; 


public class HashMapTest{

	
	
public static void main(String[] args) 
    { 
	Random r = new Random();
        HashMap<String, Integer> map 
            = new HashMap<>(); 
  
        print(map);
        for(int x = 0; x<10001; x++) {
        	String key = ""+ x;
        	map.put(key, r.nextInt(9)); 
        }
  
        System.out.println("Size of map is:- "
                           + map.size()); 
  
        print(map); 
        System.out.println(map.get("200"));
  
        map.clear(); 
        print(map); 
    } 
  
    public static void print(Map<String, Integer> map) 
    { 
        if (map.isEmpty()) { 
            System.out.println("map is empty"); 
        } 
  
        else { 
            System.out.println(map); 
        } 
    } 
}