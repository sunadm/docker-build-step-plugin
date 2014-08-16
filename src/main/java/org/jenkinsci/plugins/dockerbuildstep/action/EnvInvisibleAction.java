package org.jenkinsci.plugins.dockerbuildstep.action;

import hudson.model.InvisibleAction;

import java.util.Map;

import org.jenkinsci.plugins.dockerbuildstep.DockerEnvContributor;
import org.jenkinsci.plugins.dockerbuildstep.cmd.DockerCommand;

import com.github.dockerjava.client.model.ContainerInspectResponse;
import com.github.dockerjava.client.model.ExposedPort;
import com.github.dockerjava.client.model.Ports.Binding;

/**
 * Helper invisible action which is used for exchanging information between {@link DockerCommand}s and other object like
 * {@link DockerEnvContributor}.
 * 
 * @author vjuranek
 * 
 */
public class EnvInvisibleAction extends InvisibleAction {

    private ContainerInspectResponse containerInfo;

    public EnvInvisibleAction() {
    }

    public EnvInvisibleAction(ContainerInspectResponse containerInfo) {
        this.containerInfo = containerInfo;
    }

    public ContainerInspectResponse getContainerInfo() {
        return containerInfo;
    }

    public void setContainerInfo(ContainerInspectResponse containerInfo) {
        this.containerInfo = containerInfo;
    }

    // convenient shortcut methods

    public String getId() {
        return containerInfo.getId();
    }

    public String getHostName() {
        return containerInfo.getConfig().getHostName();
    }

    public String getIpAddress() {
        return containerInfo.getNetworkSettings().getIpAddress();
    }
    
    public Map<ExposedPort, Binding> getPortBindings() {
        return containerInfo.getNetworkSettings().getPorts().getBindings();
    }

}
