package mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TranslationMapper.class})
public abstract class TranslationCodeMapper {
}
