package org.elasticsearch.webhooks;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.index.Index;
import org.elasticsearch.index.service.IndexService;
import org.elasticsearch.index.shard.ShardId;
import org.elasticsearch.index.shard.service.IndexShard;

import org.elasticsearch.indices.IndicesLifecycle;

public class WebHooksIndicesLifecycleListener extends IndicesLifecycle.Listener {
    
    private WebHooksResponder responder;
        
    public WebHooksIndicesLifecycleListener(ESLogger logger, Settings settings) {
        this.responder = new WebHooksResponder(logger, settings);
    }
    
    @Override public void afterIndexShardStarted(IndexShard indexShard) {
        this.responder.respond("started", indexShard.shardId().index().name());
    }
    
    @Override public void afterIndexClosed(Index index, boolean delete) {
        this.responder.respond("closed", index.name());
    }
        
    @Override public void afterIndexCreated(IndexService indexService) {
        this.responder.respond("created", indexService.index().name());
    }
}