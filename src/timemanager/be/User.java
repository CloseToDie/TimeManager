/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.be;

/**
 *
 * @author kaspe
 */
public class User
{
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
                
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }
    
    
    
   
}
