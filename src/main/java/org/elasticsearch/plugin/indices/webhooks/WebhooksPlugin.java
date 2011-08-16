/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.plugin.indices.webhooks;

import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.AbstractPlugin;

import java.util.Collection;

import static org.elasticsearch.common.collect.Lists.*;

import org.elasticsearch.indices.IndicesModule;
import org.elasticsearch.indices.IndicesLifecycle;

import org.elasticsearch.webhooks.WebhooksIndicesLifecycle;

/**
 * @author Benjamin Coe
 */
public class WebhooksPlugin extends AbstractPlugin {

    private final Settings settings;

    public WebhooksPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override public String name() {
        return "indices-webhooks";
    }

    @Override public String description() {
        return "Allows webhooks to be registered for index lifecycle events.";
    }

    @Override public void processModule(Module module) {
        if (module instanceof IndicesModule) {
            module.bind(IndicesLifecycle.class).to(WebhooksIndicesLifecycle.class).asEagerSingleton();
        }
    }
}