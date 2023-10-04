Add New User:
http://localhost:8080/auth/addNewUser
{
"name" : "Sandun",
"password" : "Sandun@123",
"email" : "sadunj@gmail.com",
"roles" : "ROLE_USER"
}
---------------------------------------------------------------
Generate Token
http://localhost:8080/auth/generateToken
{
"username" : "Sandun",
"password" : "Sandun@123"
}
