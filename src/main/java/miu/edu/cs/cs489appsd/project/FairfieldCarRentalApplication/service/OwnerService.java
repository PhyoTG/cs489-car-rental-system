package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.OwnerNotFoundException;

import java.util.List;

public interface OwnerService {
    OwnerResponse createOwner(OwnerRequest request);
    OwnerResponse2 getOwner(Integer OwnerId) throws OwnerNotFoundException;
    OwnerResponse2 updateOwner(Integer OwnerId, OwnerRequest request) throws OwnerNotFoundException ;
    void deleteOwner(Integer OwnerId);
    List<OwnerResponse2> getAllOwners();
}
