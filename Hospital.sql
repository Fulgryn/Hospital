CREATE SCHEMA `hospital` ;

CREATE USER 'java'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON hospital.* TO 'java'@'localhost' IDENTIFIED BY 'password';

drop table if exists Ward;
create table Ward(
	id int not null primary key auto_increment,
    ward_name varchar(20) not null,
    room_type varchar(20) not null,
    referral_class varchar(50),
    bed_number int not null,
    bed_free int not null
);

drop table if exists Person;
create table Person(
	id int not null primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    DOB Date not null,
    address varchar(200) not null,
    email varchar(20),
    phone_Nb char(10)
);

drop table if exists Patient;
create table Patient(
    host_id int not null primary key auto_increment,
	PPSN char(8) not null,
	person_id int not null references Person(id),
    next_of_kin varchar(50),
    referral varchar(50),
    referral_class varchar(50),
    ward_id int references Ward(id)
);

drop table if exists User;
create table User(
	id int not null primary key auto_increment,
    person_id int not null references Person(id),
    username varchar(20) not null,
    password varchar(20) not null

);

drop table if exists Allergies;
create table Allergies(
	id int not null primary key auto_increment,
    patient_id int not null references Patient(PPSN),
    allergy_name varchar(20) not null
);

drop table if exists Diagnosis;
create table Diagnosis(
	id int not null primary key auto_increment,
    patient_id int not null references Patient(PPSN),
    details varchar(200) not null,
    date_issued date not null
);

drop table if exists Treatment;
create table Treatment(
	id int not null primary key auto_increment,
    patient_id int not null references Patient(PPSN),
    details varchar(200) not null,
    dosage varchar(100),
    begin_date date not null,
    end_date date not null
);

drop table if exists Admin;
create table Admin(
	id int not null primary key auto_increment,
    user_id int not null references User(id)
);

drop table if exists Staff;
create table Staff(
	id int not null primary key auto_increment,
    user_id int not null references User(id)
);

drop table if exists Doctor;
create table Doctor(
	id int not null primary key auto_increment,
    staff_id int not null references Staff(id),
    speciality varchar(20)
);

drop table if exists Nurse;
create table Nurse(
	id int not null primary key auto_increment,
    staff_id int not null references Staff(id)
);

drop table if exists Bedside_Notes;
create table Bedside_Notes(
	id int not null primary key auto_increment,
    patient_PPSN char(8) not null references Patient(PPSN),
    notes varchar(200) not null,
    notes_date date not null
);

