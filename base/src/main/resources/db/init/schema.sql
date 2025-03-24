CREATE TABLE terms
(
    terms_id     BIGINT AUTO_INCREMENT NOT NULL,
    terms_title  VARCHAR(200)          NULL,
    terms_content CLOB                 NULL,
    terms_version BIGINT               NULL,
    terms_mandatory VARCHAR(1)         NULL,
    input_id     VARCHAR(36)           NULL,
    input_dt     datetime              NULL,
    modify_id    VARCHAR(36)           NULL,
    modify_dt    datetime              NULL
);