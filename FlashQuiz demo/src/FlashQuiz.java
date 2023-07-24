import java.util.*;

public class FlashQuiz {
    private List<Flashcard> flashcards;
    private Scanner scanner;
    private Map<String, Integer> userScores;

    public FlashQuiz() {
        flashcards = new ArrayList<>();
        scanner = new Scanner(System.in);
        userScores = new HashMap<>();
    }
    public void addFlashcard() {
        System.out.print("Enter the question: ");
        String question = scanner.nextLine();
        System.out.print("Enter the answer: ");
        String answer = scanner.nextLine();

        Flashcard flashcard = new Flashcard(question, answer);
        flashcards.add(flashcard);
        System.out.println("Flashcard added!");
    }

    public void startQuiz() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards available. Please add flashcards before starting the quiz.");
            return;
        }

        int score = 0;
        int totalQuestions = flashcards.size();

        for (int i = 0; i < totalQuestions; i++) {
            Flashcard flashcard = flashcards.get(i);
            System.out.print("Question " + (i + 1) + ": " + flashcard.getQuestion() + " Answer: ");
            String userAnswer = scanner.nextLine();

            if (flashcard.getAnswer().equalsIgnoreCase(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was: " + flashcard.getAnswer());
            }
        }

        double percentage = (double) score / totalQuestions * 100;
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        userScores.put(userName, score);

        System.out.println("Quiz completed, " + userName + "! Your score: " + score + "/" + totalQuestions + " (" + percentage + "%)");
    }

    public void scoreboard() {
        System.out.println("===== Scoreboard =====");
        if (userScores.isEmpty()) {
            System.out.println("No scores to display.");
        } else {
            // Sort the users by their scores in descending order
            List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(userScores.entrySet());
            sortedScores.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            int rank = 1;
            for (Map.Entry<String, Integer> entry : sortedScores) {
                System.out.println(rank + ". " + entry.getKey() + ": " + entry.getValue() + " points");
                rank++;

                // Show the top 5 scorers
                if (rank > 5) {
                    break;
                }
            }
        }
        System.out.println("======================");
    }

    public static void main(String[] args) {
        FlashQuiz quiz = new FlashQuiz();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add flashcard");
            System.out.println("2. Start quiz");
            System.out.println("3. Show Scoreboard");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    quiz.addFlashcard();
                    break;
                case 2:
                    quiz.startQuiz();
                    break;
                case 3:
                    quiz.scoreboard();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
