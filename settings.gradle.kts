rootProject.name = "TestRates"

include(
    ":app",
    ":component.mvp",
    ":component.ui",
    ":component.utils",
    ":component.test",
    ":component.navigation",
    ":feature.rates",
    ":feature.splash"
)

project(":component.mvp").projectDir = File("component/mvp")
project(":component.ui").projectDir = File("component/ui")
project(":component.utils").projectDir = File("component/utils")
project(":component.test").projectDir = File("component/test")
project(":component.navigation").projectDir = File("component/navigation")
project(":feature.rates").projectDir = File("feature/rates")
project(":feature.splash").projectDir = File("feature/splash")