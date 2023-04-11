CREATE TABLE IF NOT EXISTS users (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '회원 식별자',
    user_id VARCHAR(15) NOT NULL COMMENT '회원 아이디',
    user_password VARCHAR(100) NOT NULL COMMENT '회원 비밀번호',
    user_name VARCHAR(15) NOT NULL COMMENT '회원 아이디',
    create_date DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '생성일',
    update_date DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '수정일'
);
COMMENT ON TABLE users is '회원 테이블';


-- CREATE TABLE IF NOT EXISTS user_roles (
--     user_roles_id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '회원 권한 식별자',
--     id VARCHAR(15) NOT NULL COMMENT '회원 아이디',
--     auth VARCHAR(15) NOT NULL DEFAULT 'USER' COMMENT '회원 권한'
-- );
-- COMMENT ON TABLE users is '회원 권한 테이블';