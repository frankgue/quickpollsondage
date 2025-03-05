package com.gkfcsolution.quickpollsondage.v2.controller;

import com.gkfcsolution.quickpollsondage.dto.swaggerDoc.ErrorResponse;
import com.gkfcsolution.quickpollsondage.dto.swaggerDoc.PollResponse;
import com.gkfcsolution.quickpollsondage.exception.ResourceNotFoundException;
import com.gkfcsolution.quickpollsondage.model.Poll;
import com.gkfcsolution.quickpollsondage.repository.PollRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController("pollControllerV2")
@RequestMapping("/v2/")
@Tag(name = "Poll Controller", description = "API permettant de gérer les sondages")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        pollRepository.findById(pollId).orElseThrow(() -> new ResourceNotFoundException("Poll with id " + pollId + " not found"));
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    @Operation(
            summary = "Récupérer tous les sondages",
            description = "Retourne une liste de tous les sondages disponibles."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des sondages récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PollResponse.class), array = @ArraySchema(
                            schema = @Schema(implementation = PollResponse.class)
                    ))

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    @Operation(
            summary = "Créer un nouveau sondage",
            description = "Cette API permet de créer un nouveau sondage en fournissant une question et une liste d'options.",
            tags = {"Sondages"}
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Sondage créé avec succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PollResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requête invalide (exemple : question vide ou options insuffisantes)",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erreur interne du serveur",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<Poll> createPoll(@Valid @RequestBody Poll poll) {
        Poll savedPoll = pollRepository.save(poll);

        URI newPollUriLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPoll.getId())
                .toUri();
        return ResponseEntity.created(newPollUriLocation).body(savedPoll);
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    @Operation(
            summary = "Récupérer un sondage par son ID",
            description = "Permet de récupérer les détails d'un sondage en fournissant son identifiant."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sondage récupéré avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PollResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requête invalide (ID incorrect)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Sondage introuvable",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public Poll getPoll(@PathVariable Long pollId) {
        return pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found with ID: " + pollId));
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    @Operation(
            summary = "Mettre à jour un sondage existant",
            description = "Mise à jour d'un sondage spécifique en fonction de son ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sondage mis à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PollResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Sondage invalide ou données de mise à jour incorrectes",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Sondage non trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<Poll> updatePoll(@PathVariable Long pollId, @Valid @RequestBody Poll poll) {
        if (!pollRepository.existsById(pollId)) {
            throw new ResourceNotFoundException("Poll not found with ID: " + pollId);
        }
        poll.setId(pollId);
        Poll savedPoll = pollRepository.save(poll);
        return new ResponseEntity<>(savedPoll, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    @Operation(
            summary = "Supprimer un sondage existant",
            description = "Suppression d'un sondage spécifique en fonction de son ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Sondage supprimé avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Sondage non trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        if (!pollRepository.existsById(pollId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Poll not found", "pollId", pollId));
        }
        pollRepository.deleteById(pollId);
        return ResponseEntity.noContent().build(); // 204 No Content pour une suppression réussie
//        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Simule la récupération de sondages
    private List<PollResponse> fetchAllPolls() {
        return Arrays.asList(
                new PollResponse(1L, "Quel est votre langage préféré ?", Arrays.asList("Java", "Python", "C++")),
                new PollResponse(2L, "Quel est votre framework préféré ?", Arrays.asList("Spring Boot", "Django", "Express.js"))
        );
    }
}
