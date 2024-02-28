```

  __  __ _                         ___                              
 |  \/  (_)_ _  ___ _ ___ ____ _  | __|  _ _ _ ___ _ __  __ _ ______
 | |\/| | | ' \/ -_) '_\ V / _` | | _| || | '_/ _ \ '_ \/ _` (_-<_-<
 |_|  |_|_|_||_\___|_|  \_/\__,_| |___\_,_|_| \___/ .__/\__,_/__/__/
                                                  |_|               
                                                            SERVER
```

# Minerva Europass (WIP)

Minerva Europass (alias IT Europass) allows, as you can understand, to generate your own Resume. Currently, there is only
one resume template. Here is a [demo.pdf](demo.pdf) file.

## Technologies

- Java
- Spring Boot
- Apache FOP
- Hibernate
- Liquibase
- Postgresql
- Docker

## Features

- resume sections:
  - profile (picture, emails, phone numbers, languages, location etc.)
  - introduction
  - job experience list
  - education list
  - skills matrix
  - personal projects list
  - certificates list
- calculate years of experience for every job/personal project
- calculate total years of experience
- calculate years of experience for every single technology
- calculate years of experience as front-end/back-end developer
- calculate technology frequency
- multi-language: english and italian
- configurable

## Configure and run the application

- copy the `application.yml` and create `application-dev.yml`
- set `spring.profiles.active=dev` as Environment Variable of your IDE
- edit the the `application-dev.yml` and set the `image-path` property to an existing path, like the Desktop path
- run the project as Spring Boot project

---

- open Postman ad import the collection file `Minerva Europass.postman_collection.json`
- edit the json by inserting your information
- after clicking on the `Send` button, the application will generate the PDF
- click on `View more actions` (3 dots) and save the response as PDF file
