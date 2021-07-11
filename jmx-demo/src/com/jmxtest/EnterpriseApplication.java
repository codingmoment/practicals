package com.jmxtest;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class EnterpriseApplication
{

  public static void main(String[] args) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException
  {
    // Creating a MBean object
    LifeCycle lifeCycle = new LifeCycle("Nilesh", 0);

    // Registering the MBean object into MBeanServer
    MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
    ObjectName name = new ObjectName("com.jmxtest:type=LifeCycle");
    mBeanServer.registerMBean(lifeCycle, name);

    DummyThread dummyThread = new DummyThread(lifeCycle);
    dummyThread.start();

  }

  private static class DummyThread extends Thread
  {
    private LifeCycle lifeCycle = null;

    public DummyThread(LifeCycle lifeCycle)
    {
      this.lifeCycle = lifeCycle;
    }

    @Override
    public void run()
    {
      while (lifeCycle.isAlive())
      {
        System.out.println("Lifecycle " + lifeCycle.getName() + " is running with age " + lifeCycle.getAge());

        try
        {
          Thread.sleep(2000);
        }
        catch (Exception e)
        {
          // consume
        }
      }
    }
  }
}
