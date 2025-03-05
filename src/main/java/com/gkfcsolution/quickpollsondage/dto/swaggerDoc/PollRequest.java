package com.gkfcsolution.quickpollsondage.dto.swaggerDoc;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Représente les données nécessaires pour créer un sondage")
public class PollRequest {

    @Schema(description = "Question du sondage", example = "Quel est votre langage préféré ?")
    private String question;

    @Schema(description = "Liste des options possibles", example = "[\"Java\", \"Python\", \"JavaScript\"]")
    private List<String> options;

    // Getters & Setters


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
