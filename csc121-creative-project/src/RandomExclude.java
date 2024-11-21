import java.util.Random;
import java.util.Set;
import java.util.HashSet;



public class RandomExclude {
	
	// Starts the ball in a random direction 
	// that is different every time
	
	public static int getRandomWithExclusion(int min, int max, Set<Integer> excluded) {
		Random rand = new Random();
		int result;
		
		do {
			result = rand.nextInt(max - min + 1) + min;
		} while (excluded.contains(result));
	
	return result;	
	}
	
	// Ensures the ball can not start in a direction of 
	// y = 0, y = -1, or y = 1 and 
	// that it starts within -5 and 5
	
	public static int randomInt() {
		Set<Integer> excludedNumbers = new HashSet<>();
		excludedNumbers.add(0);
		excludedNumbers.add(-1);
		excludedNumbers.add(1);
		
		return getRandomWithExclusion(-5, 5, excludedNumbers);
		
	}

}
