package io.armory.secrets.silly;

import com.netflix.spinnaker.kork.plugins.ConfigurableExtension;

public abstract class ConfiguredExtension<T> implements ConfigurableExtension<T> {
    @Override
    public abstract void setConfiguration(T configuration);
}
