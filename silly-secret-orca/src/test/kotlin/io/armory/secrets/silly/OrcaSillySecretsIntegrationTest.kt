package io.armory.secrets.silly

import dev.minutest.junit.JUnit5Minutests
import dev.minutest.rootContext
import org.springframework.test.context.TestContextManager
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class OrcaSillySecretsIntegrationTest : JUnit5Minutests {
  fun tests() = rootContext<OrcaSillySecretsTestFixture> {
    fixture {
      OrcaSillySecretsTestFixture().also {
        TestContextManager(OrcaSillySecretsTestFixture::class.java).prepareTestInstance(it)
      }
    }

    test("secret engine decrypts secrets") {
      expectThat(secret).isEqualTo("decrypted")
    }
  }
}