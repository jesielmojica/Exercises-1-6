import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise3 {

	public static void main(String[] args) {

        try (Scanner fileScanner = new Scanner(new File("students.txt"));
             PrintWriter writer = new PrintWriter("grades_report.txt")) {

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line);

                try {
                    String name = lineScanner.next();

                    int total = 0;
                    int count = 0;

                    while (lineScanner.hasNext()) {
                        String token = lineScanner.next();
                        int score = Integer.parseInt(token);
                        total += score;
                        count++;
                    }

                    if (count == 0) {
                        throw new NumberFormatException();
                    }

                    double average = (double) total / count;

                    if (average < 60) {
                        throw new LowGradeException("Average below 60");
                    }

                    writer.printf("Student: %s | Average: %.2f%n", name, average);

                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid data found. Skipping line: " + line);
                } catch (LowGradeException e) {
                    String name = line.split(" ")[0];
                    writer.printf("Student: %s | Average: BELOW 60 | Warning%n", name);
                } finally {
                    lineScanner.close();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: students.txt file not found.");
        } finally {
            System.out.println("Processing Complete");
        }
    }
}
