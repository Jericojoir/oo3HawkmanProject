public abstract class Card {
    protected String question;
    protected String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public abstract void displayQuestion();

    public abstract boolean checkAnswer(String userAnswer);
}
