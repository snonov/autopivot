/* 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.av.autopivot.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.av.autopivot.AutoPivotGenerator;
import com.av.csv.CSVFormat;
import com.qfs.server.cfg.IActivePivotManagerConfig;
import com.quartetfs.biz.pivot.definitions.IActivePivotManagerDescription;

/**
 * 
 * Configure the ActivePivot Manager for the AutoPivot application.
 * <p>
 * The description of the cube are generated automatically
 * based on the format of the CSV file.
 * 
 * @author ActiveViam
 *
 */
public class ActivePivotManagerConfig implements IActivePivotManagerConfig {

	/** Spring environment */
	@Autowired
	protected Environment env;

	/** Datasource configuration */
	@Autowired
	protected SourceConfig sourceConfig;

	/** Datastore configuration */
	@Autowired
	protected DatastoreConfig datastoreConfig;

	@Override
	public IActivePivotManagerDescription managerDescription() {

		CSVFormat discovery = sourceConfig.discoverFile();

		AutoPivotGenerator generator = datastoreConfig.generator();
		IActivePivotManagerDescription manager =
				generator.createActivePivotManagerDescription(discovery, env);

		return manager;
	}

}
