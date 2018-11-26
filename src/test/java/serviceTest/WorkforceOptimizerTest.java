package serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import service.WorkforceOptimizer;

@RunWith(Parameterized.class)
public class WorkforceOptimizerTest {

	WorkforceOptimizer workforceOptimizer;

	@Before
	public void initialize() {
		workforceOptimizer = new WorkforceOptimizer();
	}


	// 1)
	// In: { rooms: [35, 21, 17], senior: 10, junior: 6 }
	// Out: [ {senior: 3, junior: 1}, {senior: 1, junior: 2}, {senior: 2, junior: 0}
	// ]

	// 2)
	// In: { rooms: [24, 28], senior: 11, junior: 6 }
	// Out: [ {senior: 2, junior: 1}, {senior: 2, junior: 1} ]
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ new Integer[] { 35, 21, 17 }, 10, 6,	new String[] { "senior:3,junior:1", "senior:1,junior:2", "senior:2,junior:0" } },
				{ new Integer[] { 24, 28 }, 11, 6, new String[] { "senior:2,junior:1", "senior:2,junior:1" } },
				{ new Integer[] { 12 }, 13, 6,	new String[] { "senior:1,junior:0"} } });

	}

	@Parameter(0)
	public Integer[] rooms;

	@Parameter(1)
	public Integer srCapacityCleaner;

	@Parameter(2)
	public Integer jrCapacityCleaner;

	@Parameter(3)
	public String[] expected;

	@Test
	public void test() {
		List<Map<String, String>> res = workforceOptimizer.optimize(rooms, jrCapacityCleaner, srCapacityCleaner);
		assertEquals(res.size(), expected.length);

		for (int i = 0; i < res.size(); i++) {
			Map<String, String> map = res.get(i);
			String key = map.keySet().iterator().next();
			String value = map.get(key);
			String[] split = expected[i].split(",");
			assertEquals(key, split[0]);
			assertEquals(value, split[1]);
		}

	}
}
