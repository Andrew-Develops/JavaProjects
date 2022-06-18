//Mai jos am facut toate operatiile CRUD folosind frameworkul Hibernate
//Pe entitatea Teacher si Student avem : insert,find,update,delete
//Pe entitatea Group avem : insert,find
//Constrainturile sunt urmatoarele : unique pe:
//Identificator.number,
//Teacher.addressId, Teacher.identificatorId, Student.addressId, Student.identificatorId
//Relatii One-to-One: Teacher.addressId, Teacher.identificatorId, Student.addressId, Student.identificatorId
//Relatii Many-to-One: Students pe Group
//relatii One-to-Many: Group pe Student
//relatii Many-to-Many: Teacher si Student