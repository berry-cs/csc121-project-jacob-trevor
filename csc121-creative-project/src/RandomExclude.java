import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class RandomExclude {
	
	public static int getRandomWithExclusion(int min, int max, Set<Integer> excluded) {
		Random rand = new Random();
		int result;
		
		do {
			result = rand.nextInt(max - min + 1) + min;
		} while (excluded.contains(result));
	
	return result;	
	}
	
	public static int randomInt() {
		Set<Integer> excludedNumbers = new HashSet<>();
		excludedNumbers.add(0);
		excludedNumbers.add(-1);
		excludedNumbers.add(1);
		
		return getRandomWithExclusion(-5, 5, excludedNumbers);
		
	}

}
