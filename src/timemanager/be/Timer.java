/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.be;

import java.time.LocalDateTime;

/**
 *
 * @author kaspe
 */
public class Timer
{

    LocalDateTime startTime;
    LocalDateTime stopTime;

    public LocalDateTime getStartTime(LocalDateTime a)
    {
        return startTime;
    }

    public void setStartTime(LocalDateTime a)
    {

        a = startTime;
    }
    public LocalDateTime getStopTime(LocalDateTime b)
    {
        return stopTime;
    }

    public void setStopTime(LocalDateTime b)
    {

        b = stopTime;
    }
}
