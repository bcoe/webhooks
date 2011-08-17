package org.elasticsearch.plugin.webhooks;

import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.AbstractPlugin;

import org.elasticsearch.webhooks.WebHooksPluginService;
import org.elasticsearch.webhooks.WebHooksPluginModule;

import java.util.Collection;

import static org.elasticsearch.common.collect.Lists.*;

/**
 * @author Benjamin Coe
 */
public class WebHooksPlugin extends AbstractPlugin {

    private final Settings settings;

    public WebHooksPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override public String name() {
        return "indices-webhooks";
    }

    @Override public String description() {
        return "Allows webhooks to be registered for index lifecycle events.";
    }
    
    @Override public Collection<Class<? extends Module>> modules() {
        Collection<Class<? extends Module>> modules = newArrayList();
        modules.add(WebHooksPluginModule.class);
        return modules;
    }

    @Override public Collection<Class<? extends LifecycleComponent>> services() {
        Collection<Class<? extends LifecycleComponent>> services = newArrayList();
        services.add(WebHooksPluginService.class);
        return services;
    }
}