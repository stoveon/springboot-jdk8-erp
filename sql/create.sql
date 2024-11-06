CREATE TABLE IF NOT EXISTS user_info
(
    sid          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login_id     VARCHAR(20)  NOT NULL COMMENT '아이디',
    password     VARCHAR(50)  NOT NULL COMMENT '비밀번호',
    name         VARCHAR(100) NOT NULL COMMENT '이름',
    email        VARCHAR(30) COMMENT '이메일',
    address      VARCHAR(200) COMMENT '주소',
    phone_number VARCHAR(12) COMMENT '전화번호',
    created_at   TIMESTAMP DEFAULT now(),
    updated_at   TIMESTAMP DEFAULT now()

);

CREATE TABLE IF NOT EXISTS admin_info
(
    sid          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login_id     VARCHAR(20)  NOT NULL COMMENT '아이디',
    password     VARCHAR(50)  NOT NULL COMMENT '비밀번호',
    name         VARCHAR(100) NOT NULL COMMENT '이름',
    email        VARCHAR(30) COMMENT '이메일',
    address      VARCHAR(200) COMMENT '주소',
    phone_number VARCHAR(12) COMMENT '전화번호',
    department   VARCHAR(20) COMMENT '부서',
    power        SMALLINT     NOT NULL DEFAULT 0 COMMENT '권한 0 or 1',
    created_at   TIMESTAMP DEFAULT now(),
    updated_at   TIMESTAMP DEFAULT now()
);