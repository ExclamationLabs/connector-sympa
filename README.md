# Sympa connector for Midpoint Integration

Open source Midpoint Identity Management Connector for the [Sympa List Server](https://www.sympa.org/).

This connector is based on and leverages the [Exclamation Labs Connector Base Framework](https://github.com/ExclamationLabs/connector-base)

## Overview

This software is licensed under the terms of [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 

This connector uses the Trusted Application interface to interact with a Sympa SOAP service. This interface is described 
in the Sympa Administration Manual [here](https://sympa-community.github.io/manual/customize/soap-api.html#trust-remote-applications)  

The current version integrates with a Sympa SOAP service to allow a Midpoint instance to perform the 
following operations: 

- Create A List
- Close A List
- Get all lists 
- Get one list by list name or list address.

## Getting Started

- After installing the Sympa Server you will need to create or modify the *trusted_application.conf* file to produce an 
  application name, and an application password that can be used to configure the connection.
  
- Download the latest version of the connector from 
- Install and configure the connector into your midpoint instance

- Create a resource for each Sympa instance that you wish to control

## Midpoint Configuration

- Copy the compiled connector jar file to the *icf-connectors* subdirectory of your Midpoint instance. This is the 
connector library folder.

- Restart midPoint. 

- Within Midpoint create a resource type definition for each Sympa Instance you want to control. Each instance will 
  need its onw resource configuration file. Make sure to set the fully qualified file location of the configuration file. 
  

##Configuration Properties

The midpoint IAM must supply the following configuration properties for the connector to operate. No value can be blank or null.
See https://github.com/ExclamationLabs/connector-sympa/blob/main/src/main/resources/sympa-config-example.properties 
for an example configuration file. 
  
- **app.name** is the trusted application name specified in the target Sympa server's **trusted_applications.conf** file. 

- **app.password** is the trusted application password specified by the Sympa server's administrator and whose md5 hash 
  is stored in the **trusted_applications.conf** file.
  
- **list.master** is the email address of the list master who is permitted to perform operations executed by the connector. 
  The Sympa administrator should grant the necessary permissions to the email address specified here.
  
- **sympa.domain.url** is the url of the Sympa Service you which to control. This url endpoint is typically specified as 
  the port location in the service WSDL.


- **sympa.domain.wsdl** is the url of the Sympa server's WSDL file. This url should contain the same fully qualified 
  host name as the **sympa.domain.url** property  

