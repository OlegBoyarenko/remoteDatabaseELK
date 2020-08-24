package com.packagename.myapp.component.connect;

import data.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Connect to remote database
 */

@Component
public class RmiConnectToDB {
    private Command command;

    public RmiConnectToDB(AppProperties appProperties) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(appProperties.getRmiHost(), 2005);
        this.command = (Command) registry.lookup(Command.class.getName());
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
