package ru.stqa.mantis.manager;

import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

import javax.crypto.CipherOutputStream;

public class JamesCliHelper extends HelperBase{

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password){
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        CommandLine cmd = new CommandLine(
                "java",
                "-cp",
                "\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd",
                "AddUser", email, password);
        cmd.setWorkingDirectory(manager.property("james.workingDir"));
        CircularOutputStream output = new CircularOutputStream();
        cmd.copyOutputTo(output);
        cmd.execute();
        cmd.waitFor();
        System.out.println(output);
    }
}
