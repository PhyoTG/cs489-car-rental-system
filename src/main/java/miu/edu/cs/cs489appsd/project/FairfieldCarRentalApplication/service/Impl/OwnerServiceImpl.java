package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl;


import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Car.CarResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.Owner.OwnerResponse2;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.exception.OwnerNotFoundException;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.CarOwner;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.OwnerRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository OwnerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository OwnerRepository) {
        this.OwnerRepository = OwnerRepository;
    }
    @Override
    public OwnerResponse createOwner(OwnerRequest request) {
        var Owner = OwnerRepository.save(new CarOwner(null,request.firstName(),request.lastName(),request.contactPhone(),request.email()));
        return new OwnerResponse(Owner.getOwnerId(),Owner.getFirstName(),Owner.getLastName(),Owner.getContactPhone(),Owner.getEmail());
    }

    @Override
    public OwnerResponse2 getOwner(Integer OwnerId) throws OwnerNotFoundException {
        CarOwner a = OwnerRepository.findById(OwnerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Error: Owner with Id, %d, is not found",
                        OwnerId)));
        return a!=null? new OwnerResponse2(
                a.getOwnerId(),
                a.getFirstName(),
                a.getLastName(),
                a.getContactPhone(),
                a.getEmail(),
                a.getCarList()!=null? a.getCarList().stream()
                        .map(b -> new CarResponse(
                                b.getCarId(),
                                b.getVin(),
                                b.getModel(),
                                b.getMake(),
                                b.getMileage(),
                                b.getYear(),
                                b.getRentalRate()
                        )).toList()
                        :null):null;
    }

    @Override
    public OwnerResponse2 updateOwner(Integer OwnerId, OwnerRequest request) throws OwnerNotFoundException{
        var Owner = OwnerRepository.findById(OwnerId) .orElseThrow(() -> new OwnerNotFoundException(String.format("Error: Owner with Id, %d, is not found",
                OwnerId)));
        if(Owner != null ) {
            Owner.setOwnerId(OwnerId);
            Owner.setFirstName(request.firstName());
            Owner.setLastName(request.lastName());
            Owner.setContactPhone(request.contactPhone());
            Owner.setEmail(request.email());
            OwnerRepository.save(Owner);

            return new OwnerResponse2(
                    Owner.getOwnerId(),
                    Owner.getFirstName(),
                    Owner.getLastName(),
                    Owner.getContactPhone(),
                    Owner.getEmail(),
                    Owner.getCarList()!=null? Owner.getCarList().stream()
                            .map(b -> new CarResponse(
                                    b.getCarId(),
                                    b.getVin(),
                                    b.getModel(),
                                    b.getMake(),
                                    b.getMileage(),
                                    b.getYear(),
                                    b.getRentalRate()
                            )).toList()
                            :null);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOwner(Integer OwnerId) {
        OwnerRepository.deleteById(OwnerId);
    }

    @Override
    public List<OwnerResponse2> getAllOwners() {
        return OwnerRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName"))
                .stream()
                .map(a -> new OwnerResponse2(
                        a.getOwnerId(),
                        a.getFirstName(),
                        a.getLastName(),
                        a.getContactPhone(),
                        a.getEmail(),
                        a.getCarList()!=null? a.getCarList().stream()
                                .map(b -> new CarResponse(
                                        b.getCarId(),
                                        b.getVin(),
                                        b.getModel(),
                                        b.getMake(),
                                        b.getMileage(),
                                        b.getYear(),
                                        b.getRentalRate()
                                )).toList()
                                :null
                )).toList();
    }
}
