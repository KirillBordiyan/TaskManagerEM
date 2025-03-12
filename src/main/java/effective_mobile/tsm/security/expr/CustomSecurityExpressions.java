package effective_mobile.tsm.security.expr;

public class CustomSecurityExpressions {
    /*
    task cont:
        - get by id - isA()
        - create comment - executor/principal/admin/sudo
        - get comments - isAuth()
        - update comment - c-owner
        - delete comment - c-owner/admin/sudo

     user cont:
        - get by username - isAuth()
        - get current - owner
        - update - owner/admin/sudo
        - delete - admin/sudo

     user-task controller:
        - create task - только отправитель
        - update full task - owner/admin/sudo
        - update status only - executor
        - get by principal - isA()/admin/sudo
        - get by executor - isA()/admin/sudo
        - delete task - owner/admin/sudo

     auth c:
        - reg - permit
        - login - permit
        - refresh - isA()
        - admin - sudo


        isA()
        e/p/a/s
        owner
        o/a/s
        a/s
        e
        s
        sender

     */


}
