/*
 * Copyright 2020 Armory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.armory.secrets.silly;

import com.netflix.spinnaker.kork.secrets.EncryptedSecret;
import com.netflix.spinnaker.kork.secrets.InvalidSecretFormatException;
import com.netflix.spinnaker.kork.secrets.SecretEngine;
import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract public class AbstractSillySecretEngine implements SecretEngine {

    private final String password;

    public AbstractSillySecretEngine(String password) {
        log.info("Configuring SillySecretEngine with: " + password);
        this.password = password;
    }

    protected String getPassword() {
        return password;
    }

    public String identifier() {
        return "SillySecrets";
    }

    public byte[] decrypt(EncryptedSecret encryptedSecret) {
        return password.getBytes();
    }

    public void clearCache() { }

    public void validate(EncryptedSecret encryptedSecret) throws InvalidSecretFormatException { }

}
