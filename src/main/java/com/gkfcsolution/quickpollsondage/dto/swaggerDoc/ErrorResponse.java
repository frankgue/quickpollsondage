package com.gkfcsolution.quickpollsondage.dto.swaggerDoc;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Modèle de réponse en cas d'erreur")
public class ErrorResponse {

    @Schema(description = "Timestamp de l'erreur", example = "2024-03-05T14:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "Code HTTP de l'erreur", example = "400")
    private int status;

    @Schema(description = "Message d'erreur général", example = "La question est obligatoire")
    private String message;

    @Schema(description = "Détails supplémentaires de l'erreur", example = "[\"Le champ 'question' ne peut pas être vide\"]")
    private List<String> details;

    @Schema(description = "URI de la requête ayant généré l'erreur", example = "/api/polls")
    private String path;

    public ErrorResponse(int status, String message, List<String> details, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }

    public String getPath() {
        return path;
    }
}

