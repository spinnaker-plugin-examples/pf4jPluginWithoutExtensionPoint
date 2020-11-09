package io.armory.secrets.silly

import com.netflix.spinnaker.clouddriver.api.test.ClouddriverFixture
import com.netflix.spinnaker.kork.plugins.internal.PluginJar
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.TestPropertySource
import java.io.File

@TestPropertySource(properties = [
  "spinnaker.extensibility.plugins-root-path=build/plugins",
  "spinnaker.extensibility.plugins.Armory.SillySecretsEngine.enabled=true",
  "spinnaker.extensibility.plugins.Armory.SillySecretsEngine.config.password=decrypted",
  "my.secret=encrypted:SillySecrets!key:encrypted"
])
class ClouddriverSillySecretsTestFixture : ClouddriverFixture() {

  @Value("\${my.secret}")
  lateinit var secret: String

  init {
    val pluginId = "Armory.SillySecretsEngine"
    val plugins = File("build/plugins").also {
      it.delete()
      it.mkdir()
    }

    PluginJar.Builder(plugins.toPath().resolve("$pluginId.jar"), pluginId)
      .pluginClass(SillySecretsPlugin::class.java.name)
      .pluginVersion("1.0.0")
      .manifestAttribute("Plugin-Requires", "clouddriver>=0.0.0")
      .extensions(mutableListOf(SillySecretEngine::class.java.name))
      .build()
  }
}
