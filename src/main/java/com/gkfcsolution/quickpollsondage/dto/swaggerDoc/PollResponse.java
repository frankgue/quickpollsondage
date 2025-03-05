package com.gkfcsolution.quickpollsondage.dto.swaggerDoc;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Représente la réponse après la création d'un sondage")
public class PollResponse {

    @Schema(description = "Identifiant unique du sondage", example = "1")
    private Long id;

    @Schema(description = "Question du sondage", example = "Quel est votre langage préféré ?")
    private String question;

    @Schema(description = "Liste des options possibles", example = "[\"Java\", \"Python\", \"JavaScript\"]")
    private List<String> options;

    // Constructeur
    public PollResponse(Long id, String question, List<String> options) {
        this.id = id;
        this.question = question;
        this.options = options;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
