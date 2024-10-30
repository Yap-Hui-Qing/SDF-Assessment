package vttp.batch5.sdf.task01;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException {

		// check if csv file is passed in
		if (args.length < 1) {
			System.err.println("Missing csv");
			System.exit(1);
		}

		FileReader csv = new FileReader("task01/" + args[0]);
		BufferedReader csvbr = new BufferedReader(csv);

		String[] header = csvbr.readLine().split((","));

		String line;
		List<BikeEntry> bikeList = new LinkedList<>();
		while ((line = csvbr.readLine()) != null) {
			String[] values = line.split(",");

			BikeEntry entry = BikeEntry.toBikeEntry(values);

			bikeList.add(entry);
		}

		Map<BikeEntry, Integer> bikeMap = new HashMap<>();

		for (BikeEntry entry : bikeList) {
			int totalCyclists = 0;
			// sum of casual and registered cyclists
			totalCyclists = entry.getCasual() + entry.getRegistered();
			bikeMap.put(entry, totalCyclists);
		}

		// find the top 5 days with the most cyclist
		Map<BikeEntry, Integer> topfive = bikeMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(5)
				.collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (BikeEntry e : topfive.keySet()) {
			for (int i = 0; i < 5; i++){
				// description of position
				Map<Integer, String> position = Map.of(0, "highest",
						1, "second highest",
						2, "third highest",
						3, "fourth highest",
						4, "fifth highest");

				// description of weather
				Map<Integer, String> weather = Map.of(1, "Clear, Few clouds, Partly cloudy, Partly cloudy",
						2, "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
						3, "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
						4, "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog");

				// description of holiday
				Map<Integer, String> holiday = Map.of(1, "a holiday",
						2, "not a holiday");
				List<String> output = new LinkedList<>();
				String input1 = "The <position> recorded number of cyclists was in <season>, on a <day> in the month of <month>.";
				input1 = input1.replaceAll("<", "(");
				input1 = input1.replaceAll(">", ")");
				String input2 = "There were a total of <total> cyclists. The weather was <weather>.";
				input2 = input2.replaceAll("<", "(");
				input2 = input2.replaceAll(">", ")");
				String input3 = "<day> was <holiday>.";
				input3 = input3.replaceAll("<", "(");
				input3 = input3.replaceAll(">", ")");
				output.add(input1);
				output.add(input2);
				output.add(input3);
				for (String s : output) {
					if (s.contains("(season)")) {
						String replace = Utilities.toSeason(e.getSeason()) + " " + "(season)";
						s = s.replace("(season)", replace);
					} 
					if (s.contains("(position)")) {
						String replace = position.get(i) + " " + "(position)";
						s = s.replace("(position)", replace);
					}
					if (s.contains("(day)")) {
						s = s.replace("(day)", Utilities.toWeekday(e.getWeekday()) + " " + "(day)");
					} 
					if (s.contains("(month)")) {
						s = s.replace("(month)", Utilities.toMonth(e.getMonth()) + " " + "(month)");
					} 
					if (s.contains("(total)")) {
						s = s.replace("(total)", topfive.get(e) + " " + "(total)");
					} 
					if (s.contains("(weather)")) {
						s = s.replace("(weather)", weather.get(e.getWeather()) + " " + "(weather)");
					} 
					if (s.contains("(holiday)")) {
						s = s.replace("(holiday)", holiday.get(e.isHoliday()) + " " + "(holiday)");
					} 
					System.out.println(s);
				}
		}

		// description of position
		Map<Integer, String> position = Map.of(0, "highest",
				1, "second highest",
				2, "third highest",
				3, "fourth highest",
				4, "fifth highest");

		// description of season
		Map<Integer, String> season = Map.of(1, "spring",
				2, "summer",
				3, "fall",
				4, "winter");

		// description of day
		Map<Integer, String> day = Map.of(0, "Sunday",
				1, "Monday",
				2, "Tuesday",
				3, "Wednesday",
				4, "Thursday",
				5, "Friday",
				6, "Saturday");

		// description of month
		Map<Integer, String> month = Map.ofEntries(
				Map.entry(1, "January"),
				Map.entry(2, "February"),
				Map.entry(3, "March"),
				Map.entry(4, "April"),
				Map.entry(5, "May"),
				Map.entry(6, "June"),
				Map.entry(7, "July"),
				Map.entry(8, "August"),
				Map.entry(9, "September"),
				Map.entry(10, "October"),
				Map.entry(11, "November"),
				Map.entry(12, "December"));

		// description of weather
		Map<Integer, String> weather = Map.of(1, "Clear, Few clouds, Partly cloudy, Partly cloudy",
				2, "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
				3, "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
				4, "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog");

		// description of holiday
		Map<Boolean, String> holiday = Map.of(true, "a holiday",
				false, "not a holiday");
		}
	}
}

