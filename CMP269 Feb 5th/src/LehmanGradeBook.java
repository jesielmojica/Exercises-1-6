
public class LehmanGradeBook {

	    public boolean isPassing(int grade) {
	        validateGrade(grade);
	        return grade >= 70;
	    }

	    public char getLetterGrade(int score) {
	        validateGrade(score);

	        if (score >= 90) return 'A';
	        if (score >= 80) return 'B';
	        if (score >= 70) return 'C';
	        if (score >= 60) return 'D';
	        return 'F';
	    }

	    private void validateGrade(int grade) {
	        if (grade < 0 || grade > 100) {
	            throw new IllegalArgumentException("Grade must be between 0 and 100.");
	        }
	    }
	}
