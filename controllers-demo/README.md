# Miscellaneous

- Under the _other_ directory some script to test the controllers from command line.

# Script for creation of the project

```powershell
mn -v create-app   `
controllers-demo `
--build=gradle_kotlin  `
--lang=java  `
--java-version=21 `
--test=junit  `
--features=http-client
```

# Build from command line

I use jdk 21.

```powershell
.\gradlew.bat clean build
.\gradlew.bat clean build -x test
```

## Micronaut 4.6.0 Documentation

- [User Guide](https://docs.micronaut.io/4.6.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.6.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.6.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)

## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#nettyHttpClient)

## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


