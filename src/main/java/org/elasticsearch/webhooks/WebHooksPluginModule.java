package org.elasticsearch.webhooks;

import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.common.settings.Settings;

public class WebHooksPluginModule extends AbstractModule {
	private final Settings settings;

	public WebHooksPluginModule(Settings settings) {
		this.settings = settings;
	}

	@Override
	protected void configure() {
		bind(WebHooksPluginService.class).asEagerSingleton();
	}
}