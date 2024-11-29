package com.github.tea_pack.sapphire.services;

import com.github.tea_pack.sapphire.repositories.ViewRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VeiwService {

    private final ViewRepository clientRepository;

    // public ViewDB create(ViewDTO dto) {
    // return clientRepository.save(ViewDB.builder()
    // .clientId(dto.getClientId())
    // .address(dto.getAddress())
    // .gender(dto.getGender())
    // .ageMin(dto.getAgeMin())
    // .ageMax(dto.getAgeMax())
    // .build());
    // }

}
