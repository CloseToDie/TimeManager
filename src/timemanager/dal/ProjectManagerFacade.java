/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.dal;

import java.util.ArrayList;
import timemanager.be.Project;

/**
 *
 * @author andreasvillumsen
 */
public interface ProjectManagerFacade {
    public ArrayList<Project> getProjects();
    public Project getProject(Project project);
    public boolean storeProject(Project project);
    public boolean updateProject(Project project);
    public boolean deleteProject(Project project);
}
