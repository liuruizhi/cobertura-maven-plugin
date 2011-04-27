package org.codehaus.mojo.cobertura;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.PlexusTestCase;

/**
 * @author Edwin Punzalan
 */
public class CoberturaCheckMojoTest
    extends AbstractCoberturaTestCase
{
    public void testCheckFailureNoHalt()
        throws Exception
    {
        Mojo mojo =
            lookupMojo( "check", PlexusTestCase.getBasedir() +
                "/src/test/plugin-configs/check-no-halt-plugin-config.xml" );

        setVariableValueToObject( mojo, "pluginClasspathList", getPluginClasspath() );

        try
        {
            mojo.execute();
        }
        catch ( MojoExecutionException e )
        {
            if ( e.getMessage().equals( "Coverage check failed. See messages above." ) )
            {
                fail( "Should fail when rates are not satisfied" );
            }
            else
            {
                fail( "Unexpected exception" );
            }
        }
    }

    public void testNoCheckParameter()
        throws Exception
    {
        Mojo mojo =
            lookupMojo( "check", PlexusTestCase.getBasedir() + "/src/test/plugin-configs/check-plugin-config.xml" );

        setVariableValueToObject( mojo, "pluginClasspathList", getPluginClasspath() );

        setVariableValueToObject( mojo, "check", null );

        try
        {
            mojo.execute();

            fail( "Should fail on null check parameter" );
        }
        catch ( MojoExecutionException e )
        {
            if ( !e.getMessage().equals( "The Check configuration is missing." ) )
            {
                fail( "Unexpected exception" );
            }
        }
    }
}
