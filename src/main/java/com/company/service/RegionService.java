package com.company.service;

import com.company.dto.RegionDTO;
import com.company.entity.RegionEntity;
import com.company.enums.LangEnum;
import com.company.exception.IdNotFoundException;
import com.company.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionDTO created(RegionDTO dto, Integer pid) {

        RegionEntity entity = new RegionEntity();
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setNameUz(dto.getNameUz());
        entity.setProfileId(pid);
        entity.setKey(dto.getKey());

        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public RegionDTO getById(Integer id) {
        RegionEntity entity = regionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return toDTO(entity);

    }

    public RegionDTO getById(Integer id, LangEnum lang) {
        RegionEntity entity = regionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return toDTO(entity, lang);
    }

    public RegionEntity get(Integer id) {
        RegionEntity entity = regionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return entity;
    }

    public List<RegionDTO> getAll() {
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAll().stream().forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }

    public List<RegionDTO> getAll(Integer page, Integer size, LangEnum lang) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAll(pageable).stream().forEach(entity -> dtoList.add(toDTO(entity, lang)));
        return dtoList;
    }


    public Boolean update(RegionDTO dto, Integer id) {
        Integer n = regionRepository.update(dto.getNameRu(), dto.getNameUz(), dto.getNameEn(), dto.getKey(), id);
        return true;
    }


    public Boolean delete(Integer id) {
        regionRepository.deleteById(id);
        return true;
    }


    private RegionDTO toDTO(RegionEntity entity, LangEnum lang) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        switch (lang) {
            case EN -> dto.setName(entity.getNameEn());
            case RU -> dto.setName(entity.getNameRu());
            case UZ -> dto.setName(entity.getNameUz());
        }
        dto.setKey(entity.getKey());
        dto.setPid(entity.getProfileId());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }


    private RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        dto.setKey(entity.getKey());
        dto.setPid(entity.getProfileId());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}