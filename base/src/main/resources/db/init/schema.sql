CREATE TABLE terms
(
    title           VARCHAR(200)          NOT NULL,
    version         VARCHAR2(20)          NOT NULL,
    content         TEXT                  NOT NULL,
    lock_version    INT                   NOT NULL,
    mandatory       BOOLEAN               NULL DEFAULT FALSE,
    use_yn          BOOLEAN               NULL DEFAULT FALSE,
    input_dt        datetime              NULL,
    modify_dt       datetime              NULL
);