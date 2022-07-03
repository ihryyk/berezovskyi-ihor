package com.sprinng.hw_3.controller.mapper;

import com.sprinng.hw_3.controller.dto.BookDTO;
import com.sprinng.hw_3.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book mapToEntity(BookDTO bookDTO);
    BookDTO mapToDto(Book book);
}
