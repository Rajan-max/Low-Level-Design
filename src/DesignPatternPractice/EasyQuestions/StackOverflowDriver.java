package DesignPatternPractice.EasyQuestions;


import java.util.*;

class User {
    private String username;
    private String password;
    // Other user attributes like name, email, etc.

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters for user attributes
}

class Question {
    private int id;
    private String title;
    private String body;
    private User author;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private int votes;

    public Question(int id, String title, String body, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.votes = 0;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public String getBody(){
        return body;
    }

    public String getTitle(){
        return title;
    }
    public List<Answer>getAnswers(){
        return answers;
    }

    public void upvote() {
        votes++;
    }

    public void downvote() {
        votes--;
    }

    // Getters and setters for question attributes
}

class Answer {
    private int id;
    private String body;
    private User author;
    private List<Comment> comments;
    private int votes;

    public Answer(int id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.comments = new ArrayList<>();
        this.votes = 0;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void upvote() {
        votes++;
    }

    public void downvote() {
        votes--;
    }

    public String getBody(){
        return body;
    }

    // Getters and setters for answer attributes
}

class Comment {
    private int id;
    private String body;
    private User author;

    public Comment(int id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author;
    }

    // Getters and setters for comment attributes
}

class Vote {
    private User user;
    private boolean isUpvote;

    public Vote(User user, boolean isUpvote) {
        this.user = user;
        this.isUpvote = isUpvote;
    }

    // Getters and setters for vote attributes
}

class StackOverflow {
    private List<User> users;
    private List<Question> questions;

    public StackOverflow() {
        this.users = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void postQuestion(Question question) {
        questions.add(question);
    }

    public void answerQuestion(Question question, Answer answer) {
        question.addAnswer(answer);
    }

    public void commentOnQuestion(Question question, Comment comment) {
        question.addComment(comment);
    }

    public void commentOnAnswer(Answer answer, Comment comment) {
        answer.addComment(comment);
    }

    public void upvoteQuestion(Question question) {
        question.upvote();
    }

    public void downvoteQuestion(Question question) {
        question.downvote();
    }

    public void upvoteAnswer(Answer answer) {
        answer.upvote();
    }

    public void downvoteAnswer(Answer answer) {
        answer.downvote();
    }
    public void getAnswerToQuestion(String askedQuestion){
        boolean flag=false;
        for(Question question:questions){
            if(question.getBody().equalsIgnoreCase(askedQuestion) || question.getTitle().equalsIgnoreCase(askedQuestion)){
                flag=true;
                for (Answer answer:question.getAnswers()){
                    System.out.println(answer.getBody());
                }
            }
        }
        if(!flag){
            System.out.println("Something went Wrong!!");
        }

    }
}

public class StackOverflowDriver {
    public static void main(String[] args) {
        // Creating users
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");

        // Creating Stack Overflow instance
        StackOverflow stackOverflow = new StackOverflow();

        // Registering users
        stackOverflow.registerUser(user1);
        stackOverflow.registerUser(user2);

        // Posting questions
        Question q1 = new Question(1, "How to implement StackOverflow?", "I want to implement a simplified version of StackOverflow...", user1);
        stackOverflow.postQuestion(q1);

        // Answering questions
        Answer a1 = new Answer(1, "You can start by defining classes like User, Question, Answer, etc.", user2);
        stackOverflow.answerQuestion(q1, a1);

        Answer a2=new Answer(1, "You can Start by designing uml diagram first and then proceed with coding",user2);
        stackOverflow.answerQuestion(q1,a2);

        // Commenting on questions and answers
        Comment comment1 = new Comment(1, "This is a helpful answer!", user1);
        stackOverflow.commentOnAnswer(a1, comment1);

        stackOverflow.getAnswerToQuestion("How to implement StackOverflow?");

        // Voting on questions and answers
        stackOverflow.upvoteQuestion(q1);
        stackOverflow.upvoteAnswer(a1);
    }
}
