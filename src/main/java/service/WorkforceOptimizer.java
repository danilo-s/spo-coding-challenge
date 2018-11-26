package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class WorkforceOptimizer {

	private final static String SENIOR_LABEL = "senior:";
	private final static String JUNIOR_LABEL = "junior:";

	public List<Map<String, String>> optimize(Integer[] rooms, Integer jrCleaningCapacity, Integer srCleaningCapacity) {
		List<Map<String, String>> result = new ArrayList<>();
		for (int i = 0; i < rooms.length; i++) {
			Map<String, String> srCapacity2jrCapacity = new HashMap<>();
			int totalSr = 0;
			int totalJr = 0;

			int roomCapacity = rooms[i];
			if (roomCapacity < srCleaningCapacity) {
				// one senior it's enough
				totalSr++;
				srCapacity2jrCapacity.put(SENIOR_LABEL + totalSr, JUNIOR_LABEL + totalJr);
			} else {
				// at least one senior
				int tmp = roomCapacity - srCleaningCapacity;
				totalSr++;

				while (tmp > 0) {
					// find out if is more convenient choosing a jr or a sr
					int srCost = tmp / srCleaningCapacity;
					int jrCost = tmp / jrCleaningCapacity;

					int srRemainderCost = tmp % srCleaningCapacity;
					int jrRemainderCost = tmp % jrCleaningCapacity;

					// if the remainder is zero, with one person more i have the optimal solution
					if (srRemainderCost == 0) {
						tmp -= srCleaningCapacity;
						totalSr++;
					} else if (jrRemainderCost == 0) {
						tmp -= jrCleaningCapacity;
						totalJr++;
					}

					// minimize the number of people..
					else if (srCost < jrCost) {
						tmp -= srCleaningCapacity;
						totalSr++;
					} else if (jrCost < srCost) {
						tmp -= jrCleaningCapacity;
						totalJr++;
					} else {
						// if the number of people it's the same,
						// i try to optimize the overcapacity
						int abs = Math.abs(srRemainderCost - srCleaningCapacity);
						int abs2 = Math.abs(jrRemainderCost - jrCleaningCapacity);
						if (abs < abs2) {
							tmp -= srCleaningCapacity;
							totalSr++;
						} else {
							tmp -= jrCleaningCapacity;
							totalJr++;
						}
					}
				}

				srCapacity2jrCapacity.put(SENIOR_LABEL + totalSr, JUNIOR_LABEL + totalJr);
			}
			result.add(srCapacity2jrCapacity);
		}

		return result;

	}

}
