package org.elasticsearch.webhooks;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.indices.IndicesLifecycle;

public class WebHooksPluginService extends AbstractLifecycleComponent<WebHooksPluginService>
{   
    private IndicesLifecycle indicesLifecycle;
    private Settings settings;
    
    @Inject
    public WebHooksPluginService(Settings settings, IndicesLifecycle indicesLifecycle) {
        super(settings);
        this.indicesLifecycle = indicesLifecycle;
        this.settings = settings;
    }
    
    @Override
    protected void doClose() throws ElasticSearchException {
    }
    
    @Override
    protected void doStart() throws ElasticSearchException {
        this.indicesLifecycle.addListener(
            new WebHooksIndicesLifecycleListener(this.logger, this.settings)
        );
    }
    
    @Override
    protected void doStop() throws ElasticSearchException {
    }
}