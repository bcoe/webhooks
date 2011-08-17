package org.elasticsearch.webhooks;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.indices.IndicesLifecycle;

public class WebHooksPluginService extends AbstractLifecycleComponent<WebHooksPluginService>
{   
    @Inject
    public WebHooksPluginService(Settings settings, IndicesLifecycle indicesLifecycle) {
        super(settings);
        indicesLifecycle.addListener(
            new WebHooksIndicesLifecycleListener(this.logger)
        );
    }
    
    @Override
    protected void doClose() throws ElasticSearchException {
    }
    
    @Override
    protected void doStart() throws ElasticSearchException {
    }
    
    @Override
    protected void doStop() throws ElasticSearchException {
    }
}