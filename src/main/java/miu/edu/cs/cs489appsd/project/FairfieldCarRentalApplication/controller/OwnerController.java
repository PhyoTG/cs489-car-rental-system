package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.controller;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.OwnerNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ffweb/api/v1/owners")
public class OwnerController {

    private final OwnerService OwnerService;

    @Autowired
    public OwnerController(OwnerService OwnerService) {
        this.OwnerService = OwnerService;
    }

    // Create a new Owner
    @PostMapping(value = "/register")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerRequest request) {
        OwnerResponse response = OwnerService.createOwner(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get a Owner by ID
    @GetMapping(value = "/{OwnerId}")
    public ResponseEntity<OwnerResponse2> getOwner(@PathVariable int OwnerId) throws OwnerNotFoundException {
        OwnerResponse2 response = OwnerService.getOwner(OwnerId);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a Owner by ID
    @PutMapping(value = "/{OwnerId}")
    public ResponseEntity<OwnerResponse2> updateOwner(@PathVariable int OwnerId,
                                                           @RequestBody OwnerRequest request) throws OwnerNotFoundException {
        OwnerResponse2 response = OwnerService.updateOwner(OwnerId, request);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a Owner by ID
    @DeleteMapping("/{OwnerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable int OwnerId) {
        OwnerService.deleteOwner(OwnerId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    // Get all Owners
    @GetMapping(value = "/all")
    public ResponseEntity<List<OwnerResponse2>> getAllOwners() {
        List<OwnerResponse2> responses = OwnerService.getAllOwners();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}

