/**
 * Created by ddlucas for ${PROJECT} project.
 *
 * Copyright 2019, Lucas Software Engineering, Inc.
 * All rights reserved.

 */
import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.kotlin.dsl.*


open class GreetPlugin : Plugin<Project> {
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