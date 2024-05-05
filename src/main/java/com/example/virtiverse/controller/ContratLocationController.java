package com.example.virtiverse.controller;

import com.example.virtiverse.entities.ContratLocation;
import com.example.virtiverse.serviceImp.ContratLocationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratlocation")
@AllArgsConstructor
public class ContratLocationController {
    ContratLocationService contratLocationService;
    @GetMapping("/list")
    public List <ContratLocation> getContratsLocation()
    {
        return contratLocationService.getAllContratsLocation();
    }
    @PostMapping("/addContratLocation")
    public ContratLocation addContratLocation(@Validated @RequestBody ContratLocation contratLocation , @RequestParam Long id_maison) {
        return contratLocationService.addContratLocation(contratLocation,id_maison);
    }

    @PutMapping("/updateContratLocation/{id_location}")
    public ContratLocation updateContrat(@Validated @RequestBody ContratLocation contratLocation,@PathVariable("id_location") Long id_location ,@RequestParam Long id_maison ){
        ContratLocation c=contratLocationService.getContratLocation(id_location);
        contratLocation.setId_contrat(c.getId_contrat());
        return contratLocationService.updateContratLocation(contratLocation,id_maison);
    }

    @GetMapping("/getContratLocation/{id_location}")
    public ContratLocation getContratLocation(@PathVariable("id_location") Long id_location) {
        return contratLocationService.getContratLocation(id_location);
    }

    @DeleteMapping("/deleteContratLocation/{id_location}")
    public void deleteContratLocation( @PathVariable ("id_location")Long id_location) {
        contratLocationService.deleteContratLocation(id_location);
    }
    @PostMapping("/addContratMaisonColocataire")
    public ContratLocation addContratByUserAndMaison(@RequestBody ContratLocation contratLocation, @RequestParam Long id, @RequestParam Long idMmaison) {
        return contratLocationService.addContratByUserAndMaison(contratLocation,id,idMmaison);
    }

    @GetMapping("/contrats")
    public List<ContratLocation> getContratsByUser(@RequestParam("id") Long id) {
        return contratLocationService.getContratsByUser(id);
    }


}
