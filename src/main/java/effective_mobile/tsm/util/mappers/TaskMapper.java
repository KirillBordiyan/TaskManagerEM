package effective_mobile.tsm.util.mappers;

import effective_mobile.tsm.model.dto.input.TaskCreateInput;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.entity.task.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CommentMapper.class})
public interface TaskMapper {
    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "principal", ignore = true)
    @Mapping(target = "executor", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    Task mappingTaskInputToEntity(TaskCreateInput input);

    @Mapping(target = "taskId", source = "taskId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "principal", source = "principal.userId")
    @Mapping(target = "executor", source = "executor.userId")
    TaskResponse mappingTaskEntityToResponse(Task task);
}
