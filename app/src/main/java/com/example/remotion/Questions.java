package com.example.remotion;

class Questions {

    private String mQuestions[] = {
            "Guess the facial expression", "Guess the facial expression",
            "Guess the facial expression", "Guess the facial expression",
            "Guess the facial expression"
    };
    private String mChoice[][] = {
            {"angry", "bored", "sad", "worried"},
            {"excited", "bored", "thirsty", "sad"},
            {"excited", "happy", "sad", "tired"},
            {"happy", "tired", "shocked", "worried"},
            {"angry", "sad", "shocked", "hungry"}

    };

    private String mImages[] = {
            "angry",
            "bored",
            "excited",
            "happy",
            "hungry"

    };

    private String mQuestionType[] = {
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton"

    };

    private String mCorrectAnswers[] = {
            "angry",
            "bored",
            "excited",
            "happy",
            "hungry"
    };

    public String[] getChoice(int q) {
        String[] choice = mChoice[q];
        return choice;
    }

    public String getImages(int q) {
        String img = mImages[q];
        return img;
    }

    public int getLength() {
        return mQuestions.length;
    }

    public String getType(int q) {
        String type = mQuestionType[q];
        return type;
    }

    public String getCorrectAnswers(int q) {
        String correct = mCorrectAnswers[q];
        return correct;
    }

    public String getQuestions(int q) {
        String questions = mQuestions[q];
        return questions;
    }

    public void setmQuestions(String[] mQuestions) {
        this.mQuestions = mQuestions;
    }
}