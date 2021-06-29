# API
- [File](#file)
  * [Create database](#create-database)
    + [URL](#url)
    + [Parameters](#parameters)
  * [Open database](#open-database)
    + [URL](#url-1)
    + [Parameters](#parameters-1)
  * [Delete database](#delete-database)
    + [URL](#url-2)
    + [Parameters](#parameters-2)
  * [Rename database](#rename-database)
    + [URL](#url-3)
    + [Parameters](#parameters-3)
  * [Import GEDCOM file](#import-gedcom-file)
    + [URL](#url-4)
    + [Parameters](#parameters-4)
- [Family](#family)
  * [Family Tree](#family-tree)
    + [URL](#url-5)
    + [Parameters](#parameters-5)
  * [Get whole family](#get-whole-family)
    + [URL](#url-6)
    + [Parameters](#parameters-6)
- [Person](#person)
  * [List persons](#list-persons)
    + [URL](#url-7)
    + [Parameters](#parameters-7)
  * [Add person](#add-person)
    + [URL](#url-8)
    + [Parameters](#parameters-8)
  * [Add spouse](#add-spouse)
    + [URL](#url-9)
    + [Parameters](#parameters-9)
  * [Add father](#add-father)
    + [URL](#url-10)
    + [Parameters](#parameters-10)
  * [Add mother](#add-mother)
    + [URL](#url-11)
    + [Parameters](#parameters-11)
  * [Delete person](#delete-person)
    + [URL](#url-12)
    + [Parameters](#parameters-12)
  * [Update person](#update-person)
    + [URL](#url-13)
    + [Parameters](#parameters-13)
  * [Update individual’s father](#update-individual-s-father)
    + [URL](#url-14)
    + [Parameters](#parameters-14)
  * [Update individual’s mother](#update-individual-s-mother)
    + [URL](#url-15)
    + [Parameters](#parameters-15)
  * [Update individual’s spouse](#update-individual-s-spouse)
    + [URL](#url-16)
    + [Parameters](#parameters-16)
  * [Search person by full name](#search-person-by-full-name)
    + [URL](#url-17)
    + [Parameters](#parameters-17)
  * [Search person by id](#search-person-by-id)
    + [URL](#url-18)
    + [Parameters](#parameters-18)
  * [Get all relevant people](#get-all-relevant-people)
    + [URL](#url-19)
    + [Parameters](#parameters-19)
## File
### Create database
Create a new sqlite database file.
#### URL
POST /file/database/create
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| database_path |String|query|required|The path of the database file|
* Example Request
```shell
http://3.9.172.108:8090/api/file/database/create?database_path=%2Fhome%2Fec2-user%2FFamilyFacts%2Fsqlite%2Ftest.db
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```
### Open database
Open a database file.
#### URL
POST /file/database/open
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| database_path |String|query|required|The path of the database file|
* Example Request
```shell
http://3.9.172.108:8090/api/file/database/open?database_path=%2Fhome%2Fec2-user%2FFamilyFacts%2Fsqlite%2Finit.db
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Delete database
Delete a database file.
#### URL
POST /file/database/delete
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| database_path |String|query|required|The path of the database file|
* Example Request
```shell
http://3.9.172.108:8090/api/file/database/delete?database_path=%2Fhome%2Fec2-user%2FFamilyFacts%2Fsqlite%2Ftest.db
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Rename database
Rename a database file.
#### URL
POST /file/database/rename
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| old_name | String | query |required| The old name of the database |
| new_name | String | query |required| The new name of the database |
| database_path |String|query|required|The path of the database file|
* Example Request
```shell
http://3.9.172.108:8090/api/file/database/rename?old_name=test&new_name=demo&database_path=%2Fhome%2Fec2-user%2FFamilyFacts%2Fsqlite%2F
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Import GEDCOM file
Import a GEDCOM file and parse it into this database file.
#### URL
POST /file/gedcom/import
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| file_path | String | query|required|The path of the gedcom file|
* Example Request
```shell
http://localhost:8090/api/file/gedcom/import?gedcom_path=%2Fhome%2Fec2-user%2FFamilyFacts%2Fsqlite%2Fsample.ged
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

## Family
### Family Tree
Return one person's family tree.
#### URL
GET /api/family/tree/{person_id}
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
```shell
http://3.9.172.108:8090/api/family/tree/7
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": {
        "personId": 7,
        "personVO": {
            "personId": 7,
            "firstName": "Wilhelm Friederich",
            "lastName": "Henning",
            "sex": "female",
            "birth": 1853,
            "death": 1915,
            "address": " "
        },
        "father": {
            "personId": 6,
            "personVO": {
                "personId": 6,
                "firstName": "Johann Carl Ludewig",
                "lastName": "Henning",
                "sex": "female",
                "birth": 1805,
                "death": 1874,
                "address": " "
            },
            "father": null,
            "mother": null
        },
        "mother": {
            "personId": 11,
            "personVO": {
                "personId": 11,
                "firstName": "Living",
                "lastName": "",
                "sex": "male",
                "birth": 0,
                "death": 0,
                "address": " "
            },
            "father": null,
            "mother": null
        }
    }
}
```
### Get whole family
Get the whole family by person's id.
#### URL
GET /api/family/{person_id}
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
```shell
http://3.9.172.108:8090/api/family/135
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": {
    "father": {
      "personId": 137,
      "personVO": {
        "personId": 137,
        "firstName": "test",
        "lastName": "father",
        "sex": "male",
        "birth": 1890,
        "death": 1970,
        "address": "Newcastle",
        "spouse": null
      },
      "father": null,
      "mother": null
    },
    "mother": {
      "personId": 138,
      "personVO": {
        "personId": 138,
        "firstName": "test",
        "lastName": "mother",
        "sex": "female",
        "birth": 1890,
        "death": 1970,
        "address": "Newcastle",
        "spouse": null
      },
      "father": null,
      "mother": null
    },
    "person": {
      "personId": 135,
      "firstName": "test",
      "lastName": "person",
      "sex": "male",
      "birth": 1920,
      "death": 2010,
      "address": "Newcastle",
      "spouse": {
        "personId": 136,
        "firstName": "test2",
        "lastName": "spouse",
        "sex": "female",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": null
      }
    },
    "children": [
      {
        "personId": 135,
        "firstName": "test",
        "lastName": "person",
        "sex": "male",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": {
          "personId": 136,
          "firstName": "test2",
          "lastName": "spouse",
          "sex": "female",
          "birth": 1920,
          "death": 2010,
          "address": "Newcastle",
          "spouse": null
        }
      },
      {
        "personId": 139,
        "firstName": "test",
        "lastName": "brother1",
        "sex": "male",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": null
      },
      {
        "personId": 140,
        "firstName": "test",
        "lastName": "brother2",
        "sex": "male",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": null
      }
    ]
  }
}
```
## Person
### List persons
Return all persons in the database
#### URL
GET /api/person/list
#### Parameters
**None**
* Example Request
```shell
http://3.9.172.108:8090/api/person/list
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": [
    {
      "personId": 1,
      "firstName": "Jacob",
      "lastName": "Henning",
      "sex": "female",
      "birth": 1633,
      "death": 1704,
      "address": " "
    },
    {
      "personId": 2,
      "firstName": "Jacob",
      "lastName": "Henning",
      "sex": "female",
      "birth": 1678,
      "death": 1770,
      "address": " "
    }
  ]
}
```
### Add person
Add a new person.
#### URL
POST /api/person/create
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| first_name | String |body |required| The first name of the person |
| last_name | String |body|required|The last name of the person|
| sex | String |body|required|The sex of the person|
| birth | Integer |body|required|Birth of the person|
| death | Integer |body|required|Death the person|
| address | String |body|required|The address of the person|
* Example Request
```shell
http://3.9.172.108:8090/api/person/create?first_name=test1&last_name=test1&sex=1&birth=1920&death=2000&address=Newcastle
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Add spouse
Add the person's spouse.
#### URL
POST /api/person/create/spouse
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
| first_name | String |body |required| The first name of the spouse |
| last_name | String |body|required|The last name of the spouse|
| sex | String |body|required|The sex of the spouse|
| birth | Integer |body|required|Birth of the spouse|
| death | Integer |body|required|Death the spouse|
| address | String |body|required|The address of the spouse|
* Example Request
```shell
http://3.9.172.108:8090/api/person/create/spouse?person_id=135&first_name=test&last_name=spouse&sex=1&birth=1920&death=2010&address=Newcastle
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Add father
Add the person's father.
#### URL
POST /api/person/create/father
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
| first_name | String |body |required| The first name of the father |
| last_name | String |body|required|The last name of the father|
| sex | String |body|required|The sex of the father|
| birth | Integer |body|required|Birth of the father|
| death | Integer |body|required|Death the father|
| address | String |body|required|The address of the father|
* Example Request
```shell
http://3.9.172.108:8090/api/person/create/father?person_id=135&first_name=test&last_name=father&sex=0&birth=1890&death=1970&address=Newcastle
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Add mother
Add the person's mother.
#### URL
POST /api/person/create/mother
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
| first_name | String |body |required| The first name of the mother |
| last_name | String |body|required|The last name of the mother|
| sex | String |body|required|The sex of the mother|
| birth | Integer |body|required|Birth of the mother|
| death | Integer |body|required|Death the mother|
| address | String |body|required|The address of the mother|
* Example Request
```shell
http://3.9.172.108:8090/api/person/create/mother?person_id=135&first_name=test&last_name=mother&sex=1&birth=1890&death=1970&address=Newcastle
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Delete person
Delele a person.
#### URL
POST /api/person/delete/{person_id}
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
* Example Request
```shell
http://3.9.172.108:8090/api/person/delete/135
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Update person
Update a person.
#### URL
POST /api/person/update/{person_id}
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |query |required| The id of the person |
| person | JSON |body|required|The information of the person|
* Example Request
```shell
http://3.9.172.108:8090/api/person/update/136
```
Request Body:
```json
{
    "personId": 136,
    "firstName": "test",
    "lastName": "person",
    "sex": "female",
    "birth": 1921,
    "death": 2010,
    "address": "Newcastle",
    "spouse": null
}
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Update individual’s father
update individul’s father.
#### URL
POST /api/person/update/father
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |query |required| The id of the person |
| father_id | Integer |query|required|The father of the person|
* Example Request
```shell
http://3.9.172.108:8090/api/person/father?person_id=135&father_id=137
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Update individual’s mother
update individul’s mother.
#### URL
POST /api/person/update/father
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |query |required| The id of the person |
| mother_id | Integer |query|required|The mother of the person|
* Example Request
```shell
http://3.9.172.108:8090/api/person/mother?person_id=135&mother_id=138
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Update individual’s spouse
update individul’s spouse.
#### URL
POST /api/person/update/spouse
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |query |required| The id of the person |
| spouse_id | Integer |query|required|The spouse of the person|
* Example Request
```shell
http://3.9.172.108:8090/api/person/spouse?person_id=135&spouse_id=136
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": null
}
```

### Search person by full name
Find a person by first name and last name.
#### URL
GET /api/person/search
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| first_name | String |query |optional| The first name of the person |
| last_name | String |query|optional|The last name of the person|
* Example Request
```shell
http://3.9.172.108:8090/api/person/search?first_name=Wilhelm%20Friederich&last_name=Henning
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": {
      "personId": 7,
      "firstName": "Wilhelm Friederich",
      "lastName": "Henning",
      "sex": "female",
      "birth": 1853,
      "death": 1915,
      "address": " ",
      "spouse": null
    }
}
```
### Search person by id
Find a person by person id.
#### URL
GET /api/person/search/{person_id}
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
* Example Request
```shell
http://3.9.172.108:8090/api/person/search/7
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": {
      "personId": 7,
      "firstName": "Wilhelm Friederich",
      "lastName": "Henning",
      "sex": "female",
      "birth": 1853,
      "death": 1915,
      "address": " ",
      "spouse": null
    }
}
```
### Get all relevant people
Get all relevant persons by id.
#### URL
GET /api/person/relationship/{person_id}
#### Parameters
|  Name   | Type |  In  | Required | Description |
|  ----   | ---- | ---- |   ----   |    ----     |
| person_id | Integer |path |required| The id of the person |
* Example Request
```shell
http://3.9.172.108:8090/api/person/relationship/135
```
* Example Response
```json
{
  "success": true,
  "code": 200,
  "msg": "Success",
  "data": {
    "person": {
      "personId": 135,
      "firstName": "test",
      "lastName": "person",
      "sex": "male",
      "birth": 1920,
      "death": 2010,
      "address": "Newcastle",
      "spouse": {
        "personId": 136,
        "firstName": "test2",
        "lastName": "spouse",
        "sex": "female",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": null
      }
    },
    "father": {
      "personId": 137,
      "firstName": "test",
      "lastName": "father",
      "sex": "male",
      "birth": 1890,
      "death": 1970,
      "address": "Newcastle",
      "spouse": null
    },
    "mother": {
      "personId": 138,
      "firstName": "test",
      "lastName": "mother",
      "sex": "female",
      "birth": 1890,
      "death": 1970,
      "address": "Newcastle",
      "spouse": null
    },
    "spouse": {
      "personId": 136,
      "firstName": "test2",
      "lastName": "spouse",
      "sex": "female",
      "birth": 1920,
      "death": 2010,
      "address": "Newcastle",
      "spouse": {
        "personId": 135,
        "firstName": "test",
        "lastName": "person",
        "sex": "male",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": null
      }
    },
    "brothersAndSisters": [
      {
        "personId": 135,
        "firstName": "test",
        "lastName": "person",
        "sex": "male",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": {
          "personId": 136,
          "firstName": "test2",
          "lastName": "spouse",
          "sex": "female",
          "birth": 1920,
          "death": 2010,
          "address": "Newcastle",
          "spouse": null
        }
      },
      {
        "personId": 139,
        "firstName": "test",
        "lastName": "brother1",
        "sex": "male",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": null
      },
      {
        "personId": 140,
        "firstName": "test",
        "lastName": "brother2",
        "sex": "male",
        "birth": 1920,
        "death": 2010,
        "address": "Newcastle",
        "spouse": null
      }
    ],
    "children": []
  }
}
```
