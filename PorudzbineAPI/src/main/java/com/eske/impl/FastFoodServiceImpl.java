package com.eske.impl;

import com.eske.dto.FastFoodDTO;
import com.eske.model.Adresa;
import com.eske.model.FastFood;
import com.eske.model.User;
import com.eske.repo.AddressRepo;
import com.eske.repo.FastFoodRepo;
import com.eske.repo.UserRepo;
import com.eske.req.CreateFastFoodREQ;
import com.eske.service.FastFoodService;
import com.eske.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FastFoodServiceImpl implements FastFoodService {


    @Autowired
    private FastFoodRepo fastFoodRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;


    @Autowired
    private UserService userService;


    @Override
    public FastFood createFastFood(CreateFastFoodREQ req, User user) {

        Adresa adresa = addressRepo.save(req.getAdresa());

        FastFood fastFood = new FastFood();
        fastFood.setFastFoodAdress(adresa);
        fastFood.setFastFoodName(req.getIme());
        fastFood.setDescription(req.getDescrption());
        fastFood.setOrderListImages(req.getImages());
        fastFood.setContactInfo(req.getContactInfo());
        fastFood.setRegDate(LocalDateTime.now());

        fastFood.setOwner(user);

        return fastFoodRepo.save(fastFood);


    }

    @Override
    public FastFood updateFastFood(Long fastFoodID, CreateFastFoodREQ updatedFastFood) {
        Optional<FastFood> fastFoodOptional = fastFoodRepo.findById(fastFoodID);


        if(fastFoodOptional.isPresent())
        {
            FastFood fastFood = fastFoodOptional.get();

            if(fastFood.getDescription() != null)
            {
                fastFood.setDescription(updatedFastFood.getDescrption());
            }

            if(fastFood.getFastFoodName() != null)
            {
                fastFood.setFastFoodName(updatedFastFood.getIme());
            }


            return fastFoodRepo.save(fastFood);

        }
        else
        {
            throw new EntityNotFoundException("Ne postoji taj FastFood");
        }

    }

    @Override
    public void deleteFastFood(Long fastFoodID) {

        fastFoodRepo.findById(fastFoodID)
                .ifPresentOrElse(
                        fastFood -> fastFoodRepo.deleteById(fastFoodID),
                        () -> {
                            throw new EntityNotFoundException("FastFood not found with ID: " + fastFoodID);
                        }
                );



    }

    @Override
    public List<FastFood> allFastFoods() {
        return fastFoodRepo.findAll();
    }

    @Override
    public List<FastFood> searchFastFood(String FastFoodName) {
       return fastFoodRepo.findBySearchQuery(FastFoodName);
    }

    @Override
    public FastFood searchFastFoodID(Long fastFoodID) {

        return fastFoodRepo.findById(fastFoodID)
                .orElseThrow(() -> new EntityNotFoundException("Ne postoji taj FastFood sa ID: " + fastFoodID));}

    @Override
    public FastFood getFastFoodBYUserId(Long UserID) {

       return fastFoodRepo.findhByOwnerId(UserID)
                .orElseThrow(()-> new EntityNotFoundException("Nema vlasnika restorana sa IDjem " + UserID));


    }

    @Override
    public FastFoodDTO addToFav(Long fastFoodID, User user) {

        FastFoodDTO fastFoodDTO = new FastFoodDTO();

        Optional<FastFood> fastFoodOpt = fastFoodRepo.findById(fastFoodID);

        if(fastFoodOpt.isPresent())
        {
            FastFood fastFood = fastFoodOpt.get();

            fastFoodDTO.setOpis(fastFood.getDescription());
            fastFoodDTO.setIme(fastFood.getFastFoodName());
            fastFoodDTO.setSlike(fastFood.getOrderListImages());
            fastFoodDTO.setFastFoodID(fastFood.getFastfoodID());
            fastFoodDTO.setFastFoodID(fastFoodID);


            Boolean isFav = false;

            List<FastFoodDTO> omiljene = user.getOmiljeno();

            for(FastFoodDTO omiljeno : omiljene)
            {
                if(omiljeno.getFastFoodID().equals(fastFoodID))
                {
                    isFav = true;
                    break;
                }
            }

            /// PROVJERITI !!!!!!!!!!!!!!

            if(isFav)
            {
                omiljene.removeIf(omiljeno -> omiljeno.getFastFoodID().equals(fastFoodID));
            }
            else
            {
                omiljene.add(fastFoodDTO);
            }

            userRepo.save(user);
            return fastFoodDTO;




        }
        else
        {
            throw  new EntityNotFoundException("Nema restorana sa IDjem : " + fastFoodID);
        }




    }

    @Override
    public FastFood upadateFastFoodStatus(Long id) {

       Optional<FastFood> fastFoodOptional = fastFoodRepo.findById(id);

       if(fastFoodOptional.isPresent())
       {
           FastFood fastFood = fastFoodOptional.get();

           fastFood.setOpen(!fastFood.getOpen());
           return fastFoodRepo.save(fastFood);

       }
       else
       {
           throw  new EntityNotFoundException("Nema restorana sa IDjem : " + id);
       }
    }
}
