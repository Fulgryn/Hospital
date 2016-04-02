drop table if exists Ward;
create table Ward(
	id int not null primary key auto_increment,
    ward_name varchar(20) not null,
    room_type varchar(20) not null,
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
    email varchar(20),
    phone_Nb char(10),
	PPSN char(8) not null,
    next_of_kin varchar(50),
    allergy varchar(200),
    history varchar(200),
    referral varchar(50),
    referral_class varchar(50),
    ward_id int references Ward(id),
    blood_type varchar(3),
    blood_pressure varchar(10),
    expected_discharge date
);

drop table if exists Staff;
create table Staff(
	id int not null primary key auto_increment,
    title varchar(30),
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    gender varchar(10),
    address varchar(200) not null,
    phone_Nb char(10),
    speciality varchar(20)
);

drop table if exists Diagnosis;
create table Diagnosis(
	id int not null primary key auto_increment,
    patient_id int not null references Patient(id),
    patient_analysis varchar(200),
    symptoms varchar(200),
    diagnosis varchar(200),
    date_issued date
);

drop table if exists Treatment;
create table Treatment(
	id int not null primary key auto_increment,
    patient_id int not null references Patient(id),
	treatment_type varchar(30),
    dosage varchar(30),
    treatment_name varchar(30),
    treatment_length varchar(30),
    effects varchar(100),
    begin_date date,
    end_date date,
    notes varchar(200)
);

drop table if exists Bedside_Notes;
create table Bedside_Notes(
	id int not null primary key auto_increment,
    patient_id char(8) not null references Patient(id),
    comments varchar(200),
    investigations varchar(200),
    status varchar(100),
    notes_date date
);


insert into Patient(
    first_name,
    last_name,
    DOB,
    address,
    email,
    phone_Nb,
	PPSN,
    next_of_kin,
    allergy,
    history,
    referral,
    referral_class,
    ward_id)
    values('John','Test','1959-11-11','42 not here street','johntest@gmail.com','0102030405','1234567A','unknown',null,null,'???','I honestly do not know what is it sorry',1);

insert into ward(
    ward_name,
    room_type,
    bed_number,
    bed_free)values('cardiology','test','20','20');
    
update patient set ward_id = null where id=1;