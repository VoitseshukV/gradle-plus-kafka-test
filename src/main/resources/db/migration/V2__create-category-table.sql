CREATE TABLE categories
(
    id          bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        varchar(255) NOT NULL,
    description varchar(255) NULL,
    is_deleted  bit          NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

alter table categories
    add constraint name
        unique (name);
