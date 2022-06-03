package hu.progmatic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Runner> runnerLists = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("resources\\ub2017egyeni.txt"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Runner runner = new Runner(line);
                runnerLists.add(runner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("3. feladat: Egyéni indulók: " + runnerLists.size());
        System.out.println("4. feladat: Célba érkező női sportolók: " + numberOfWomensFullDistance(runnerLists) + " fő");
        System.out.println("5. feladat: Kérem a sportoló nevét:");
        String name = sc.nextLine();
        System.out.println(resultOfSearch(name, runnerLists));
        System.out.println("7. feladat: Átlagos idő: " + (avarageCounter(runnerLists)) + " óra");
        System.out.println("8. feladat: Verseny győztesei");
        System.out.println(femaleWinner(runnerLists));
        System.out.println(maleWinner(runnerLists));

    }

    public static int numberOfWomensFullDistance(List<Runner> runnerLists) {
        int counter = 0;
        for (Runner runnerList : runnerLists) {
            if (runnerList.getCategory().equalsIgnoreCase("Noi") && runnerList.getPercentage() == 100) {
                counter++;
            }
        }
        return counter;
    }

    public static String resultOfSearch(String name, List<Runner> runnerLists) {
        for (Runner runnerList : runnerLists) {
            if (runnerList.getName().equalsIgnoreCase(name)) {
                if (runnerList.getPercentage() == 100) {
                    return "Indult egyéniben a sportoló? Igen\n" +
                            "Teljesítette a teljes távot? Igen";
                } else {
                    return "Indult egyéniben a sportoló? Igen\n" +
                            "Teljesítette a teljes távot? Nem";
                }
            }
        }
        return "Indult egyéniben a sportoló? Nem";
    }

    public static double totalTimeOfRunnerInHour(Runner runner) {
        double totalTimeInHour;
        String[] pieces = runner.getTime().split(":");
        double hourInSecond = (Integer.parseInt(pieces[0]) * 60) * 60;
        double minuteInSecond = Integer.parseInt(pieces[1]) * 60;
        double second = Integer.parseInt(pieces[2]);
        double totalTimeInSecond = second + minuteInSecond + hourInSecond;
        totalTimeInHour = (totalTimeInSecond / 60) / 60;
        return totalTimeInHour;
    }

    public static String femaleWinner(List<Runner> runnerLists) {
        double min = Integer.MAX_VALUE;
        Runner femaleWinner = new Runner();
        for (Runner runnerList : runnerLists) {
            if (runnerList.getPercentage() == 100 && runnerList.getCategory().equalsIgnoreCase("Noi")) {
                if (totalTimeOfRunnerInHour(runnerList) < min) {
                    min = totalTimeOfRunnerInHour(runnerList);
                    femaleWinner = runnerList;

                }
            }
        }
        return "Nők: " + femaleWinner.getName() + " (" + femaleWinner.getId() + ".)" + " - " + femaleWinner.getTime();
    }

    public static String maleWinner(List<Runner> runnerLists) {
        Runner maleWinner = new Runner();
        double min = Integer.MAX_VALUE;
        for (Runner runnerList : runnerLists) {
            if (runnerList.getPercentage() == 100 && runnerList.getCategory().equalsIgnoreCase("Ferfi")) {
                if (totalTimeOfRunnerInHour(runnerList) < min) {
                    min = totalTimeOfRunnerInHour(runnerList);
                    maleWinner = runnerList;
                }
            }
        }
        return "Férfiak: " + maleWinner.getName() + " (" + maleWinner.getId() + ".)" + " - " + maleWinner.getTime();
    }

    public static double avarageCounter(List<Runner> runnerLists) {
        double sum = 0;
        int counter = 0;
        for (Runner runnerList : runnerLists) {
            if (runnerList.getPercentage() == 100 && runnerList.getCategory().equalsIgnoreCase("Ferfi")) {
                sum += totalTimeOfRunnerInHour(runnerList);
                counter++;
            }
        }
        return sum / counter;
    }
}
