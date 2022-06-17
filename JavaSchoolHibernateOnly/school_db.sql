use school_db;

create table if not exists student(
	id int primary key not null auto_increment,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
    year_of_birth varchar(50) not null,
    electives varchar(50) not null,
    address_id int not null unique,
    identificator_id int not null unique,
    classes_id int not null
);

create table if not exists professor(
	id int primary key not null auto_increment,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
    curriculum varchar(50) not null,
    address_id int not null unique,
    identificator_id int not null unique
);

create table if not exists address(
	id int primary key not null auto_increment,
	city varchar(50) not null,
	street varchar(50) not null
);

create table if not exists identificator(
	id int primary key not null auto_increment,
	number varchar(50) not null unique
);

create table if not exists classes(
	id int primary key not null auto_increment,
	name varchar(50) not null unique
);

create table if not exists student_professor(
	id int primary key not null auto_increment,
	student_id int not null,
    professor_id int not null
);

alter table student
add constraint fk_student_address foreign key (address_id) references address(id),
add constraint fk_student_classes foreign key (classes_id) references classes(id),
add constraint fk_student_identificator foreign key (identificator_id) references identificator(id);

alter table professor
add constraint fk_professor_address foreign key (address_id) references address(id),
add constraint fk_professor_identificator foreign key (identificator_id) references identificator(id);

alter table student_professor
add constraint fk_student_professor_student foreign key (student_id) references student(id),
add constraint fk_student_professor_professor foreign key (professor_id) references professor(id);








