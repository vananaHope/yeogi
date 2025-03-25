CREATE TABLE terms
(
    terms_id     BIGINT AUTO_INCREMENT NOT NULL,
    title        VARCHAR(200)          NOT NULL,
    content      TEXT                  NOT NULL,
    version      INT                   NOT NULL,
    mandatory    VARCHAR(1)            NULL DEFAULT 'N',
    use_yn       VARCHAR(1)            NULL DEFAULT 'N',
    input_dt     datetime              NULL,
    modify_dt    datetime              NULL
);