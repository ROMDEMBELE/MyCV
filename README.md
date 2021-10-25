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
        
   ```kotlin
    data class Model(val attribute1, val attribute2, ...)
   ```
    
   2. Create Repository Interface with model use case. Repository Interface must be post-fixed with "Repository".
    You can populate the repository with use case method later.
    
   ```kotlin
    interface ModelRepository {
        // use case 1 : -> create new Model
        suspend fun create(model: Model)
        // use case 2 : -> delete Model
        suspend fun delete(model: Model)
        ...
    }
   ```

   3. You can also declare enum class in the scope using kotlin enum class. Enum value must be UpperCase with _ separator.
   The domain enum can called within any app module.
    
   ```kotlin
    enum class ModelType {
        TYPE_1,
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
 
  ```kotlin
  @Entity(tableName = "models")
  data class ModelEntity(val attribute1: String, ...) {
       fun to<Model>() = Model(...)
  }

  fun Model.toModelEntity() = ModelEntity(...)
  ```

  Also if your Entity contains attribute with Enum type you need to create a type converter to convert the enum value to Int in database.
  Manage the null case conversion in @TypeConverter
       
  ```kotlin
  @TypeConverter
  fun TypeToInt(type: Type?): Int? = ...
  ```

  2. Declare new Dao & Add it to the Database
       
  ```kotlin
  @Dao
  interface ModelDao { }
  ```

  3. Create Repository implementation in data/repository package
    
## App
This module contains the Application Android based on MVVM Architecture

Package structured by functional scope
    - Activity, Application, etc...
    - common
        * extension : contain common extension ex : StringExtension, DateExtension, etc...
    - error : contain error enum class ex : InputError, InternalError, etc...
    - binding : contain **@BindingAdapter** to convert X into value in layout attribute.
    - user
    - meal
    - etc...
    
The module activities using Navigation Component, with multiple activities.
   1. <Name>Activity : empty activity. Use annotation @AndroidEntryPoint to import the Hilt module within activity
    
   ```kotlin
    @AndroidEntryPoint
    class HomeActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)
        }
    }
   ```
    
   2. Only create layout activity_<name>.xml with NavigationComponent.
    
   ```xml
    <merge xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        tools:context="<path to activity>">
    
        <androidx.fragment.app.FragmentContainerView
            android:id="@+id/<id>"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:defaultNavHost="true"
            app:navGraph="@navigation/<navigation_graph>" />
    
    </merge>
   ```
    
   3. For each activity create a navigation graph using the activity name
    - navigation_main

   4. MVVM + Data Binding
   
   - Create Model of View class. Post fix the name of the class with *Model*.
      * The class must extend **BaseObservable()**
      * The class must implement mapping method : *ModelOfView.toModel() : Model*
      * The class must implement mapping method (extension) : *Model.toModelOfView(): ModelOfView*
      * The class must provide observable field error 
      * The class must implement auto validation method on the observable field, use set() method to manage validation & error display.

  ```kotlin
  class NameOfFragmentModel(val attribute1: String? = null, ...) : BaseObservable() {
      var attribute1: String? = null
          set(value) {
              attribute1Error = ... // call validation method and return error InputError
              field = value
              validate()
              notifyChange()
          }
  
      var attribute1Error: InputError? = null
      var isValidate: Boolean = false
  
      fun toModel(): Model {
          return if (attribute1 != null) {
              Model(attribute1, ...)
          } else {
              throw ClassCastException("Unable to cast NameOfFragmentModel in Model")
          }
      }
  
      private fun validate() {
          isValidate = attribute1Error == null
      }
  }
  ```

   - Create your Fragment with associated ViewModel.
      * Use annotation **@AndroidEntryPoint** for Hilt module injection.
      * Use auto generated binding class (NameOfTheLayout) to init the data binding with **onCreateView()**.
      * Use auto generated binding class component to init the listener withing Fragment.
      * Use Hilt to inject the viewModel
   
   ```kotlin
   @AndroidEntryPoint
   class Fragment : Fragment() {
      private val viewModel: ViewModel by viewModels()
       
      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
         val binding = GeneratedBinding.inflate(inflater, container, false)
       
         binding.model = this.userFormViewModel.model
       
         binding.button.setOnClickListener { view -> ... }
       
         return binding.root
      }
   }
   ```

   - Create layout file with encapsulation <layout>.
     * Section <data> can be empty or null
     * Section <data> contains <variable> & <import> used within the layout.
     * Call the variable within layout attribute.
       - *One-way Data Binding (Update value -> Update Layout)* : **text="@{modelOfView.name}"**
       - *Two-way Data Binding (Update value <-> Update Layout)* : **text="@={modelOfView.name}"**

   ```xml
   <layout>
      <data>
         <import type="View"/>
         <variable name="modelOfView" type="com...user.ModelOfView"/>
      </data>
      <ConstraintLayout...>
          ...
      </ConstraintLayout>
   </layout>
   ```


      
