[![Codacy Badge](https://www.codacy.com/project/badge/ddfe5e51bfdd41a99e8cabb41aed8fa4)](https://www.codacy.com/app/mateuszjaje/utils-mongodb)

# Sbt line

```scala
libraryDependencies += "io.github.morgaroth" %% "utils-mongodb" % "1.2.9"
```

# CHANGELOG

* v1.2.6:

    * added second dao class: `MongoDAOStringKey` as class provides dao based con typesafe config object and collection name

* migration to separate library, name of dependency changes from `morgaroth-utils-mongodb` to `utils-mongodb`

* v1.2.5:

    * no changes

* v1.2.4:

    * more
    * added SalatDAOConf class (extending SalatDAO) which provides reading database access from application configuration
