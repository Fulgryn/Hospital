drop table if exists Ward;
create table Ward(
	id int not null primary key auto_increment,
    ward_name varchar(20) not null,
    room_type varchar(20) not null,
    referral_class varchar(50),
    bed_number int not null,
    bed_free int not null
);
drop table if exists Patient;
create table Patient(
	id int not null primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    DOB Date not null,
    address varchar(200) not null,
    email varchar(50),
    phone_Nb char(10),
	PPSN char(8) not null,
    next_of_kin varchar(200),
    referral varchar(50),
    referral_class varchar(50),
    ward_id int references Ward(id)
);

drop table if exists Allergies;
create table Allergies(
	id int not null primary key auto_increment,
    patient_id int not null references Patient(id),
    allergy_name varchar(20) not null
);

drop table if exists Diagnosis;
create table Diagnosis(
	id int not null primary key auto_increment,
    patient_id int not null references Patient(id),
    details varchar(200) not null,
    date_issued date not null
);



