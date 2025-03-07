package effective_mobile.tsm.util.mappers;

import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.entity.task.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {})
public interface CommentMapper {

    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "commentOwner", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "commentContent", source = "commentContent")
    Comment mappingCommentInputToEntity(CommentCreateInput input);


    @Mapping(target = "commentId", source = "commentId")
    @Mapping(target = "commentOwner", source = "commentOwner.userId")
    @Mapping(target = "task", source = "task.taskId")
    @Mapping(target = "commentContent", source = "commentContent")
    @Mapping(target = "createdAt", source = "createdAt")
    CommentResponse mappingCommentEntityToResponse(Comment comment);
}
