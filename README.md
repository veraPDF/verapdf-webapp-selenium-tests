# verapdf-webapp-selenium-tests

**Prerequisites**

JDK 11, Maven

This is targeted at people without [Maven](https://maven.apache.org/) experience.

To build it, you will need to download and unpack the latest (or recent) version of Maven (https://maven.apache.org/download.cgi)
and put the `mvn` command on your path.

#Build

You can use your local Maven installation to build project sources:
```
mvn clean install
```

Maven will compile your project, an put the results it in a jar file in the `target` directory.

# A couple of Maven commands

* `mvn compile`: it will just compile the code of your application and tell you if there are errors
* `mvn test`: it will compile the code of your application and your tests. It will then run your tests (if you wrote any) and let you know if some fails
* `mvn install`: it will do everything `mvn test` does and then if everything looks file it will install the library or the application into your local maven repository (typically under <USER FOLDER>/.m2). In this way you could use this library from other projects you want to build on the same machine
* `mvn clean install test -DsuiteXmlFile=testng.xml` it will start tests which you want and set in `testng.xml` file