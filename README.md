# Spring Boot vs Micronaut Benchmark

## HTTP Test

```
$ cd httpservicesb
$ ./mvnw clean package
$ cd ..

$ cd httpservicemn
$ ./gradlew clean build
$ cd ..
```

### Execute support containers
```
$ mkdir grafana-storage
$ docker-compose up -d
```

### Measuring startup time
```
$ java -Xmx128m -jar httpservicesb/target/httpservicesb-0.0.1-SNAPSHOT.jar
ctrl + c

$ java -Xmx128m -jar httpservicemn/build/libs/httpservicemn-0.1-all.jar
ctrl + c
```

### First Response Time
```
$ node time.js httpservicesb/target/httpservicesb-0.0.1-SNAPSHOT.jar
$ node time.js httpservicemn/build/libs/httpservicemn-0.1-all.jar
```

### Performance Under Load
Run
```
$ java -Xmx128m -jar httpservicesb/target/httpservicesb-0.0.1-SNAPSHOT.jar
```

or

```
$ java -Xmx128m -jar httpservicemn/build/libs/httpservicemn-0.1-all.jar
```

In a new terminal do the following:

```
$ cd loadtests
$ ./gradlew gatlingRun --rerun-tasks
```

To check memory usage after load tests do the following:
```
$ ps x -o rss,vsz,command | grep java
```

## Webhook Test

Build all applications.

```
$ cd slow-service
$ ./mvnw clean package
$ cd ..

$ cd kafka-loader
$ ./gradlew clean build
$ cd ..

$ cd messagingservicemn
$ ./gradlew clean build
$ cd ..

$ cd messagingservicesb
$ ./mvnw clean package
$ cd ..
```

Access `https://webhook.site` and copy your unique URL to clipboard. For example: `https://webhook.site/e01ec75a-9997-4208-ad33-f478faac83be`

Edit file `db-dump/schema.sql` on line 21. Change the insert statement to use your URL.

#### Restart docker-compose

```
$ docker-compose down
$ docker-compose up -d
```

### Execute slow-servuce

In a new terminal execute:
```
$ java -Xmx128m -jar slow-service/target/slow-service-0.0.1-SNAPSHOT.jar
```

### Load data into Kafka
```
$ java -jar kafka-loader/build/libs/kafka-loader-0.1-all.jar
```

### Run application
Execute one of the two services as following.

```
java -Xmx128m -jar messagingservicesb/target/messagingservicesb-0.0.1-SNAPSHOT.jar
```

or

```
java -Xmx128m -jar messagingservicemn/build/libs/messagingservicemn-0.1-all.jar
```
