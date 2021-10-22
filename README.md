# ArchitectureSample

This project in organized in modules based on the clean-architecture structure : separation between domain / data & view

## Domain
This module contains the models & the use cases of the application, in kotlin only.
In "domain" module packages are organized by entity
  - domain  
    - user
    - activities
    - meal
    - etc...

Each package contains the models, enums & repository related to the package scope.
    1. Declare a new model using kotlin data class with primary constructor
    ```
    data class <ModelName>(val attribute1, val attribute2, ...) 
    ```
    2. Create Repository Interface with model use case. Repository Interface must be post-fixed with "Repository".
    You can populate the repository with use case method later.
    ```
    interface <ModelName>Repository {
        // use case 1 : -> create new Model
        suspend fun create(model: <ModelName>)
        // use case 2 : -> delete Model
        suspend fun delete(model: <ModelName>)
        ...
    }
    ```
    3. You can also declare enum class in the scope using kotlin enum class. Enum value must be UpperCase with _ separator.
    ! The domain enum can called within any app module !
    ```
    enum class ModelType {
        TYPE_1
        TYPE_2
    }
    ```

## Data
In "data" module packages are organized by type
  - repository : The package database contains the domain repository implementation

  - di ( dependence injection )

  - database
    - entity : contain Room Database Entity.
    - dao : contain Room Database DAO 

    1. Declare new Entity using kotlin data class, Post-fix the data class with "Entity"

       Override the table name for replace the interface name with simple word
       ex : @Entity(tableName = "user") replace "UserEntity".

       Add mapping method to convert Data/Entity into Domain/Model and reverse.
       In data class create method : to<Model>.

       Outside of the data class declare an extension of the domain : to<Model>Entity.
       ```
       @Entity(tableName = "models")
       data class <Model>Entity(val attribute1: String, ...) {
          fun to<Model>() = Model(...)
       }

       fun Model.to<Model>Entity() = <Model>Entity(...)
       ```

       Also if your Entity contains attribute with Enum type you need to create a type converter to convert the enum value to Int in database
       ```
       @TypeConverter
       fun <Type>ToInt(type: <Type>): Int = ...
       ```

    2. Declare new Dao (CRUD???) & Add it to the Database
       ```
       @Dao
       interface <ModelName>Dao { }
       ```

    3. Create Repository implementation in data/repository package
    
## App