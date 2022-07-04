package com.epam.hw_4.controller.mapper;

import com.epam.hw_4.controller.dto.BookDTO;
import com.epam.hw_4.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book mapToEntity(BookDTO bookDTO);
    BookDTO mapToDto(Book book);
}
