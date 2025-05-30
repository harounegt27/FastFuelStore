package com.pfe.ffs.services.voix;

import com.pfe.ffs.entity.Voix;

import java.util.List;

public interface VoixService{
    void initVoix();
    public boolean isVoixExists(String name);

    public List<Voix> getAllVoix();
}
