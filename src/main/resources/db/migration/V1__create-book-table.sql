CREATE TABLE books
(
    id          bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title       varchar(255) NOT NULL,
    author      varchar(255) NOT NULL,
    isbn        varchar(255) NOT NULL,
    price       decimal      NOT NULL,
    description varchar(255) NULL,
    cover_image varchar(255) NULL,
    is_deleted  bit          NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE books
    ADD CONSTRAINT isbn
        UNIQUE (isbn);