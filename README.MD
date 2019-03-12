troubleshooting

1)Git error git Permission to url denied to xxx
git remote set-url origin https://<username>:<password>@github.com/narulasrinivas/FirstRepository.git

2)Android studio 3.0: Unable to resolve dependency for :app@dexOptions/compileClasspath': Could not resolve project :animators




To Solve the issue, simply.

What ever the

buildTypes{
          debug{ ... }
          release{ ... }
    }

you have in your (app) build.gradle file, You have to include all the buildTypes{ } with same names as like

buildTypes{
      debug{ ... }
      release{ ... }
}


-----------------------------------------------------------------------------
Create and publish a library to github using jitpack
1)create library module
2)modify the build.gradle of library module
https://jitpack.io/

apply plugin: 'com.android.library'
apply plugin: 'com.github.dcendents.android-maven'
repositories {
    mavenCentral()
    google()
    jcenter()
    maven { url "https://jitpack.io" }
}
group='com.github.huynguyenwork86'


3)code the libary and when ready
4)create a tag using command:
 git tag -a v1.1 -m "onemonthcalendarview version 1.1"
 git push origin v1.1
 
5)./gradlew install

that's that

-----------------------------------------------------------------------------
USE in another app

1)modify the build.gradle of the project
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        //DO NOT ADD IT HERE
//        maven { url 'https://jitpack.io' }
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.3.2'
        classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

2)modify the build.gradle of the app module

implementation 'com.github.huynguyenwork86:onemonthcalendarsample:v1.1'



