/*
    Copyright 2021 Exclamation Labs
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.exclamationlabs.connid.base.sympa;

import com.exclamationlabs.connid.base.connector.BaseFullAccessConnector;
import com.exclamationlabs.connid.base.sympa.adapter.SympaListsAdapter;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.driver.SympaDriver;
import org.identityconnectors.framework.spi.ConnectorClass;

@ConnectorClass(displayNameKey = "sympa.connector.display",
        configurationClass = SympaConfiguration.class)
public class SympaConnector extends BaseFullAccessConnector<SympaConfiguration> {

    public SympaConnector() {
        super(SympaConfiguration.class);
        setDriver(new SympaDriver());
        setAdapters(new SympaListsAdapter());
    }

}
