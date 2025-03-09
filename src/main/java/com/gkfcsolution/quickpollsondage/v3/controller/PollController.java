package com.gkfcsolution.quickpollsondage.v3.controller;

import com.gkfcsolution.quickpollsondage.dto.swaggerDoc.ErrorResponse;
import com.gkfcsolution.quickpollsondage.dto.swaggerDoc.PollResponse;
import com.gkfcsolution.quickpollsondage.model.Poll;
import com.gkfcsolution.quickpollsondage.repository.PollRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("pollControllerV3")
@RequestMapping("/v3/")
@Tag(name = "Poll Controller", description = "API permettant de gérer les sondages")
public class PollController {

    @Autowired
    private PollRepository pollRepository;


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
    public ResponseEntity<Page<Poll>> getAllPolls(Pageable pageable) {
        Page<Poll> allPolls = pollRepository.findAll(pageable);
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

}
