![CI](https://github.com/spinnaker-plugin-examples/pf4jPluginWithoutExtensionPoint/workflows/CI/badge.svg)
![Latest Kork](https://github.com/spinnaker-plugin-examples/pf4jPluginWithoutExtensionPoint/workflows/Latest%20Kork/badge.svg?branch=master)

Spinnaker Plugin (PF4J based) that uses a regular interface as an extension point.

Note these things when extending a regular interface with Pf4J:
* currently your extension can only have one interface that is the implicit extension point
* you need to provide this compiler argument `-Apf4j.ignoreExtensionPoint`

<h2>Usage</h2>

1) Run `./gradlew releaseBundle`
2) Put the `/build/distributions/pf4jPluginWithoutExtensionPoint-X.zip` in the [configured plugins location for your service](https://pf4j.org/doc/packaging.html).
3) Configure the Spinnaker service. Put the following in the service yml to enable the plugin and configure the extension.
```
spinnaker:
  extensibility:
    plugins:
      Armory.SillySecretsPlugin:
        enabled: true
        extensions:
          armory.sillySecrets:
            enabled: true
            config:
              password: 'psswd'
```

Or use the [examplePluginRepository](https://github.com/spinnaker-plugin-examples/examplePluginRepository) to avoid copying the plugin `.zip` artifact.

To debug the plugin inside a Spinnaker service (like Orca) using IntelliJ Idea follow these steps:

1) Run `./gradlew releaseBundle` in the plugin project.
2) Copy the generated `.plugin-ref` file under `build` in the plugin project submodule for the service to the `plugins` directory under root in the Spinnaker service that will use the plugin .
3) Link the plugin project to the service project in IntelliJ (from the service project use the `+` button in the Gradle tab and select the plugin build.gradle).
4) Configure the Spinnaker service the same way specified above.
5) Create a new IntelliJ run configuration for the service that has the VM option `-Dpf4j.mode=development` and does a `Build Project` before launch.
6) Debug away...
