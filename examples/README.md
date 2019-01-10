## DEMO

#### gradle-hello-groovy-dsl
- ` gradle init --dsl groovy --package hello.dsl --project-name gradle-hello-groovy-dsl --test-framework junit --type java-application `
- add hello task
<code>  
tasks.register("hello") { 
    group = "greet"
    description "This is a groovy task inside build.gradle"
    doLast { 
        println 'Hello, From inside build.gradle'
    }
}
</code>


#### gradle-hello-kotlin-dsl
- ` gradle init --dsl kotlin --package hello.dsl --project-name gradle-hello-kotlin-dsl --test-framework kotlintest --type kotlin-application `
- add hello task
<code>

tasks.register("hello") { 
    description="hello kotlin task from inside build.gradle.kts"
    group = "greet"
    doLast { 
        println("Hello, from inside build.gradle.kts")
    }
}
</code>
- now make it generic
<code>
open class Greeting: DefaultTask() {  
    lateinit var message: String   
    lateinit var recipient: String
    @TaskAction 
    fun sayGreeting() {
        println("$message, $recipient!") 
    }
}


tasks.register<Greeting>("hello") { 
    group = "Welcome"
    description = "Produces a world greeting"
    message = "Hello" 
    recipient = "World"
}
</code>
- now add one more
<code>
tasks.register<Greeting>("gutenTag") {
    group = "Welcome"
    description = "Produces a German greeting"
    message = "Guten Tag"
    recipient = "Welt"
}
</code>


#### hello-world-kotlin-plugin
- ` gradle init --dsl kotlin --package hello.dsl --project-name gradle-hello-kotlin-plugin --test-framework kotlintest --type kotlin-application `
- mkdir -p buildSrc/src/main/kotlin
- add buildSrc/build.gradle.kts
<code>  
plugins {  
    `kotlin-dsl`  
}  
gradlePlugin {  
    plugins {  
        register("greet-plugin") {  
            id = "greet"
            implementationClass = "GreetPlugin"
        }  
    }  
}  
repositories {
    jcenter()
}  
</code>  
- add buildSrc/src/main/kotlin/GreetPlugin.kt
<code>  
import org.gradle.api.Plugin  
import org.gradle.api.Project  
import org.gradle.kotlin.dsl.*
  open class GreetPlugin :   Plugin<Project> {  
    object props {  
        val greetPluginVersion="1.0"  
        val kotlinVersion="1.3.11"
        val springBootVersion="2.1.1.RELEASE"
        val jdkVersion="1.8"
    }  
    override fun apply(project: Project): Unit = project.run {
        tasks {
            register("greet") {
                group = "greet"
                description = "Prints a description of ${project.name}."
                doLast {
                    println("I'm ${project.name}.")
                }
            }
        }
    }
}
</code>
- add build.gradle.kts
<code>
plugins {  
    id("org.jetbrains.kotlin.jvm") version ("${GreetPlugin.props.kotlinVersion}")
    application
    greet
}
repositories {
    jcenter()
}  
dependencies {
compile("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable")
implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
testImplementation("org.jetbrains.kotlin:kotlin-test")
testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}  
application {
    mainClassName = "hello.dsl.AppKt"
}
tasks.register("hello") {
    group = "greet"
    description = "Produces a world greeting"
    doLast {
  println("Hello with greet plugin: ${GreetPlugin.props.greetPluginVersion}")  
  }  
}  
</code>

#### goodbye-maven
- unzip etc/goodbye/goodbye-maven.zip
- cd to unzipped folder
- ` gradle init `
- ` add gradle plugin  id 'org.springframework.boot' version '2.1.1.RELEASE'  `

#### hello-world-cpp
- ` gradle init --dsl kotlin --project-name gradle-hello-cpp --type basic `
- add main.cpp  
<code>
#include \<iostream>  
  int main() {  
    std::cout << "Hello World !" << std::endl;  
    return 0;  
}  
</code>
- add plugins
<code>  
plugins {
    id("cpp-application")   
}  
</code>
- add runCpp task    
<code>
tasks.register<Exec>("runCpp") {   
    group = "exec"  
    description="runs the output from build exe"  
    commandLine = listOf("./build/exe/main/debug/${rootProject.name}")  
}
</code>  


