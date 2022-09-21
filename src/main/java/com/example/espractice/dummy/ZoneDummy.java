package com.example.espractice.dummy;

import com.example.espractice.zone.Zone;
import com.example.espractice.zone.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ZoneDummy implements ApplicationRunner {

    private final ZoneRepository zoneRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        zoneRepository.save(Zone.of("경기도","안양시"));
    }
}
