create schema if not exists tasklist;

create table if not exists tasklist.users
(
    user_id UUID primary key default gen_random_uuid(),
    user_name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null
);

create table if not exists tasklist.task_data
(
    task_id UUID primary key default gen_random_uuid(),
    title varchar(255) not null,
    description TEXT,
    task_status varchar(255) not null,
    task_priority varchar(255) not null,
    created_at timestamp not null,
    task_principal_id UUID,
    task_executor_id UUID,
    FOREIGN KEY (task_principal_id) REFERENCES tasklist.users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (task_executor_id) REFERENCES tasklist.users(user_id) ON DELETE CASCADE
);

create table if not exists tasklist.comment_data
(
    comment_id UUID primary key default gen_random_uuid(),
    comment_content TEXT not null,
    created_at timestamp not null,
    comment_owner_id UUID not null,
    comment_task_id UUID not null,
    FOREIGN KEY (comment_owner_id) REFERENCES tasklist.users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (comment_task_id) REFERENCES tasklist.task_data(task_id) ON DELETE CASCADE
)

create table if not exists tasklist.user_task
(
    user_id_principal UUID not null,
    task_id UUID not null,
    user_id_executor UUID not null,
    PRIMARY KEY (user_id, task_id, user_id_executor),
    FOREIGN KEY (user_id) REFERENCES tasklist.users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES tasklist.task_data(task_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id_executor) REFERENCES tasklist.users(user_id) ON DELETE CASCADE
)

create table if not exists tasklist.task_comment
(
    task_id UUID not null,
    comment_id UUID not null,
    PRIMARY KEY (task_id, comment_id),
    FOREIGN KEY (task_id) REFERENCES tasklist.task_data(task_id) ON DELETE CASCADE,
    FOREIGN KEY (comment_id) REFERENCES tasklist.comment_data(comment_id) ON DELETE CASCADE
)