package org.fastcampus.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.fastcampus.post.domain.content.PostPublicationState;

@Converter
public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {

    @Override
    public String convertToDatabaseColumn(PostPublicationState postPublicationState) {
        return postPublicationState.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String s) {
        if (s == null) return PostPublicationState.PUBLIC; // 기본값 설정
        return PostPublicationState.valueOf(s);    }
}
