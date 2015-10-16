[![Codacy Badge](https://www.codacy.com/project/badge/ddfe5e51bfdd41a99e8cabb41aed8fa4)](https://www.codacy.com/app/mateuszjaje/utils-mongodb)

# Sbt line

```scala
libraryDependencies += "io.github.morgaroth" %% "utils-mongodb" % "2.0.0"
```

# CHANGELOG


* 2.0.0:

    * updated salat and ficus and config libraries
    * rewritten base class, now it is **MongoDayAnyKey**, it is initialized by **Config** and collecion name,
    next from this one extends classes **MongoDAOObjectIdKey** and **MongoDAO** which specifies key type (**MongoDAO** as default uses
    String key)
    * all daos have **JodaSupport** subclasses which adds joda serializers to context 
    * all daos have **FromResource** subclasses which takes path (in config) and loads this config from resources
    * final list of accessible DAO mutations is:
        * initialized by config object:
            * **MongoDAOAnyKey\[ObjectType, KeyType](config, collectionName)** - basic
            * **MongoDAO\[ObjectType](config, collectionName)** - key type frozen as String
            * **MongoDAOObjectIdKey\[ObjectType](config, collectionName)** - key type frozen as ObjectId
        * initialized by config object with joda support
            * **MongoDAOAnyKeyJodaSupport\[ObjectType, KeyType](config, collectionName)**
            * **MongoDAOJodaSupport\[ObjectType](config, collectionName)**
            * **MongoDAOObjectIdKeyJodaSupport\[ObjectType](config, collectionName)**
        * initialized by path inside resource-config
            * **MongoDAOAnyKeyFromResource\[ObjectType, KeyType](pathInConfig, collectionName)**
            * **MongoDAOFromResource\[ObjectType](pathInConfig, collectionName)**
            * **MongoDAOObjectIdKeyFromResource\[ObjectType](pathInConfig, collectionName)**
        * initialized by path inside resource-config with joda suport
            * **MongoDAOAnyKeyFromResourceJodaSupport\[ObjectType, KeyType](pathInConfig, collectionName)**
            * **MongoDAOFromResourceJodaSupport\[ObjectType](pathInConfig, collectionName)**
            * **MongoDAOObjectIdKeyFromResourceJodaSupport\[ObjectType](pathInConfig, collectionName)**


* 1.2.10:
    * updated Ficus library

* 1.2.6:

    * added second dao class: `MongoDAOStringKey` as class provides dao based con typesafe config object and collection name
    * migration to separate library, name of dependency changes from `morgaroth-utils-mongodb` to `utils-mongodb`

* 1.2.5:

    * no changes

* 1.2.4:

    * more
    * added SalatDAOConf class (extending SalatDAO) which provides reading database access from application configuration
