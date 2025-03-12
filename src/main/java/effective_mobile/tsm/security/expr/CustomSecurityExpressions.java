package effective_mobile.tsm.security.expr;

import effective_mobile.tsm.model.entity.user.Role;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.security.body.JwtEntity;
import effective_mobile.tsm.service.CommentService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("CSE")
@RequiredArgsConstructor
public class CustomSecurityExpressions {

    private final UserService userService;
    private final TaskService taskService;
    private final CommentService commentService;

    public boolean isTaskOwner(UUID taskId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity je = (JwtEntity) auth.getPrincipal();
        User u = userService.getEntityByUsername(je.getUsername());
        return userService.isPrincipal(u.getUserId(), taskId);
    }

    public boolean isCommentOwner(UUID commentId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity je = (JwtEntity) auth.getPrincipal();
        User u = userService.getEntityByUsername(je.getUsername());
        return commentService.isCommentOwner(u.getUserId(), commentId);
    }

    public boolean isAdminOrSudo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(ga ->
                        ga.getAuthority().equals(Role.ADMIN.toString()) ||
                                ga.getAuthority().equals(Role.SUDO.toString()));
    }

    public boolean isSudo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals(Role.SUDO.toString()));
    }

    public boolean isExecutor(UUID taskId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity je = (JwtEntity) auth.getPrincipal();
        User u = userService.getEntityByUsername(je.getUsername());
        return userService.isExecutor(u.getUserId(), taskId);
    }

    public boolean isOwnerOrSenderByUsername(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName().equals(username);
    }

    public boolean isOwnerOrSenderById(UUID userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity je = (JwtEntity) auth.getPrincipal();
        User entityByEmail = userService.getEntityByEmail(je.getEmail());
        return userId.equals(entityByEmail.getUserId());
    }


    /*
    user-task controller:
        -+ create task - только отправитель
        -+ update full task - owner/admin/sudo
        -+ update status only - executor
        -+ get by principal - isA()/admin/sudo
        -+ get by executor - isA()/admin/sudo
        -+ delete task - owner/admin/sudo

    task cont:
        -+ get by id - isA()
        -+ create comment - executor/principal/admin/sudo
        -+ get comments - isAuth()
        -+ update comment - c-owner
        -+ delete comment - c-owner/admin/sudo

     user cont:
        -+ get by username - isAuth()
        -+ get current - owner
        -+ update - owner/admin/sudo
        -+ delete - admin/sudo


     auth c:
        -+ reg - permit
        -+ login - permit
        -+ refresh - isA()
        -+ admin - sudo

+  isA()
      +  e/p/a/s
      +  owner
      +  o/a/s
      +  a/s
      +  e
      +  s
      +  sender


     */


}
