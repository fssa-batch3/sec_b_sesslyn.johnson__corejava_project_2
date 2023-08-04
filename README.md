# Minimal Application Checklist
## Database Design
- [ ]ER diagram of the database![\[](https://iili.io/HtJyKsn.png) 


- [ ] Table scripts : [script](/src/main/resources/db/migration/Queries.sql)

## Project Setup

- [ ] Create a new Java project
- [ ] Set up a MySQL database
- [ ] Add necessary libraries
	- [ ] JDBC, 
	- [ ] MySQL Connector, 
	- [ ] JUnit, 
	- [ ] Dotenv

## Module: User 

- [ ]  users table

### Feature: Create User

>User can create a new account.

#### Pre-requisites:
- [ ]  user model
- [ ]   user DAO ( create )
- [ ]  user service ( create )

#### Validations:

 - [ ]   Form Validation
   * user null
   * name ( null, empty, pattern )
   * email ( null, empty, pattern )
   * password ( null, empty, pattern )
   * phone number ( length, >= 600000001 && <= 9999999999 )
     
  - [ ]   Business Validation
         * Email Already exists 

#### Messages:

   * User object can not be null.
   * Name can not be null or empty.
   * Email can not be null or empty.
   * Email does not match the pattern.
   * Password can not be null or empty.
   * Password does not match the pattern.
   * Phone number can not be less than or more than 10 digits.
   * Phone number does not match the pattern.
   * User already exists  
   
 #### Flow: 
```mermaid
graph TD;
  A(Value Passed to UserService - User Object) --> B(Form Validation) -- Valid --> C(Business Validation)
  B --Invalid --> G(Validation Exception)
  C-- Valid --> D(Value Passed to UserDAO)
  D --> E(Store Value in Database)
  C -- Invalid --> F(Validation Exception)
```

### Feature: User Details

>We can get all the current user details.

#### Pre-requisites:
- [ ]  user interface ( findByEmail)
- [ ]  user DAO ( findAll, findById, findByEmail)
- [ ]  user service ( findAll, findById, findByEmail)

#### Validations:
 - [ ]   Form Validation
  * Id <= 0
  * Email ( null , email , pattern )

                
  - [ ]   Business Validation
  * Check whether the Id exists
  * Check whether the email exists

              
#### Messages:
 * Id can not be less than zero.
 * Email can not be null or empty.
 * Email does not match the pattern.
 * Password can not be null or empty.
 * Password does not match the pattern.
 * Id doesn't exists. 
 * Email doesn't exists. 

           
  #### Flow: 

```mermaid
graph TD;
  A(Value Passed  to  UserService - Id / email ) -->B(Form Validation) -- Valid --> C(Business Validation)
  B --Invalid --> H(Validation Exception)
  C -- Valid--> D(Value Passed  to  UserDAO)
  D --> E(Retrieve  result  from  Database)
  C -- Invalid --> F(Validation Exception)
```

### Feature: Update User

>Existing user can update their details.

#### Pre-requisites:
 - [ ]  user dao ( update )

 - [ ]  user service ( update )
 
 #### Validations:
  - [ ]   Form Validation
     * Id <= 0
     * User Object
          *  user null
          * name ( null, empty, pattern )
          * password ( null, empty, pattern )
          * phone number ( length, >= 600000001 && <= 9999999999 )

  - [ ]   Business Validation

       * Check whether the Id exists
       
#### Messages:

* Id can not be less than zero.
* User object can not be null
* Name can not be null or empty.
* Password can not be null or empty.
* Password does not match the pattern.
* Phone number can not be less than or more than 10 digits.
* Phone number does not match the pattern.
* Id doesn't exists
         
 #### Flow: 

```mermaid
graph TD;
  A(Value Passed  to  UserService - Id & User object) --> B(Form Validation) -- Valid --> C(Business Validation)
  B --Invalid --> H(Validation Exception)
  C -- Valid--> D(Value Passed  to  UserDAO)
  D --> E(Update value from  Database)
  C -- Invalid --> F(Validation Exception)
```

 ### Feature: Delete User
 
 >User can log out from their account.
 
#### Pre-requisites:
 - [ ]  user dao ( delete  )

 - [ ]  user service ( delete )
 
#### Validations:
 - [ ]   Form Validation

     * Id <= 0

 - [ ]   Business Validation

      * Check whether the Id exists

#### Messages:
* Id can not be less than xero
* Id doesn't exists
      
 #### Flow: 

```mermaid
graph TD;
  A(Value Passed  to  UserService - Id) -->B(Form Validation) -- Valid --> C(Business Validation)
  B --Invalid --> G(Validation Exception)
  C -- Valid --> D(Value  Passed  to  UserDAO to Update active status)
  D --> E(Update status in Database)
  C -- Invalid --> F(Validation Exception)
```

## Module: Appointment ( Consumer )  

### Feature: List all designer profiles  

>User can see all the designer profiles.

#### Pre-requisites:

- [ ] user dao ( findAllDesigner )  
- [ ] user service ( findAllDesigner )
      
 #### Flow:  

```mermaid  
graph TD;  
A(UserService) --> B(UserDAO to reterive data)  
B --> C(Get all designer profiles from Database)  
```
       
### Feature: Designer profile details  

> User can see the profile details of a particular designer.

#### Pre-requisites:

- [ ] user dao ( findDesignerById )
- [ ]  user service ( findDesignerById  )
      
#### Validations:  
  - [ ]   Form Validation
                 
       * Id <= 0
   
   - [ ]   Business Validation
   
        * Check whether the Id exists
         
#### Messages:
* Id can not be less than zero
* Id doesn't exists     
         
 #### Flow:  

```mermaid  
graph TD;  
A(Value Passed to UserService - Id) --> B(Form Validation) -- Valid --> C(Business Validation)
  B --Invalid --> G(Validation Exception)
C -- Valid --> D(Value Passed to UserDAO)  
D --> E(Return user)  
C -- Invalid --> F(Validation Exception)  
```
## Module: Appointment 
### Table

- [ ]  appointment table

### Feature: Book Now

> User can book appointment with a designer.

#### Pre-requisites:
- [ ]  appointment model
- [ ]  appointment dao (create )
- [ ]  appointment service ( create )

#### Validations:
 - [ ]   Form Validation
 
      * appointment object null
      * from_user ( id <= 0 ) (Exists check in user table )
      *  to_user ( id <= 0 ) (Exists check in user table )
      * email ( null, empty, pattern )
      * phone number ( length, >= 600000001 && <= 9999999999 )
       * status (waiting or approved or rejected)
       *  Date cannot be null
       * Date range cannot be past days , cannot be in future more than 3 months              and cannot be today 
       *  Time cannot be null 
       * Time range cannot be before 8 am , cannot be after 8 pm
          
                     
  - [ ]   Business Validation
       * Check whether the from_user exists in user table
       * Check whether the from_user has any appointment in upcoming days
       * Check whether the to_user exists and is_designer in user table
       * Check whether the to_user has an appointment in the same date and time

#### Messages:
 * Appointment object can not be null
* User id can not be less than zero
* Designer id can not be less than zero
* Email can not be null or empty.
* Email does not match the pattern. 
* Phone number can not be less than or more than 10 digits.
* Phone number does not match the pattern.
* Status can not be null or empty
* Status does not match the pattern.
* Date can not be null
* Date can not be past or can not be in future more than 3 months.
* Date should be in this format ( yyyy - MM - dd )
* Time can not be null
* Time should be in the range of 8am between 8pm.
* User doesn't exists
* The appointment you have is yet to be completed. Please be patient.
* Designer doesn't exists
* The designer has an appointment scheduled at that specific time. Please reschedule the appointment for a different time slot.

 #### Flow: 

```mermaid
graph TD;
  A(Value Passed to appointmentService - Appointment Object) --> B(Form Validation) -- Valid --> C(Business Validation)
  B --Invalid --> G(Validation Exception)
  C -- Valid --> D(Value Passed to appointmentDAO)
  D --> E(Store Value in Database)
  C -- Invalid --> F(Validation Exception)
```

## Module: Appointment ( Designer )

### Feature: List all Designer Appointment request

>Designer can see the appointment request.

#### Pre-requisites:
- [ ] First, complete book appointment feature 
- [ ]  appointment dao ( findAllAppointment , findAllAppointmentRequest )
- [ ]  appointment service ( findAllAppointment, findAllAppointmentRequest )

#### Validations:
  - [ ]   Form Validation
       * status ( null, empty)
       * status ( waiting list // rejected // approved )
         
#### Messages:
* Status can not be null or empty
* Status does not match the pattern.
                  
 #### Flow: 

```mermaid
graph TD;
  A(Value Passed  to  appointmentService - Status) --> B(Form Validation)
  B--> C{Validation  Result}
  C -- Valid --> D(Value  Passed  to  appointmentDAO)
  D --> E(Retrieve value from Database)
  C -- Invalid --> F(Validation Exception)
```

### Feature: Update request status

>Designer can update the appointment request.

#### Pre-requisites:

- [ ]  appointment dao ( updateRequestStatus )

- [ ]  appointment service ( updateRequestStatus )
             
#### Validations:
- [ ]  appointment validator
   * id <= 0
    * appointment null
    * status ( null, empty)
    * status ( waiting list // rejected // approved )    
           
 - [ ]   Business Validation

      * Check whether the to_user exists and is_designer  in user table
      * Check whether the to_user has an appointment 
 
 #### Messages:
* Designer id can not be less than zero
* Status can not be null or empty
* Status does not match the pattern.
* Designer doesn't exists
* The designer has an appointment scheduled at that specific time. 
         
 #### Flow: 

```mermaid
graph TD;
  A(Value Passed  to  appointmentService - Id, Status) --> B(Form Validation) -- Valid --> C(Business Validation)
  B --Invalid --> G(Validation Exception)
  C -- Valid --> D(Value  Passed  to  appointmentDAO)
  D --> E(Update value in Database)
  C -- Invalid --> F(Validation Exception)
```
