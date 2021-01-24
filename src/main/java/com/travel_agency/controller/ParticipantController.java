package com.travel_agency.controller;

import com.travel_agency.dto.ParticipantDTO;
import com.travel_agency.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ParticipantController {

    private ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @ResponseBody
    @GetMapping("/participants")
    public List<ParticipantDTO> getAllParticipants(){
        return participantService.getAllParticipants();
    }

    @ResponseBody
    @GetMapping("/participant/{id}")
    public ParticipantDTO getParticipantById(@PathVariable Long id){
        return participantService.getParticipantById(id);
    }

    @GetMapping("/saveParticipant")
    public void saveParticipant(@RequestBody ParticipantDTO participantDTO){
        participantService.saveParticipant(participantDTO);
    }

    @GetMapping("/deleteParticipant/{id}")
    public void deleteParticipant(@PathVariable Long id){
        participantService.deleteParticipantById(id);
    }


}
