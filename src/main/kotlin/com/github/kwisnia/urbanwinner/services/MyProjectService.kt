package com.github.kwisnia.urbanwinner.services

import com.intellij.openapi.project.Project
import com.github.kwisnia.urbanwinner.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
