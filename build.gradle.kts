plugins {
    id("fpgradle-minecraft") version("0.10.0")
}

group = "mega"

minecraft_fp {
    mod {
        modid   = "FTBU"
        name    = "FTBUtilities"
        rootPkg = "ftb.utils"
    }

    api {
        packages = listOf("api")
    }

    tokens {
        tokenClass = "mod.Tags"
    }

    publish {
        changelog = "https://github.com/myname/mymod/releases/tag/$version"
        maven {
            repoUrl  = "https://mvn.falsepattern.com/gtmega_releases"
            repoName = "mega"
        }
    }
}

repositories {
    exclusive(mega(), "mega")
}

dependencies {
    implementation("mega:FTBL-mc1.7.10:1.1.1-mega:dev")
}