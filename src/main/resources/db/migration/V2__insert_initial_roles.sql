INSERT INTO auth_roles (role_name, display_name, is_active, created_at, updated_at, is_deleted)
VALUES
    ('ROLE_ADMIN', 'ADMIN', true, now(), now(), false),
    ('ROLE_EDITOR', 'EDITOR', true, now(), now(), false),
    ('ROLE_AUTHOR', 'AUTHOR', true, now(), now(), false);

INSERT INTO auth_users (full_name, username, password, status, is_active, created_at, updated_at, is_deleted)
VALUES
    ('Abdullayev Bahriddin', 'abahriddin67@gmail.com', '$2a$10$KdQBLKcrUXsB2Z8SWZpSY.SSIW8iyBbaFfZObd2ssewAtrjtjdnJq', 'ACTIVE', true, now(), now(), false);

insert into auth_users_roles (user_id, role_id)
values
    (1,1),
    (1,2),
    (1,3);