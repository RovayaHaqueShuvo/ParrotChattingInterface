# ParrotChattingInterface

#Adding Dependency in  "build.gradle.kts(Module:app)"

    implementation ("com.github.mukeshsolanki.android-otpview-pinview:otpview:3.1.0")
    implementation ("com.github.mukeshsolanki.android-otpview-pinview:otpview-compose:3.1.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.github.pgreze:android-reactions:1.5.1")
    implementation ("com.github.3llomi:CircularStatusView:V1.0.3")
    implementation ("com.github.OMARIHAMZA:StoryView:1.0.2-alpha")


#Addindg URL in "setting.gradle.kts(Project Setting)


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
        


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

 
