package org.elasticsearch.webhooks;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.inject.Inject;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.service.IndexService;
import org.elasticsearch.index.shard.ShardId;
import org.elasticsearch.index.shard.service.IndexShard;

import org.elasticsearch.indices.IndicesLifecycle;

public class WebHooksIndicesLifecycleListener extends IndicesLifecycle.Listener {
    
    private ESLogger logger;
        
    public WebHooksIndicesLifecycleListener(ESLogger logger) {
        this.logger = logger;
    }
    
    @Override public void afterIndexClosed(Index index, boolean delete) {
        this.logger.info("The index " + index.name() + " finished closing." );
    }
        
    @Override public void afterIndexCreated(IndexService indexService) {
        this.logger.info("The index " + indexService.index().name() + " finished being created." );
    }
}