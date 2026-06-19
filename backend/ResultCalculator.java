package backend;

public interface ResultCalculator {
    double passMarks = 50;

    double calculateTotal();
    double calculatePercentage();
    String calculateGrade();
}
