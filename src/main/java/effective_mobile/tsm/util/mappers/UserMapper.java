package effective_mobile.tsm.util.mappers;


import effective_mobile.tsm.model.dto.input.UserCreateInput;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TaskMapper.class})
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "principalOf", ignore = true)
    @Mapping(target = "executorOf", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User mappingUserInputToEntity(UserCreateInput input);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "principalOf", source = "principalOf")
    @Mapping(target = "executorOf", source = "executorOf")
    UserResponse mappingUserEntityToResponse(User user);
}
