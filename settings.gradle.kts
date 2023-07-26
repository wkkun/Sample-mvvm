pluginManagement {
    includeBuild("buildLogic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "Sample-mvvm"
include("app")
includeDir("base")


/**
 * 添加文件夹下面的所有依赖
 */
fun includeDir(dirName: String) {
    file(dirName).listFiles()?.forEach { module ->
        if (module.isDirectory){
            module.listFiles { child->
                return@listFiles child.name.equals("build.gradle.kts",true)
            }?.also {
                if (it.isNotEmpty()){
                    include(module.name)
                    project(":${module.name}").projectDir = module
                }
            }
        }
    }
}
