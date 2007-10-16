package org.eclipse.imp.presentation.builders;

import org.eclipse.core.resources.IProject;

import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;

import org.eclipse.imp.smapifier.builder.SmapiProjectNature;

import org.eclipse.imp.presentation.Activator;

public class ImppNature extends ProjectNatureBase {
    // SMS 28 Mar 2007:  plugin class now totally parameterized
    public static final String k_natureID= Activator.kPluginID + ".nature";

    public String getNatureID() {
	return k_natureID;
    }

    public String getBuilderID() {
	return ImppBuilder.BUILDER_ID;
    }

    public void addToProject(IProject project) {
	super.addToProject(project);
	new SmapiProjectNature("impp").addToProject(project);
    };

    protected void refreshPrefs() {
	// TODO implement preferences and hook in here
    }

    public IPluginLog getLog() {
	// SMS 28 Mar 2007:  plugin class now totally parameterized
	return Activator.getInstance();
    }

    protected String getDownstreamBuilderID() {
	// TODO Auto-generated method stub
	return null;
    }
}
