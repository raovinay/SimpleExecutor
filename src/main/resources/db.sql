create schema email;

use email;

create table email (
id int,
email varcahr(100),
processed boolean
);

create user email;

SET PASSWORD FOR 'email' = PASSWORD('email');

grant select, insert, update on email.* to email;

