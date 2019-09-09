[![Codacy Badge](https://api.codacy.com/project/badge/Grade/53249f63a34b445dbbe8853bd12b9cfa)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=davidfrd/TBExcerciseA&amp;utm_campaign=Badge_Grade)

# TBExercise

This is an exercise which allows a restaurant to calculate how much they need to pay their 
workers based on the working hours that they have made (shifts). This calculations 
are calculated applying certain rules to these shifts.

## How to run with Docker

### Previous requirements
To execute the application with docker, you should install it previously. 

See how to install on https://docs.docker.com/install/

### Running
To running this application, you should enter in the root folder of this repository 
and run the next command:

```
docker-compose up
```

## Consuming service
After a few minutes, when the application is up, you must be able to consume the services 
in the port `8085` and you will be able to consume get billing endpoint.

ex.
```
localhost:8085/billing
```