package com.bahn.casestudy.info;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bahn.casestudy.info.ApiNotImplementedException;
import com.bahn.casestudy.info.ApiNotImplementedExceptionResponse;

/**
 * If someone tries to query .../api/...
 */
@RestController
@RequestMapping(path = "/api")
public class NonOperationSiteController {
    private final String NOT_IMPLEMENTED_MSG = "Diese API ist noch nicht implementiert.";

    /**
     * Response in case someone calls /api/ only.
     * @return The error message.
     */
    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<ApiNotImplementedExceptionResponse> getNotImplemented() {
        ApiNotImplementedExceptionResponse response = new ApiNotImplementedExceptionResponse(
            new ApiNotImplementedException(NOT_IMPLEMENTED_MSG)
        );

        return new ResponseEntity<ApiNotImplementedExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }


    /**
     * Response in case someone calls /api only.
     * @return The error message.
     */
    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<ApiNotImplementedExceptionResponse> getNotImplementedWithSlash() {
        ApiNotImplementedExceptionResponse response = new ApiNotImplementedExceptionResponse(
            new ApiNotImplementedException(NOT_IMPLEMENTED_MSG)
        );

        return new ResponseEntity<ApiNotImplementedExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
}
