```

  __  __ _                         ___                              
 |  \/  (_)_ _  ___ _ ___ ____ _  | __|  _ _ _ ___ _ __  __ _ ______
 | |\/| | | ' \/ -_) '_\ V / _` | | _| || | '_/ _ \ '_ \/ _` (_-<_-<
 |_|  |_|_|_||_\___|_|  \_/\__,_| |___\_,_|_| \___/ .__/\__,_/__/__/
                                                  |_|               
                                                            SERVER
```

# Minerva Europass Server

Minerva Europass (alias IT Europass) allows, as you can understand, to generate your own Resume. The particularity of
Minerva Europass is the automatic time calculation for each experience and the summary of technologies which you used.
Currently, there is only one resume template. This is the back-end side. Here is a [demo.pdf](demo.pdf) file. See also
the [client side application](https://github.com/goto-eof/minerva-europass-client).

## Technologies

Java • Spring Boot • Apache FOP • Hibernate • Liquibase • PostgreSQL • Docker

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
- auto-save resume on disk
- configurable

## Configure and run the application

- configure the project in order to run it with JDK 21
- copy the `application.yml` and create `application-dev.yml`
- set `spring.profiles.active=dev` as Environment Variable of your IDE
- edit the `application-dev.yml` and set the `image-path` property to an existing path, like the Desktop path
- run the project as Spring Boot project
- edit also the `pdf-path` in order to enable the resume file storage on disk

---

- open Postman ad import the collection file `Minerva Europass.postman_collection.json`
- edit the json by inserting your information
- after clicking on the `Send` button, the application will generate the PDF
- click on `View more actions` (3 dots) and save the response as PDF file

---
Tested on Linux and MacOS.
<img src="https://andre-i.eu:8080/api/v1/ipResource/custom.png?host=https://github.com/goto-eof/minerva-europass-server" onerror="this.parentNode.removeChild(this)" />
