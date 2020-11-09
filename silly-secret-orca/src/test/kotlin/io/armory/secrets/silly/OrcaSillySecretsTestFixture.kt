package io.armory.secrets.silly

import com.netflix.spinnaker.kork.plugins.internal.PluginJar
import com.netflix.spinnaker.orca.api.test.OrcaFixture
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import java.io.File

@TestPropertySource(properties = [
  "spinnaker.extensibility.plugins-root-path=build/plugins",
  "spinnaker.extensibility.plugins.Armory.SillySecretsEngine.enabled=true",
  "spinnaker.extensibility.plugins.Armory.SillySecretsEngine.config.password=decrypted",
  "my.secret=encrypted:SillySecrets!key:encrypted"
])
class OrcaSillySecretsTestFixture : OrcaFixture() {

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
      .manifestAttribute("Plugin-Requires", "orca>=0.0.0")
      .extensions(mutableListOf(SillySecretEngine::class.java.name))
      .build()
  }
}
