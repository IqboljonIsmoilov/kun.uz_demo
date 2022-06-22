package com.company.service;

import com.company.dto.ArticleTypeDTO;
import com.company.entity.ArticleTypeEntity;
import com.company.enums.LangEnum;
import com.company.exception.IdNotFoundException;
import com.company.repository.ArticleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleTypeService {

    private final ArticleTypeRepository articleTypeRepository;


    public ArticleTypeDTO created(ArticleTypeDTO dto, Integer pid) {

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setNameUz(dto.getNameUz());
        entity.setProfileId(pid);
        entity.setKey(dto.getKey());

        articleTypeRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


    public ArticleTypeDTO getById(Integer id) {
        ArticleTypeEntity entity = articleTypeRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return toDTO(entity);
    }


    public ArticleTypeDTO getById(Integer id, LangEnum lang) {
        ArticleTypeEntity entity = articleTypeRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return toDTO(entity, lang);
    }


    public ArticleTypeEntity get(Integer id) {
        ArticleTypeEntity entity = articleTypeRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return entity;
    }


    public List<ArticleTypeDTO> getAll() {
        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        articleTypeRepository.findAll().stream()
                .forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }


    public List<ArticleTypeDTO> getAll(Integer page, Integer size, LangEnum lang) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<ArticleTypeDTO> dtoList = new LinkedList<>();

        articleTypeRepository.findAll(pageable).stream()
                .forEach(entity -> dtoList.add(toDTO(entity, lang)));
        return dtoList;
    }


    public Boolean update(ArticleTypeDTO dto, Integer id) {

        Integer n = articleTypeRepository.update(dto.getNameRu(), dto.getNameUz(), dto.getNameEn(), dto.getKey(), id);
        return n > 0;
    }


    public Boolean delete(Integer id) {
        articleTypeRepository.deleteById(id);
        return true;
    }


    private ArticleTypeDTO toDTO(ArticleTypeEntity entity, LangEnum lang) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
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


    private ArticleTypeDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
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