package com.pfe.ffs.services.voix;

import com.pfe.ffs.entity.Voix;
import com.pfe.ffs.repository.VoixRepo;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class VoixServiceImpl implements VoixService{
    @Autowired
    private VoixRepo voixRepo;

    @PostConstruct
    public void initVoix() {
        saveVoix("Terre");
        saveVoix("Mer");
        saveVoix("Pipe");
    }

    public boolean isVoixExists(String name) {
        Optional<Voix> optionalVoix = voixRepo.findByName(name);
        return optionalVoix.isPresent();
    }

    private void saveVoix(String nom) {
        Voix voix = new Voix();
        if (!isVoixExists(nom)){
            voix.setName(nom);
            voixRepo.save(voix);
        }
    }

    @Override
    public List<Voix> getAllVoix(){return voixRepo.findAll();}
}
