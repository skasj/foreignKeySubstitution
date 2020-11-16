package org.example.foreignKeySubstitution.constants;

public enum ExamRequestType {
    CHOICE_QUESTION(0), COMPLETION(1);
    private int tag;

    public int getTag() {
        return tag;
    }

    ExamRequestType(int tag) {
        this.tag = tag;
    }
}
