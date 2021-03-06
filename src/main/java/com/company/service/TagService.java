package com.company.service;

import com.company.dto.TagDTO;
import com.company.entity.TagEntity;
import com.company.enums.LangEnum;
import com.company.exception.IdNotFoundException;
import com.company.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagDTO created(TagDTO dto, Integer pid) {

        TagEntity entity = new TagEntity();
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setNameUz(dto.getNameUz());
        entity.setProfileId(pid);
        entity.setKey(dto.getKey());

        tagRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


    public TagDTO getById(Integer id) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return toDTO(entity);
    }


    public TagEntity get(Integer id) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id not Found"));
        return entity;
    }


    public List<TagDTO> get(List<Integer> id, LangEnum lang) {
        List<TagEntity> entity = tagRepository.findAllById(id);
        List<TagDTO> dtoList = new LinkedList<>();
        for (TagEntity tagEntity : entity) {
            dtoList.add(toDTO(tagEntity, lang));
        }
        return dtoList;
    }


    public List<TagDTO> getAll() {
        List<TagDTO> dtoList = new LinkedList<>();
        tagRepository.findAll().stream().forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }


    public List<TagDTO> getAll(Integer page, Integer size, LangEnum lang) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<TagDTO> dtoList = new LinkedList<>();
        tagRepository.findAll(pageable).stream().forEach(entity -> dtoList.add(toDTO(entity, lang)));
        return dtoList;
    }


    public Boolean update(TagDTO dto, Integer id) {

        Integer n = tagRepository.update(dto.getNameRu(), dto.getNameUz(), dto.getNameEn(), dto.getKey(), id);
        return true;
    }


    public Boolean delete(Integer id) {
        tagRepository.deleteById(id);
        return true;
    }


    private TagDTO toDTO(TagEntity entity, LangEnum lang) {
        TagDTO dto = new TagDTO();
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


    private TagDTO toDTO(TagEntity entity) {
        TagDTO dto = new TagDTO();
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